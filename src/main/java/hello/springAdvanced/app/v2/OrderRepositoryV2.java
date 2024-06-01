package hello.springAdvanced.app.v2;


import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.domain.LogTraceV2;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryV2 {

    public Map<Integer, Object> repository = new ConcurrentHashMap<>();
    public AtomicInteger id = new AtomicInteger(0);
    private final LogTraceV2 logTrace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository " + LogCode.BEGIN.getValue());

            if (itemId.equals("ex") || StringUtils.isBlank(itemId)) {
                throw new IllegalArgumentException("예외 발생");
            }

            int andIncrement = id.getAndIncrement();
            log.info("created Id : {}", andIncrement);
            repository.put(andIncrement, itemId);
            sleep(1000);

        } catch (Exception e) {
            logTrace.completeLog(LogCode.ERROR, status, e);
            throw e; //예외는 꼭 다시 던져야 한다.
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
