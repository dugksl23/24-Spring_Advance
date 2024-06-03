package hello.springAdvanced.exception.advice;


import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public void exception(Exception e) {
        log.error("error msg : {}", e.getMessage());
    }

    @ExceptionHandler(TraceStatusException.class)
    public String handleTraceStatusException(TraceStatusException e) {
        TraceStatus status = e.getTraceStatus();
        log.error("Exception caught: {}, TraceStatus: {}", e.getMessage(), status.toString());
        return "error";
    }

}
