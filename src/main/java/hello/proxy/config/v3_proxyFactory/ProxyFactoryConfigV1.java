package hello.proxy.config.v3_proxyFactory;


import hello.proxy.app.V1.*;
import hello.proxy.config.v2_dynamicProxy.LogTraceBasicHandler;
import hello.proxy.trace.ProxyLogTrace;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.Proxy;

@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderControllerV1 getOrderControllerV1(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderControllerV1Impl(getOrderServiceV1(logTrace)));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvisor(logTrace)));

        return (OrderControllerV1) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV1 getOrderServiceV1(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderServiceV1Impl(getOrderRepositoryV1(logTrace)));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvisor(logTrace)));

        return (OrderServiceV1) proxyFactory.getProxy();

    }

    @Bean
    public OrderRepositoryV1 getOrderRepositoryV1(ProxyLogTrace logTrace) {

        ProxyFactory proxyFactory = new ProxyFactory(new OrderRepositoryV1Impl());
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvisor(logTrace)));

        return (OrderRepositoryV1) proxyFactory.getProxy();
    }

    private Pointcut nameMatchPointcut() {

        final String[] PATTERN = new String[]{"request*", "order*", "save*"};

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        return pointcut;

    }

}
