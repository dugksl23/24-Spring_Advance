package hello.proxy.config.v3_proxyFactory;


import hello.proxy.app.V2.OrderControllerV2;
import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.app.V2.OrderServiceV2;
import hello.proxy.trace.ProxyLogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 getOrderControllerV2(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderControllerV2(getOrderServiceV2(logTrace)));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvisor(logTrace)));
        OrderControllerV2 proxy = (OrderControllerV2) proxyFactory.getProxy();
        log.info("proxy target : {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 getOrderServiceV2(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderServiceV2(getOrderRepositoryV2(logTrace)));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvisor(logTrace)));
        OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();
        log.info("proxy target : {}", proxy.getClass());
        return proxy;

    }

    @Bean
    public OrderRepositoryV2 getOrderRepositoryV2(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderRepositoryV2());
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvisor(logTrace)));

        OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();
        log.info("proxy target : {}", proxy.getClass());
        return proxy;
    }

    private Pointcut nameMatchPointcut() {

        final String[] PATTERN = new String[]{"request*", "order*", "save*"};

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        return pointcut;

    }

}
