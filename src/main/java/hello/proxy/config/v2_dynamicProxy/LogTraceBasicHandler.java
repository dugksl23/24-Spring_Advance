package hello.proxy.config.v2_dynamicProxy;

import hello.proxy.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final ProxyLogTrace logTrace;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

        // method 메타 정보에는 클래스 및 메서드 이름도 함께 넘어온다.
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        TraceStatusV2 traceStatus = null;
        Object result = null;
        try {
            traceStatus = logTrace.begin(className + "." + methodName + "()");

            // 실제 타겟 로직 호출
            result = method.invoke(target, args);

            logTrace.end(traceStatus);
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw new TraceStatusExceptionV2(e,traceStatus);
        }

        return result;
    }
}
