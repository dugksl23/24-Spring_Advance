package hello.jdkProxy.code;

import hello.proxy.pureProxy.proxy.concreateProxy.TimeProxy;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.MediaSize;
import java.lang.reflect.InvocationHandler;
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
        // 1 : 프록시 대상 메타 정보, 2: 프록시 대상 인터페이스, 3. 프록시 대상 구체(핵심 비지니스 로직/변하는 로직)
        // getClassLoader();
        //  클래스에 정의된 메서드로, 특정 클래스의 클래스 로더를 반환합니다.
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

        // 1. 핵심 비지니스 로직
        AInterface target = new BImpl();
        // 2. 공통 부가 기능 수행하는 프록시 인터페이스
        //    프록시는 구체클래스와 의존관계를 맺고 직접 호출한다.
        //    따라서 구체 클래스별 프록시 인터페이스 생성해야 한다.

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // 3. 동적 프록시 클래스를 만들어주는 로직
        //    현재는 하기 로직 하나로 인터페이스 하나로만 대체가 된다.
        //  JDK 동적 프록시는 인터페이스 기반으로 프록시 생성한다.
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        String name = proxy.call("yohan");
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

}


