package hello.proxy.exception;

import hello.proxy.trace.TraceStatusV2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class TraceStatusExceptionV2 extends Exception {

    private final TraceStatusV2 status;

    public TraceStatusExceptionV2(Exception e, TraceStatusV2 status) {
        super(e);
        this.status = status;
    }

    public TraceStatusExceptionV2(TraceStatusV2 status) {
        this.status = status;
    }

    public TraceStatusExceptionV2(Exception e) {
        super(e);
        this.status = null;
    }
}
