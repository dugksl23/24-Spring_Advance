package hello.proxy.trace;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public class TraceStatusV2 {

    private TraceIdV2 traceId;
    private Long startTime;
    private String message;
    private LogCodeV2 logCode;

    public TraceStatusV2(TraceIdV2 traceId, String message, LogCodeV2 logCode) {
        this.traceId = traceId;
        this.startTime = System.currentTimeMillis();
        this.message = message;
        this.logCode= logCode;
    }
}
