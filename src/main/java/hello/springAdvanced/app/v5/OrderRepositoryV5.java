package hello.springAdvanced.app.v5;


import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.strategy.LogTraceTemplateImpl;
import hello.springAdvanced.trace.strategy.callback.TraceCallback;
import hello.springAdvanced.trace.template.AbstractTemplate;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Slf4j
public class OrderRepositoryV5 {

    public Map<Integer, Object> repository = new ConcurrentHashMap<>();
    public AtomicInteger id = new AtomicInteger(0);
    private final LogTraceTemplateImpl template;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.template = new LogTraceTemplateImpl(logTrace);
    }

    public void save(String itemId) throws TraceStatusException {

        template.execute(this.getClass().getName(), () -> {
            if (itemId.equals("ex") || StringUtils.isBlank(itemId)) {
                throw new IllegalArgumentException("예외 발생");
            }

            int andIncrement = id.getAndIncrement();
            log.info("created Id : {}", andIncrement);
            repository.put(andIncrement, itemId);
            sleep(1000);
            return null;
        });

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
