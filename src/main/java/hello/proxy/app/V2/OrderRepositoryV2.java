package hello.proxy.app.V2;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private Map<Long, String> repository = new ConcurrentHashMap<>();
    private AtomicLong ItemSeq = new AtomicLong();

    public void save(String itemId) {

        if (itemId.contains("ex")) {
            throw new IllegalArgumentException("EX 발생");
        }

        repository.put(ItemSeq.getAndIncrement(), itemId);


    }
}
