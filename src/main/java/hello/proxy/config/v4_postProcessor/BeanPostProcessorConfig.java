package hello.proxy.config.v4_postProcessor;


import hello.proxy.app.V2.OrderControllerV2;
import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.app.V2.OrderServiceV2;
import hello.proxy.config.v1_proxy.AppV1Config;
import hello.proxy.config.v1_proxy.AppV2Config;
import hello.proxy.config.v3_proxyFactory.LogTraceAdvice;
import hello.proxy.config.v4_postProcessor.postProcessor.PackageLogTracePostProcessor;
import hello.proxy.trace.ProxyLogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor packageLogTracePostProcessor(ProxyLogTrace logTrace) {
        String packageName = "hello.proxy.app";
        Advisor advisor = getLogTraceAdvisor(logTrace);
        return new PackageLogTracePostProcessor(packageName, advisor);
    }


    private Pointcut nameMatchPointcut() {

        final String[] PATTERN = new String[]{"request*", "order*", "save*"};

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        return pointcut;

    }

    private Advisor getLogTraceAdvisor(ProxyLogTrace logTrace) {
        return new DefaultPointcutAdvisor(nameMatchPointcut(), new LogTraceAdvice(logTrace));
    }

}
