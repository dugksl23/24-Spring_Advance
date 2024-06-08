package hello.springAdvanced.trace.templateMethod;

import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// v3 에서 반복된 log 관련 코드를 template 으로 정의한 템플릿 코드 클래스이다.
public abstract class AbstractTemplate<T> {
    // 추상 템플릿 관련 생성을 할 때, <T> 제네릭을 통해서
    // 객체 생성시 타입에 대한 정보를 나중에 객체를 생성하는 시점으로 미룰 수 있다.

    private final LogTrace logTrace;

    public AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public T execute(String message) throws TraceStatusException {
        TraceStatus status = null;

        try {
            TraceStatus begin = logTrace.begin(message);

            // 비지니스 로직 호출 및 반환 타입을 return
            T result = call();

            logTrace.end(begin);

            return result;

        } catch (Exception ex) {
            logTrace.exception(status, ex);
            throw new TraceStatusException(ex, status);
        }
    }

    protected abstract T call() throws Exception;
}
