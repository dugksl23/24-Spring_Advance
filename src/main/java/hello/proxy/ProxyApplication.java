package hello.proxy;

import hello.proxy.config.v1_proxy.AppV1Config;
import hello.proxy.config.v1_proxy.AppV2Config;
import hello.proxy.config.v5_autoProxy.AutoProxyConfig;
import hello.proxy.config.v6_aop.AopConfig;
import hello.proxy.exception.ExceptionConfig;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.ThreadLocalLogTraceV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV3Config.class, AppV2Config.class})
//@Import({AppV4Config.class})
//@Import({AppV2Config.class, DynamicProxyFilterConfig.class})
//@Import({ProxyFactoryConfigV1.class, AppV2Config.class})
//@Import({AppV3Config.class, AppV2Config.class})
//@Import({BeanPostProcessorConfig.class, AppV1Config.class, AppV2Config.class})
//@Import({AutoProxyConfig.class, AppV1Config.class, AppV2Config.class, ExceptionConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
@Import({AopConfig.class})
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public ProxyLogTrace getLogTrace() {
        return new ThreadLocalLogTraceV2();
    }
}
