package hello.proxy.exception;

import hello.springAdvanced.trace.TraceStatus;
import lombok.Getter;


@Getter
public class TraceStatusException extends Exception {

    private final TraceStatus status;

    public TraceStatusException(TraceStatus status) {
        this.status = status;
    }

    public TraceStatusException(Exception e, TraceStatus status) {
        super(e);
        this.status = status;
    }

}
