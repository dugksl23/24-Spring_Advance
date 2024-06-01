package hello.springAdvanced.trace.helloTrace;

import hello.springAdvanced.trace.TraceId;
import hello.springAdvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class HelloTraceV1ImplTest {


    @Test
    void helloTraceV1_Begin_End(){
        HelloTraceV1Impl trace = new HelloTraceV1Impl();
        TraceStatus logTraceBegin = trace.begin("log trace begin");
        trace.end(logTraceBegin);
    }

    @Test
    void helloTraceV1_Exception(){
        HelloTraceV1Impl trace = new HelloTraceV1Impl();
        TraceStatus logTraceBegin = trace.begin("log trace begin");;
        trace.exception(logTraceBegin, new Exception("exception"));
    }

}