package hello.proxy.config.v6_aop.aspect;

import hello.proxy.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect // pointcut 과 advice 로 구성된 Advisor 를 편리하게 생성하도록 지원하는 애노테이션
@RequiredArgsConstructor
public class LogTraceAspect {

    private final ProxyLogTrace logTrace;

    /**
     * execute() 에는 Advice 로직 정의
     *
     * @Around : pointcut
     */
    @Around("execution(* hello.proxy.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        String message = joinPoint.getSignature().toShortString();
        TraceStatusV2 status = null;
        Object result = null;
        try {
            status = logTrace.begin(message);
            result = joinPoint.proceed();
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw new TraceStatusExceptionV2(e, status);
        }

        return result;

    }

}
