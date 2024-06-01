package hello.springAdvanced.trace.helloTrace;


import hello.springAdvanced.trace.TraceStatus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

public interface HelloTraceV1 {

    public TraceStatus begin(String msg);
    public void end(TraceStatus status);
    public void exception(TraceStatus status, Exception ex);


}

