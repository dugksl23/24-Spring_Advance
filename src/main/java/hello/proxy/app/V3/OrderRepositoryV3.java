package hello.proxy.app.V3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private Map<Long, String> repository = new ConcurrentHashMap<>();
    private AtomicLong ItemSeq = new AtomicLong();
    private final ApplicationContext applicationContext;

    public void save(String itemId) {

        if (itemId.contains("ex")) {
            throw new IllegalArgumentException("EX 발생");
        }

        repository.put(ItemSeq.getAndIncrement(), itemId);


    }
}
