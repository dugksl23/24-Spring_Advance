package hello.proxy.pureProxy.proxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CasheProxySubject implements Subject {

    private final RealSubject target;
    private String cacheValue;

    @Override
    public String process() throws InterruptedException {

        log.info("프록시 객체 호출");
        if (cacheValue == null) {
            cacheValue = target.process();
        }

        return cacheValue;

    }
}