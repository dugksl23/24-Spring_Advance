package hello.proxy.pureProxy.proxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InheritedCashProxyRealSubject extends RealSubject {

    private String cashValue;

    @Override
    public String process() throws InterruptedException {

        log.info("프록시 객체 호출");
        if (cashValue == null) {
            cashValue = super.process();
        }
        return cashValue;
    }
}
