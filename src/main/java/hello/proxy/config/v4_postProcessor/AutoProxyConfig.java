package hello.proxy.config.v4_postProcessor;

import hello.proxy.config.v3_proxyFactory.LogTraceAdvice;
import hello.proxy.trace.ProxyLogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyConfig {


    //    @Bean
    public Advisor getLogTraceAdvisor(ProxyLogTrace logTrace) {
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(nameMatchPointcut(), advice);
    }

    private Pointcut nameMatchPointcut() {

        final String[] PATTERN = new String[]{"request*", "order*", "save*", "noLog*"};
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        return pointcut;

    }

    //    @Bean
    public Advisor getTraceAdvisor2(ProxyLogTrace logTrace) {

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        String expression = "execution(* hello.proxy.app..*(..))";
        // .*(..) : 어떤 파라미터가 들어와도 상관없다는 표현식
        // 해당 경로에 위치해야지만 프록시가 적용된다.
        pointcut.setExpression(expression);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public Advisor getTraceAdvisor3(ProxyLogTrace logTrace) {

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        String expression = "execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))";
        // .*(..) : 어떤 파라미터가 들어와도 상관없다는 표현식
        // 해당 경로에 위치해야지만 프록시가 적용된다.
        pointcut.setExpression(expression);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }


}
