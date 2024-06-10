package hello.proxy.app.V2;

import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@RequiredArgsConstructor
public class OrderRepositoryV2ClassProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 repositoryV2;
    private final ProxyLogTrace logTrace;

    public void save(String itemId) {

        TraceStatusV2 statusV2 = logTrace.begin(this.getClass().getSimpleName());
        repositoryV2.save(itemId);
        logTrace.end(statusV2);

    }
}
