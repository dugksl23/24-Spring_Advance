package hello.proxy.config.v3_proxyFactory;


import hello.proxy.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

    private final ProxyLogTrace logTrace;


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String methodName = invocation.getMethod().getName();
        String className = Objects.requireNonNull(invocation.getThis()).getClass().getName();
        String classMethodName = className + "." + methodName + "()";
        TraceStatusV2 status = null;
        Object result = null;
        try {
            status = logTrace.begin(classMethodName);
            result = invocation.proceed();
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.end(status);
            throw new TraceStatusExceptionV2(e, status);
        }

        return result;
    }
}
