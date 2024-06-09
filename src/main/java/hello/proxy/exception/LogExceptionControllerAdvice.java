package hello.proxy.exception;


import hello.springAdvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class LogExceptionControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public void exception(Exception e) {
        e.printStackTrace();
    }

    @ExceptionHandler(value = TraceStatusException.class)
    public void handleTraceStatusException(TraceStatusException e) {
        TraceStatus status = e.getStatus();
        log.info("[{}] Exception: {}", status.getTraceId(), e.getMessage());
        e.printStackTrace();
    }


}
