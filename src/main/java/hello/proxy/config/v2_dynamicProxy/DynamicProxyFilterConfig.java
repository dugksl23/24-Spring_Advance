package hello.proxy.config.v2_dynamicProxy;

import hello.proxy.app.V1.*;
import hello.proxy.trace.ProxyLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private final String[] methodPattern = new String[]{"save*", "request*", "order*"};

    @Bean
    public OrderControllerV1 getOrderControllerV1(ProxyLogTrace logTrace) {

        OrderControllerV1 controllerV1 = new OrderControllerV1Impl(getOrderServiceV1(logTrace));
        // JDK 동적 프록시는 타겟의 인터페이스를 기반으로 프록시를 동적 생성한다.
        // LogTraceBasicHandler(Template) 들의 target 각각 config 를 통해 선조립한다.
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(controllerV1, logTrace, methodPattern));

        return proxy;
    }

    @Bean
    public OrderServiceV1 getOrderServiceV1(ProxyLogTrace logTrace) {
        OrderServiceV1 serviceV1 = new OrderServiceV1Impl(getOrderRepositoryV1(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(serviceV1, logTrace, methodPattern));
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 getOrderRepositoryV1(ProxyLogTrace logTrace) {
        OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepositoryV1, logTrace, methodPattern));
        return proxy;
    }


}
