package hello.proxy.config.v6_aop;

import hello.proxy.config.v1_proxy.AppV1Config;
import hello.proxy.config.v1_proxy.AppV2Config;
import hello.proxy.config.v6_aop.aspect.LogTraceAspect;
import hello.proxy.exception.ExceptionConfig;
import hello.proxy.trace.ProxyLogTrace;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class, ExceptionConfig.class})
public class AopConfig {

    @Bean
    public LogTraceAspect LogTraceAspect(ProxyLogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }

}
