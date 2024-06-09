package hello.proxy.app.V3;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class OrderRepositoryV3 {

    private Map<Long, String> repository = new ConcurrentHashMap<>();
    private AtomicLong ItemSeq = new AtomicLong();

    public void save(String itemId) {

        if (itemId.contains("ex")) {
            throw new IllegalArgumentException("EX 발생");
        }

        repository.put(ItemSeq.getAndIncrement(), itemId);


    }
}
