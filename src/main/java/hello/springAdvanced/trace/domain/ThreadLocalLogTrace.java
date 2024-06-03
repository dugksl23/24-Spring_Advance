package hello.springAdvanced.trace.domain;

import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceId;
import hello.springAdvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<EX--";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>(); // threadLocal 로 동시성 이슈 해결

    private TraceId synTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextDepthLevel());
        }

        return traceIdHolder.get();
    }


    @Override
    public TraceStatus begin(String message) {
        // begin() 과 beginSync() 함수 분기 처리 함수
        TraceId traceId = synTraceId();
        log.info("[{}] {}{} | LogCode: {}", traceId.getTransactionId(), addSpace(START_PREFIX, traceId.getDepthLevel()), message, LogCode.BEGIN);
        return new TraceStatus(traceId, message, LogCode.BEGIN);
    }

    @Override
    public void end(TraceStatus status) {

        TraceId traceId = status.getTraceId();
        status.setLogCode(LogCode.END);
        Long endTimeMs = status.getEndTimeMs();
        long duration = status.getDurationMs();

        log.info("[{}] {}{} | LogCode: {} | Duration Time = {}ms", traceId.getTransactionId(), addSpace(END_PREFIX, traceId.getDepthLevel()), status.getMessage(), status.getLogCode(), duration);

        // 현재 LogTrace 가 singleton 으로 등록이 되어있기에
        // 하나의 객체를 모두 공유한다. 따라서 traceIdHolder 를 null 로 만들어야 한다.
        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstDepthLevel()) {
            traceIdHolder.remove();
            log.info("threadLocal.remove() 호출  : {}", traceIdHolder.get());
        } else {
            // 중간 단계일 경우에는 이전 레벨로 원복
            // why? 각 계층마다 정상 실행 되었을 경우, 해당 계층의 end 의 level 에 맞는 위치에
            //      로그를 찍기 위함이기에 하나씩 depthLevel 을 -1 한다.
            traceIdHolder.set(traceId.createPreviousDepthLevel());
        }

    }

    @Override
    public void exception(TraceStatus status, Exception ex) {
        status.setLogCode(LogCode.ERROR);
        TraceId traceId = status.getTraceId();
        Long endTimeMs = status.getEndTimeMs();
        Long duration = status.getDurationMs();
        log.info("[{}] {}{} | LogCode: {} | Duration Time = {}ms | Ex = {}", traceId.getTransactionId(), addSpace(EX_PREFIX, traceId.getDepthLevel()), status.getMessage(), status.getLogCode(), duration, ex.toString());
    }


    // depth Level 만드는 용도 -> traceId 와는 무관.
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }

}
