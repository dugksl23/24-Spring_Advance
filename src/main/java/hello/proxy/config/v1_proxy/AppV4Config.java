package hello.proxy.config.v1_proxy;

import hello.proxy.app.V2.*;
import hello.proxy.trace.ProxyLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppV4Config {

    @Bean
    @Primary
    public OrderControllerV2 getOrderControllerV2(ProxyLogTrace logTrace) {
        OrderControllerV2 orderControllerV2 = new OrderControllerV2(getOrderServiceV2(logTrace));
        return new OrderControllerV2ClassProxy(orderControllerV2, logTrace);
    }

    @Bean
    public OrderServiceV2 getOrderServiceV2(ProxyLogTrace logTrace) {
        return new OrderServiceV2ClassProxy(new OrderServiceV2(getOrderRepositoryV2(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV2 getOrderRepositoryV2(ProxyLogTrace logTrace) {
        return new OrderRepositoryV2ClassProxy(new OrderRepositoryV2(), logTrace);
    }


}

