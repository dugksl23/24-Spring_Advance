package hello.proxy.config.v3_proxyFactory;


import hello.proxy.app.V1.*;
import hello.proxy.trace.ProxyLogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderControllerV1 getOrderControllerV1(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderControllerV1Impl(getOrderServiceV1(logTrace)));
        proxyFactory.addAdvisor(getLogTraceAdvice(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1) proxyFactory.getProxy();
        log.info("proxy target : {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 getOrderServiceV1(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderServiceV1Impl(getOrderRepositoryV1(logTrace)));
        proxyFactory.addAdvisor(getLogTraceAdvice(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1) proxyFactory.getProxy();
        log.info("proxy target : {}", proxy.getClass());
        return proxy;

    }

    @Bean
    public OrderRepositoryV1 getOrderRepositoryV1(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderRepositoryV1Impl());
        proxyFactory.addAdvisor(getLogTraceAdvice(logTrace));

        OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();
        log.info("proxy target : {}", proxy.getClass());
        return proxy;
    }

    private Pointcut nameMatchPointcut() {

        final String[] PATTERN = new String[]{"request*", "order*", "save*"};

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        return pointcut;

    }

    private Advisor getLogTraceAdvice(ProxyLogTrace logTrace) {
        return new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvice(logTrace));
    }

}
