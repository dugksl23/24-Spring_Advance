package hello.proxy.pureProxy.proxy.concreateProxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
// 데코레이터 기능을 하는 프록시
public class TimeProxy extends Concrete {

    private Concrete concrete; // TimeProxy 는 concrete 의 자식 클래스.
                                // 다형성에 의해서 부모는 자식까지 품을 수 있다.

    @Override
    public void operation() {

        log.info("Time decorator 실행");
        long startTime = System.currentTimeMillis();
        concrete.operation();
        long endTime = System.currentTimeMillis();
        log.info("duration: {} ms", endTime - startTime);
    }
}
