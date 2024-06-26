package hello.jdkProxy.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;


@Slf4j
public class JdkDynamicProxyTest {


    @Test
    public void jdkProxyTest() {

        // 1. 핵심 비지니스 로직을 수행할 구체 생성
        AInterface target = new AImpl();

        // 2.　변하지 않을 로직을 수행할 프록시 생성 및 내부 invoke() 함수에 template 으로 정의됨. (template)
        //     호출할 실제 객체와 의존하고 있기에 변하는 로직인 핵심 비지니스 로직을 수행할 구현체를 파라미터로 전달 (callback)
        TimeInvocationHandler invocationHandler = new TimeInvocationHandler(target);

        // 3. 프록시 객체 생성 로직
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, invocationHandler);
        // getClassLoader();
        //  클래스 로더는 자바 런타임 환경에서 클래스를 동적으로 로드하는 데 사용되는 객체입니다.


        // 4. 프록시 객체로 대상 객체 호출
        //   프록시 객체는 핸들러의 로직을 수행한다.
        String name = proxy.call("name");
        proxy.call2();

        log.info("name : {}", name);
        log.info("targetClass : {}", target.getClass());
        log.info("proxyClass : {}", proxy.getClass());

    }

    @Test
    public void jdkProxyTest2() {

        AInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        String name = proxy.call("yohan");
        proxy.call2();
        log.info("name : {}", name);
        log.info("targetClass : {}", target.getClass());
        log.info("proxyClass : {}", proxy.getClass());

    }

    @Test
    public void jdkProxyTest3() {

        // 1. 핵심 비지니스 로직
        ALogic aLogic = new ALogic();

        // 2. 공통 부가 기능 수행하는 프록시 인터페이스
        //    프록시는 구체클래스와 의존관계를 맺고 직접 호출한다.
        //    따라서 구체 클래스별 프록시 인터페이스 생성해야 한다.
        TimeProxyInterface timeProxy = new TimeProxyImpl();
        String name = "Yohan";
        String executeBySupply = timeProxy.executeBySupply(() -> aLogic.save(name));
        String s = timeProxy.executeByFunction((String... args) -> aLogic.save(name));
        log.info("execute : {}", executeBySupply);
        log.info("execute : {}", s);

    }

    @Test
    public void jdkProxyTest4() {

        // 1. 핵심 비지니스 로직
        ALogic aLogic = new ALogic();
        BLogic bLogic = new BLogic();

        // 2. 공통 부가 기능 수행하는 프록시 인터페이스
        //    프록시는 구체클래스와 의존관계를 맺고 직접 호출한다.
        //    따라서 구체 클래스별 프록시 인터페이스 생성해야 한다.
        TimeProxyInterface timeProxy = new TimeProxyImpl();

        // Function 사용 - String 반환
        String stringResult = timeProxy.executeByFunction((String... args) -> aLogic.save("name"));
        log.info("String Result: {}", stringResult);

        // Function 사용 - Integer 반환
        Integer integerResult = timeProxy.executeByFunction((Integer... args) -> bLogic.save(1));
        log.info("Integer Result: {}", integerResult);

    }

    @Test
    public void jdkProxyTest5() {

        // 1. 핵심 비지니스 로직
        ALogic aLogic = new ALogic();
        BLogic bLogic = new BLogic();

        // 2. 공통 부가 기능 수행하는 프록시 인터페이스
        //    프록시는 구체클래스와 의존관계를 맺고 직접 호출한다.
        //    따라서 구체 클래스별 프록시 인터페이스 생성해야 한다.
        TimeProxyInterface timeProxy = new TimeProxyImpl();

        // Function 사용 - Integer 반환
        Integer integerResult = timeProxy.executeByFunction(args -> bLogic.save((Integer) args[0], (String) args[1]), 30, "John");
        log.info("Integer Result: {}", integerResult);

    }

}


