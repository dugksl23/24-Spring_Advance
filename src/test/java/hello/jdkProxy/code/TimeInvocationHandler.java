package hello.jdkProxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@RequiredArgsConstructor
@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    // 프록시는 호출할 구현체의 Target 을 필드로 갖는다.
    private final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("TimeProxy 실행");
        long start = System.currentTimeMillis();
        // 메서드의 파라미터까지 함께 받는다.
        // 메서드 호출
        Object invoke = method.invoke(target, args); // call() 을 넘겨준다.

        long end = System.currentTimeMillis();
        long result = end - start;
        log.info("TimeProxy 종료 및 ResultTime : {}", result);

        return invoke;
    }
}
