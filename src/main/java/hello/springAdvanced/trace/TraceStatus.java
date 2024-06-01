package hello.springAdvanced.trace;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.tools.Trace;

/**
 * 로그를 시작했을 때의 상태 정보를 갖고 있는 클래스
 */
@Getter
@AllArgsConstructor
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private String message;
}
