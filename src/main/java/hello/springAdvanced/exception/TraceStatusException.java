package hello.springAdvanced.exception;

import hello.springAdvanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public class TraceStatusException extends Exception {

    private final TraceStatus traceStatus;

    public TraceStatusException(Exception e, TraceStatus traceStatus) {
        super(e);
        this.traceStatus = traceStatus;
    }

    public TraceStatus getTraceStatus() {
        return traceStatus;
    }
}

