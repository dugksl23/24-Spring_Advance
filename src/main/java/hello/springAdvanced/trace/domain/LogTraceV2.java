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
//loggerTrace = LoggerFactory
public class LogTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<EX--";


    // v1
    public TraceStatus begin(String msg) {
        TraceId traceId = new TraceId();
        log.info("[{}] {}{} LogCode: {}", traceId.getTransactionId(), addSpace(START_PREFIX, traceId.getDepthLevel()), msg, LogCode.BEGIN);
        return new TraceStatus(traceId, msg, LogCode.BEGIN);
    }

    // v2에 추가
    public TraceStatus beginSync(TraceId traceId, String msg) {
        TraceId nextDepthLevel = traceId.createNextDepthLevel();
        log.info("[{}] {}{} LogCode: {}", nextDepthLevel.getTransactionId(), addSpace(START_PREFIX, nextDepthLevel.getDepthLevel()), msg, LogCode.BEGIN);
        return new TraceStatus(nextDepthLevel, msg, LogCode.BEGIN);
    }

    public void completeLog(LogCode code, TraceStatus status, Exception ex) throws Exception {
        if (code == LogCode.END) {
            status.setEndTimeMs(System.currentTimeMillis());
            status.setLogCode(LogCode.END);
            endLog(status);
        }

        if (code == LogCode.ERROR) {
            status.setEndTimeMs(System.currentTimeMillis());
            status.setLogCode(LogCode.ERROR);
            exceptionLog(status, ex);
        }
    }

    private void endLog(TraceStatus status) {
        TraceId traceId = status.getTraceId();
        long duration = status.getEndTimeMs();

        log.info("[{}] {}{} LogCode: {} End Time = {}ms",
                traceId.getTransactionId(),
                addSpace(EX_PREFIX, traceId.getDepthLevel()),
                status.getMessage(),
                status.getLogCode(),
                duration);

        log.info(status.toString());
    }

    private void exceptionLog(TraceStatus status, Exception ex) throws Exception {
        TraceId traceId = status.getTraceId();
        long duration = status.getEndTimeMs();
        if (ex == null) {
            log.info("[{}] {}{}, LogCode: {}, End Time = {}ms",
                    traceId.getTransactionId(),
                    addSpace(END_PREFIX, traceId.getDepthLevel()),
                    status.getMessage(),
                    status.getLogCode(),
                    duration);

        } else {
            log.info("[{}] {}{} LogCode: {} End Time = {}ms ex = {}",
                    traceId.getTransactionId(),
                    addSpace(EX_PREFIX, traceId.getDepthLevel()),
                    status.getMessage(),
                    status.getLogCode(),
                    duration,
                    ex.toString());
        }

        log.info(status.toString());
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }


//    // v1
//    public TraceStatus begin(String msg) {
//        TraceId traceId = new TraceId();
//        // 로그 출력
//        log.info("[{}] {}{}", traceId.getTransactionId(), addSpace(START_PREFIX, traceId.getDepthLevel()), msg, LogCode.BEGIN);
//        return new TraceStatus(traceId, msg, LogCode.BEGIN);
//    }
//
//    //　v2에 추가
//    public TraceStatus beginSync(TraceId traceId, String msg) {
//        TraceId nextId = traceId.createNextDepthLevel();
//
//        //로그 출력
//        log.info("[{}] {}{}", nextId.getTransactionId(), addSpace(START_PREFIX, nextId.getDepthLevel()), msg);
//        return new TraceStatus(nextId, msg, LogCode.BEGIN);
//    }
//
//
//    public void completeLog(LogCode code, TraceStatus status, Exception ex) {
//        if (code.getValue() == LogCode.END.getValue()) {
//            createLog(status, null);
//        }
//        if (code.getValue() == LogCode.ERROR.getValue()) {
//            createLog(status, ex);
//        }
//    }
//
//
//    private void createLog(TraceStatus status, Exception ex) {;
//        TraceId traceId = status.getTraceId();
//        if (ex == null) {
//            log.info("[{}] {}{} End Time = {}ms",
//                    traceId.getTransactionId(),
//                    addSpace(END_PREFIX, traceId.getDepthLevel()),
//                    status.getMessage(),
//                    status.getEndTimeMs());
//        } else {
//            log.info("[{}] {}{} End Time={}ms ex={}", traceId.getTransactionId(), addSpace(EX_PREFIX, traceId.getDepthLevel()), status.getMessage(), status.getEndTimeMs(), ex.toString());
//        }
//    }
//
//
//    private static String addSpace(String prefix, int level) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < level; i++) {
//            sb.append((i == level - 1) ? "|" + prefix : "|   ");
//        }
//        return sb.toString();
//    }
}



