package hello.springAdvanced.trace.domain;

import hello.springAdvanced.trace.LogCode.LogCode;
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
// logger 의 역할
public class LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<EX-";

    // v1
    public TraceStatus begin(String msg) {
        TraceId traceId = new TraceId();
        // 로그 출력
        log.info("[{}] {}{}", traceId.getTransactionId(), addSpace(START_PREFIX, traceId.getDepthLevel()), msg);
        return new TraceStatus(traceId, msg, LogCode.BEGIN);
    }


    public void completeLog(LogCode code, TraceStatus status, Exception ex) {
        if (code.getValue() == LogCode.END.getValue()) {
            status.setMessage(LogCode.END.getValue());
            createLog(status, null);
        }
        if (code.getValue() == LogCode.ERROR.getValue()) {
            status.setMessage(LogCode.ERROR.getValue());
            createLog(status, ex);
        }
    }


    private void createLog(TraceStatus status, Exception ex) {;
        TraceId traceId = status.getTraceId();
        if (ex == null) {
            log.info("[{}] {}{} time = {}ms", traceId.getTransactionId(), addSpace(END_PREFIX, traceId.getDepthLevel()), status.getMessage(), status.getEndTimeMs());
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getTransactionId(), addSpace(EX_PREFIX, traceId.getDepthLevel()), status.getMessage(), status.getEndTimeMs(), ex.toString());
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
