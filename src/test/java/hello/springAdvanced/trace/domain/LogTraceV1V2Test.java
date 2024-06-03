package hello.springAdvanced.trace.domain;

import hello.springAdvanced.app.v2.OrderServiceV2;
import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogTraceV1V2Test {

    @Autowired
    private OrderServiceV2 orderServiceV2;

    private static final Logger log = LoggerFactory.getLogger(LogTraceV1V2Test.class);
    @Autowired
    private LogTraceV1 logTrace;

    @Test
    void helloTraceV2_Begin_End() throws Exception {
        LogTraceV2 logTraceV2 = new LogTraceV2();
        TraceStatus beginStatus = logTraceV2.begin("hello1 ");
        TraceStatus syncStatus = logTraceV2.beginSync(beginStatus.getTraceId(), "hello2 ");
        logTraceV2.completeLog(LogCode.END, syncStatus, null);
        logTraceV2.completeLog(LogCode.END, beginStatus, null);
    }

    @Test
    void helloTraceV2_Exception() throws Exception {
        LogTraceV2 traceV2 = new LogTraceV2();
        TraceStatus beginStatus = traceV2.begin("hello1 ");
        TraceStatus syncStatus = traceV2.beginSync(beginStatus.getTraceId(), "hello2 ");
        traceV2.completeLog(LogCode.END, syncStatus, null);
        traceV2.completeLog(LogCode.ERROR, beginStatus, new IllegalArgumentException("exception 발생"));
    }

}