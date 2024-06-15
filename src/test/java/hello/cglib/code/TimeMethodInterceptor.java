package hello.cglib.code;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        log.info("TimeMethodInterceptor start");
        long startTime = System.currentTimeMillis();

        // target 호출
        Object result = proxy.invoke(target, args);

        long endTime = System.currentTimeMillis();
        log.info("TimeMethodInterceptor end time: {} ms", endTime - startTime);
        return result;

    }
}
