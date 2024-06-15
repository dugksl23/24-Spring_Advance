package hello.proxy.config.v2_dynamicProxy;

import hello.proxy.app.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final ProxyLogTrace logTrace;
    private final String[] methodPattern; // 외부 Config 에서 주입

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

        // method 메타 정보에는 클래스 및 메서드 이름도 함께 넘어온다.
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        // 특정 Method Name 의 Pattern 아니라면,바로 동적 호출.
        if (!PatternMatchUtils.simpleMatch(methodPattern, methodName)) {
            method.invoke(target, args);
        }

        TraceStatusV2 traceStatus = null;
        Object result = null;
        try {
            traceStatus = logTrace.begin(className + "." + methodName + "()");

            // 실제 타겟 로직 호출
            result = method.invoke(target, args);

            logTrace.end(traceStatus);
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw new TraceStatusExceptionV2(e, traceStatus);
        }

        return result;
    }
}
