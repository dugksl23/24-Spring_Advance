package hello.springAdvanced.trace.domain;

import hello.springAdvanced.app.v2.OrderServiceV2;
import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class ThreadLocalLogTraceTest {

    private ThreadLocalLogTrace logger = new ThreadLocalLogTrace();

    @Test
    public void begin_end_test(){
        TraceStatus hello1 = logger.begin("hello1");
        TraceStatus hello2 = logger.begin("hello2");
        logger.end(hello1);
        logger.end(hello2);

    }

    @Test
    public void begin_exception_test(){
        TraceStatus hello1 = logger.begin("hello1");
        TraceStatus hello2 = logger.begin("hello2");
        logger.end(hello1);
        logger.exception(hello2, new RuntimeException("test"));
    }
}