package hello.springAdvanced.trace.config;

import hello.springAdvanced.trace.domain.FieldTrace;
import hello.springAdvanced.trace.domain.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldTrace();
    }
}
