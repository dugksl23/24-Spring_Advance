package hello.proxy.config.v1_proxy;

import hello.proxy.app.V1.*;
import hello.proxy.trace.ProxyLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV3Config {

    //@RequiredArgsConstructor 에 의해서
    // 생성자 주입방식을 통해, 컴파일 시점에 모든 의존관계를 완성해야 하기에
    // 최초 의존관계 설정 이후에 별도 의존관계 설정은 필요없다.


    @Bean
    public OrderControllerV1 getOrderControllerV1(ProxyLogTrace proxyLogTrace) {
        return new OrderControllerV1Proxy(new OrderControllerV1Impl(getOrderServiceV1(proxyLogTrace)), proxyLogTrace);
    }


    @Bean
    public OrderServiceV1 getOrderServiceV1(ProxyLogTrace proxyLogTrace) {
        return new OrderServiceV1Proxy(new OrderServiceV1Impl(getOrderRepositoryV1((proxyLogTrace))), proxyLogTrace);
    }

    @Bean
    public OrderRepositoryV1 getOrderRepositoryV1(ProxyLogTrace proxyLogTrace) {
        return new OrderRepositoryV1Proxy(new OrderRepositoryV1Impl(), proxyLogTrace);
    }
}
