package hello.proxy.trace;


import hello.springAdvanced.trace.LogCode.LogCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ThreadLocalLogTraceV2 implements ProxyLogTrace {

    private static final String BEGIN_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<EX--";

    private ThreadLocal<TraceIdV2> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatusV2 begin(String message) {
        TraceIdV2 traceId = syncTraceId();
        log.info("[{}] {}{} | LogCode : {}", traceId.getTraceId(),
                addSpace(BEGIN_PREFIX, traceId.getDepthLevel()),
                message, LogCode.BEGIN);
        Long startTime = System.currentTimeMillis();
        return new TraceStatusV2(traceId, message, LogCodeV2.BEGIN);
    }



    @Override
    public void end(TraceStatusV2 status) {
        /**
         * 2024.06.09. end 일 경우에는 syncTraceId() 가 아니라,
         * 전달된 status를 바탕으로 traceId.createPreviousDepthLevel() 실행되게끔 해야 한다..
         */
        TraceIdV2 traceId = status.getTraceId();
        Long endTime = System.currentTimeMillis();
        Long duration = endTime - status.getStartTime();
        log.info("[{}] {}{} | LogCode : {} | Duration: {} ms",
                traceId.getTraceId(),
                addSpace(END_PREFIX, traceId.getDepthLevel()),
                status.getMessage(),
                LogCode.END, duration);

        releaseTransactionId();
    }

    @Override
    public void exception(TraceStatusV2 status, Exception e) {
        TraceIdV2 traceId = status.getTraceId();
        log.info("[{}] {}{}, LogCode : {} | Ex= {}", traceId,
                addSpace(EX_PREFIX, traceId.getDepthLevel()),
                status.getMessage(),
                LogCodeV2.EXCEPTION,
                e.toString());
    }

    private TraceIdV2 syncTraceId() {
        TraceIdV2 traceId = traceIdHolder.get();
        if (traceId == null) {
            traceIdHolder.set(new TraceIdV2());
        } else {
            traceIdHolder.set(traceId.createNextDepthLevel());
        }

        return traceIdHolder.get();
    }

    private void releaseTransactionId() {

        TraceIdV2 traceId = traceIdHolder.get();
        if(traceId.isFirstDepthLevel()){
            traceIdHolder.remove();
        } else {
            // 각 depth 마다 end() 함수 호출하기에 depthLevel 감소 시켜줘야 한다.
            traceIdHolder.set(traceId.createPreviousDepthLevel());
        }

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
