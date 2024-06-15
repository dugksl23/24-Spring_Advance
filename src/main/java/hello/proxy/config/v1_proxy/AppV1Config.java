package hello.proxy.config.v1_proxy;

import hello.proxy.app.V1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 getOrderControllerV1() {
        return new OrderControllerV1Impl(getOrderServiceV1());
    }

    @Bean
    public OrderServiceV1 getOrderServiceV1() {
        return new OrderServiceV1Impl(getOrderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 getOrderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }
}
