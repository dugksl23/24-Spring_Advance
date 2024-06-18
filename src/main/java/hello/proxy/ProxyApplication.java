package hello.proxy;

import hello.proxy.config.v1_proxy.AppV2Config;
import hello.proxy.config.v3_proxyFactory.ProxyFactoryConfigV1;
import hello.proxy.config.v3_proxyFactory.ProxyFactoryConfigV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.ThreadLocalLogTraceV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV3Config.class, AppV2Config.class})
//@Import({AppV4Config.class})
//@Import({AppV2Config.class, DynamicProxyFilterConfig.class})
@Import({ProxyFactoryConfigV1.class, AppV2Config.class})
//@Import({AppV3Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app.V2")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public ProxyLogTrace getLogTrace() {
        return new ThreadLocalLogTraceV2();
    }
}
