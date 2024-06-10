package hello.proxy;

import hello.proxy.config.AppV2Config;
import hello.proxy.config.AppV3Config;
import hello.proxy.config.AppV4Config;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.ThreadLocalLogTraceV2;
import hello.springAdvanced.trace.config.LogTraceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV3Config.class, AppV2Config.class})
@Import({AppV4Config.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public ProxyLogTrace getLogTrace() {
        return new ThreadLocalLogTraceV2();
    }
}
