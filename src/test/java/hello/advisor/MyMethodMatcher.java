package hello.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodMatcher;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.Method;


@Slf4j
public class MyMethodMatcher implements MethodMatcher {

    private final String[] PATTERN = new String[]{"save*", "request*", "order*"};

    @Override
    public boolean matches(Method method, Class<?> targetClass) {

        String name = method.getName();
        Boolean result = true;
        log.info("method name is {}", name);
        log.info("method target is {}", targetClass.getName());
        if (PatternMatchUtils.simpleMatch(PATTERN, name)) {
            return result;
        }
        result = false;
        log.info("method match   result is {}", result);
        return result;
    }


    // 하기 두개의 메서드는 무시
    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}
