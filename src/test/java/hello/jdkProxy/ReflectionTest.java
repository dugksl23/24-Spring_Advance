package hello.jdkProxy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

@Slf4j
public class ReflectionTest {


    @Test
    public void reflectionTest() {
        Hello hello = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String s = hello.callA();
        log.info("result = {}", s);
        //　공통 로직 1 끝

        // 공통 로직 2 시작
        log.info("start");
        String s1 = hello.callB();
        log.info("result = {}", s1);
        // 공통 로직 2 끝

    }

    @Test
    public void templateCallbackTest1() {
        CallbackHello callback = new CallbackHello();
        Template template = new Template();

        template.execute(callback::callA);
        template.execute(callback::callB);
    }

    @Test
    public void templateCallbackTest2() {
        Template template = new Template();

        template.execute(() -> {
            log.info("callA");
            return "callA";
        });
        template.execute(() -> {
            log.info("callB");
            return "callB";
        });
    }

    @Test
    public void reflectionTest2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //　클래스 정보
        Class<?> classHello = Class.forName("hello.jdkProxy.ReflectionTest$Hello");
        // 타겟 클래스
        Hello target = new Hello();

        // 메서드 정보
        Method callA = classHello.getMethod("callA");
        // invoke() 는 동적으로 리플렉션을 통해 클래스를 생성한다.
        // 매개변수로는 실행하고자 타겟 클래스를 주입하면 된다.
        Object invoke = callA.invoke(target);
        log.info("target = {}", invoke);

        Class<?> classHellob = Class.forName("hello.jdkProxy.ReflectionTest$Hello");
        Method callB = classHellob.getMethod("callB");
        Object invoke1 = callB.invoke(target);
        log.info("target = {}", invoke1);

    }

    @Test
    public void reflectionTest3() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //　클래스 정보
        Class<?> classHello = Class.forName("hello.jdkProxy.ReflectionTest$Hello");
        // 타겟 클래스
        Hello target = new Hello();

        // callA 정보
        Method callA = classHello.getMethod("callA");
        dynamicCall(target, callA);

        // callB 정보
        Method callB = classHello.getMethod("callB");
        dynamicCall(target, callB);

    }

    private void dynamicCall(Object object, Method method) throws InvocationTargetException, IllegalAccessException {
        log.info("dynamic start");
        // 함수를 추상화했다. 추상화란, 복잡한 데이터, 구조, 시스템 등의 복잡도를 낮추고, 핵심만을 가려내 인터페이스 등으로 만드는 것,
        Object invoke = method.invoke(object);
        log.info("dynamic result = {}", invoke);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call A");
            return "b";
        }
    }

    @Slf4j
    static class CallbackHello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call A");
            return "b";
        }
    }


    @Slf4j
    static class Template<T> {

        public <T> T execute(Supplier<T> methodCall) {
            // 공통 로직 시작
            log.info("start");
            T s = methodCall.get();
            log.info("execute call = {}", s);
            //　공통 로직 끝
            return s;
        }
    }


}
