package hello.proxy.app.V1;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

    private Map<Long, String> repository = new ConcurrentHashMap<>();
    private AtomicLong ItemSeq = new AtomicLong();

    @Override
    public void save(String itemId) {

        if (itemId.contains("ex")) {
            throw new IllegalArgumentException("EX 발생");
        }

        repository.put(ItemSeq.getAndIncrement(), itemId);


    }
}
