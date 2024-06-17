package hello.proxyFactory.advice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
@RequiredArgsConstructor
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // CGLIB        : MethodInterceptor
        // JDK 동적 프록시 : InvocationHandler
        // ProxyFactory : MethodInterceptor

        log.info("Advice Start");
        long timeStart = System.currentTimeMillis();
        // 비지니스 로직 수행

        // invocation.proceed() 에는 이미 target class 을 찾아서 실행
        // 큰 특징으로는 타겟 클래스가 정보가 명시되지 않았다. ex) private final Object target
        // 적용할 대상 메서드를 pointCut 으로 지정했기에 proceed() 내부에 target 과, args 가 모두 포함됨.
        // 내부에 method.invoke(target, args) 로직 자동 수행
        // target class 의 메서드의 반환타입을 반환.
        Object proceed = invocation.proceed();
        long timeEnd = System.currentTimeMillis();
        log.info("Advice End");
        long timeCost = timeEnd - timeStart;
        log.info("Advice Cost: {}", timeCost);

        return proceed;
    }
}
