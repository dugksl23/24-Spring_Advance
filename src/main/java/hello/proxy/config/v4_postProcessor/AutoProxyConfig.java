package hello.proxy.config.v4_postProcessor;

import hello.proxy.config.v3_proxyFactory.LogTraceAdvice;
import hello.proxy.trace.ProxyLogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyConfig {


    @Bean
    public Advisor getLogTraceAdvisor(ProxyLogTrace logTrace) {
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(nameMatchPointcut(), advice);
    }

    private Pointcut nameMatchPointcut() {

        final String[] PATTERN = new String[]{"request*", "order*", "save*"};
//        final String[] PATTERN = new String[]{};
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        return pointcut;

    }


}
