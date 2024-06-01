package hello.springAdvanced.trace.domain;

import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class LogTraceV2Test {

    private static final Logger log = LoggerFactory.getLogger(LogTraceV2Test.class);

    @Test
    void helloTraceV2_Begin_End() {
        LogTraceV2 logTraceV2 = new LogTraceV2();
        TraceStatus beginStatus = logTraceV2.begin("hello1 " + LogCode.BEGIN.getValue());
        TraceStatus syncStatus = logTraceV2.beginSync(beginStatus.getTraceId(), "hello2 " + LogCode.BEGIN.getValue());
        logTraceV2.completeLog(LogCode.END, syncStatus, null);
        logTraceV2.completeLog(LogCode.END, beginStatus, null);
    }

    @Test
    void helloTraceV2_Exception() throws InterruptedException {
        LogTraceV2 traceV2 = new LogTraceV2();
        TraceStatus beginStatus = traceV2.begin("hello1 " + LogCode.BEGIN.getValue());
        TraceStatus syncStatus = traceV2.beginSync(beginStatus.getTraceId(), "hello2 " + LogCode.BEGIN.getValue());
        traceV2.completeLog(LogCode.ERROR, syncStatus, new Exception("exception 발생"));
        traceV2.completeLog(LogCode.ERROR, beginStatus, new Exception("exception 발생"));
    }

}