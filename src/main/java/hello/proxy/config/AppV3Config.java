package hello.proxy.config;

import hello.proxy.app.V1.*;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.ThreadLocalLogTraceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV3Config {

    //@RequiredArgsConstructor 에 의해서
    // 생성자 주입방식을 통해, 컴파일 시점에 모든 의존관계를 완성해야 하기에
    // 최초 의존관계 설정 이후에 별도 의존관계 설정은 필요없다.

    @Bean
    public ProxyLogTrace getLogTrace() {
        return new ThreadLocalLogTraceV2();
    }

    @Bean
    public OrderControllerV1 getOrderControllerV1() {
        return new OrderControllerV1Proxy(
                new OrderControllerV1Impl(new OrderServiceV1Proxy(new OrderServiceV1Impl(new OrderRepositoryV1Proxy(new OrderRepositoryV1Impl(), getLogTrace())), getLogTrace())), getLogTrace());

    }


//    @Bean
//    public OrderServiceV1 getOrderServiceV1() {
//        return new OrderServiceV1Proxy(new OrderServiceV1Impl(getOrderRepositoryV1()),getLogTrace());
//    }
//
//    @Bean
//    public OrderRepositoryV1 getOrderRepositoryV1() {
//        return new OrderRepositoryV1Proxy(new OrderRepositoryV1Impl(), getLogTrace());
//    }
}
