package hello.springAdvanced.trace;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.tools.Trace;

/**
 * 로그를 시작했을 때의 상태 정보를 갖고 있는 클래스
 */
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private Long endTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, String message) {
        this.traceId = traceId;
        this.startTimeMs = getStartTimeMs();
        this.message = message;
    }

    private Long getStartTimeMs() {
        if (startTimeMs == null) {
            return this.startTimeMs = System.currentTimeMillis();
        }
        return startTimeMs;
    }

    public Long getEndTimeMs() {
        if (endTimeMs == null) {
            return this.endTimeMs = System.currentTimeMillis() - startTimeMs;
        }
        return endTimeMs;
    }
}
