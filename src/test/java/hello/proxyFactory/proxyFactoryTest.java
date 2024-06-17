package hello.proxyFactory;

import hello.common.ConcreteService;
import hello.common.ServiceImpl;
import hello.common.ServiceInterface;
import hello.jdkProxy.code.AImpl;
import hello.proxyFactory.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class proxyFactoryTest {


    @Test
    @DisplayName("인터페이스가 있을 경우 JDK 동적 프록시 사용")
    public void interfaceFactoryTest(){

        ServiceInterface serviceInterface = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(serviceInterface);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target class : {}", proxy.getClass());
        proxy.save();
        proxy.find();

        //ProxyFactory 를 통해 생성된 프록시일 경우에만 확인 가능 : AopUtils.isAopProxy(proxy)
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();

    }


    @Test
    @DisplayName("구체 클래스 있을 경우 CGLIB 사용")
    public void classFactoryTest(){

        ConcreteService service = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("target class : {}", proxy.getClass());
        proxy.call();

        //ProxyFactory 를 통해 생성된 프록시일 경우에만 확인 가능 : AopUtils.isAopProxy(proxy)
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();

    }

    @Test
    @DisplayName("proxyTargetClass 옵션 사용하여, 인터페이스가 있지만 CGLIB 사용하여 프록시 객체 생성")
    public void proxyTargetClass(){

        ServiceInterface serviceInterface = new ServiceImpl();
        //　기존 설정대로라면 JDK 동적 프록시를 통한 프록시 생성
        ProxyFactory proxyFactory = new ProxyFactory(serviceInterface);
        proxyFactory.addAdvice(new TimeAdvice());
        // CGLIB 는 인터페이스 기반이 아닌 클래스 상속하여 프록시 생성한다.
        // 마찬가지로 아래 옵션을 통해서 인터페이스가 아닌, 클래스 기반 상속을 통해 프록시 생성
        // ex) new ServiceImpl();
        proxyFactory.setProxyTargetClass(true);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target class : {}", proxy.getClass());
        proxy.save();
        proxy.find();

        //ProxyFactory 를 통해 생성된 프록시일 경우에만 확인 가능 : AopUtils.isAopProxy(proxy)
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();

    }

}
