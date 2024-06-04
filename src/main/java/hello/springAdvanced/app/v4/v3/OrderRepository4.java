package hello.springAdvanced.app.v4.v3;


import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.template.AbstractTemplate;
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
public class OrderRepository4 {

    public Map<Integer, Object> repository = new ConcurrentHashMap<>();
    public AtomicInteger id = new AtomicInteger(0);
    private final LogTrace logTrace;

    public void save(TraceStatus status, String itemId) throws Exception {

        try {
            status = logTrace.begin("OrderRepository");

            if (itemId.equals("ex") || StringUtils.isBlank(itemId)) {
                throw new IllegalArgumentException("예외 발생");
            }

            int andIncrement = id.getAndIncrement();
            log.info("created Id : {}", andIncrement);
            repository.put(andIncrement, itemId);
            sleep(1000);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e; //예외는 꼭 다시 던져야 한다.
        }
    }

    public String save(String itemId) throws Exception {

        AbstractTemplate<String> template = new AbstractTemplate<>(logTrace) {

            @Override
            protected String call() {

                if (itemId.equals("ex") || StringUtils.isBlank(itemId)) {
                    throw new IllegalArgumentException("예외 발생");
                }

                int andIncrement = id.getAndIncrement();
                log.info("created Id : {}", andIncrement);
                repository.put(andIncrement, itemId);
                sleep(1000);

                return "success";
            }
        };

        return template.execute("orderRepository");


    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
