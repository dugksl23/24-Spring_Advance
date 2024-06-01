package hello.springAdvanced.trace.helloTrace;

import hello.springAdvanced.trace.TraceId;
import hello.springAdvanced.trace.TraceStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Getter
@Setter
public class HelloTraceV1Impl implements HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<EX-";


    @Override
    public TraceStatus begin(String msg) {
        TraceId traceId = new TraceId();
        long currentStartTimeMillis = System.currentTimeMillis();

        // 로그 출력
        log.info("[{}] {}{}", traceId.getTransactionId(), addSpace(START_PREFIX, traceId.getDepthLevel()), msg);
        return new TraceStatus(traceId, currentStartTimeMillis, msg);
    }

    @Override
    public void end(TraceStatus status) {
        completeLog(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception ex) {
        completeLog(status, ex);
    }

    private void completeLog(TraceStatus status, Exception ex) {
        long endTimeMillis = System.currentTimeMillis();
        long resultTimeMs = endTimeMillis - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (ex == null) {
            log.info("[{}] {}{} time = {}ms", traceId.getTransactionId(), addSpace(END_PREFIX, traceId.getDepthLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getTransactionId(), addSpace(EX_PREFIX, traceId.getDepthLevel()), status.getMessage(), resultTimeMs, ex.toString());
        }
    }


    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
