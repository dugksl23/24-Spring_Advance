package hello.springAdvanced.app.v0.repository;


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
public class OrderRepositoryV0 {

    public Map<Integer, Object> repository = new ConcurrentHashMap<>();
    public AtomicInteger id = new AtomicInteger(0);

    public void save(String itemId) {

        if (itemId.equals("ex") || StringUtils.isBlank(itemId)) {
            throw new RuntimeException("예외 발생");
        }

        int andIncrement = id.getAndIncrement();
        log.info("created Id : {}", andIncrement);
        repository.put(andIncrement, itemId);
        sleep(1000);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
