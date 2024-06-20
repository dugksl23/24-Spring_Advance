package hello.proxy.exception;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {

    @Bean
    public LogExceptionControllerAdvice logExceptionControllerAdvice() {
        return new LogExceptionControllerAdvice();
    }

}
