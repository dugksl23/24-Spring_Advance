package hello.springAdvanced.trace.domain;

import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;


class LogTraceV1Test {


    @Test
    void helloTraceV1_Begin_End() {
        LogTraceV1 trace = new LogTraceV1();
        TraceStatus logTraceBegin = trace.begin(LogCode.BEGIN.getValue());
        trace.completeLog(LogCode.END, logTraceBegin, null);
    }

    @Test
    void helloTraceV1_Exception() throws InterruptedException {
        LogTraceV1 trace = new LogTraceV1();
        TraceStatus logTraceBegin = trace.begin(LogCode.BEGIN.getValue());
        trace.completeLog(LogCode.ERROR, logTraceBegin, new Exception("exception occured"));
    }

}