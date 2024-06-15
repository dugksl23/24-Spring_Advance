package hello.cglib;

import hello.cglib.code.TimeMethodInterceptor;
import hello.common.ConcreteService;
import hello.common.ServiceImpl;
import hello.common.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;


@Slf4j
public class CglibTest {

    @Test
    public void cgLibTest() {

        ConcreteService service = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        // 동적 프록시 생성 코드 시작

        // 구체 클래스 지정하여 상속받는 프록시 생성함.
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(service));
        // 프록시 객체는 구체 클래스를 상속했기에 부모 클래스(Target)으로 받을 수 있다.
        ConcreteService proxy = (ConcreteService) enhancer.create();

        proxy.call();
        log.info("target Class : {}", service .getClass());
        log.info("proxy Class : {}", proxy.getClass());

    }

    @Test
    public void cgLibTest2() {

        ServiceInterface service = new ServiceImpl();

        Enhancer enhancer = new Enhancer();
        // 동적 프록시 생성 코드 시작

        // 구체 클래스 지정하여 상속받는 프록시 생성함.
        enhancer.setSuperclass(ServiceInterface.class);
        enhancer.setCallback(new TimeMethodInterceptor(service));
        // 프록시 객체는 구체 클래스를 상속했기에 부모 클래스(Target)으로 받을 수 있다.
        ServiceInterface proxy = (ServiceInterface) enhancer.create();

        proxy.find();
        proxy.save();
        log.info("target Class : {}", service .getClass());
        log.info("proxy Class : {}", proxy.getClass());

    }

}
