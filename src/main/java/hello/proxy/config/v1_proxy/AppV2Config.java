package hello.proxy.config.v1_proxy;

import hello.proxy.app.V2.OrderControllerV2;
import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.app.V2.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV2Config {

//    @Bean
//    public OrderControllerV2 getOrderControllerV2() {
//        return new OrderControllerV2(getOrderServiceV2());
//    }

    @Bean
    public OrderServiceV2 getOrderServiceV2() {
        return new OrderServiceV2(getOrderRepositoryV2());
    }

    @Bean
    public OrderRepositoryV2 getOrderRepositoryV2() {
        return new OrderRepositoryV2();
    }
}
