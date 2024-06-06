package hello.springAdvanced.trace.config;

import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.domain.ThreadLocalLogTrace;
import hello.springAdvanced.trace.strategy.LogTraceTemplateImpl;
import hello.springAdvanced.trace.strategy.callback.LogTraceTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public LogTraceTemplate logTraceTemplate(LogTrace logTrace) {
        return new LogTraceTemplateImpl(logTrace);

    }
}
