package hello.springAdvanced.trace;


import hello.springAdvanced.trace.LogCode.LogCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.tools.Trace;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 로그를 시작했을 때의 상태 정보를 갖고 있는 클래스
 */
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Slf4j
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private Long endTimeMs;
    private String message;
    private LogCode logCode;
    private Long durationMs;

    public TraceStatus(TraceId traceId, String message, LogCode logCode) {
        this.traceId = traceId;
        this.startTimeMs = getStartTimeMs();
        this.message = message;
        this.logCode = logCode;
    }

    public Long getStartTimeMs() {
        if (startTimeMs == null) {
            return this.startTimeMs = System.currentTimeMillis();
        }
        return startTimeMs;
    }

    public Long getEndTimeMs() {
        if (endTimeMs == null) {
            endTimeMs = System.currentTimeMillis();
        }
        this.durationMs = endTimeMs - startTimeMs;
        return endTimeMs;
    }


    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        StringBuilder sb = new StringBuilder();
        sb.append("TraceId: ").append(traceId.getTransactionId()).append("\n");
        sb.append("Message: ").append(message).append("\n");
        sb.append("LogCode: ").append(logCode).append("\n");
        sb.append("StartTimeMs: ").append(startTimeMs).append("\n");
        sb.append("EndTimeMs: ").append(endTimeMs).append("\n");
        sb.append("StartTime : ").append(format.format(startTimeMs)).append("\n");
        sb.append("EndTime : ").append(format.format(endTimeMs)).append("\n");
        sb.append("Duration: ").append(endTimeMs - startTimeMs).append(" ms\n");
        return sb.toString();
    }
}
