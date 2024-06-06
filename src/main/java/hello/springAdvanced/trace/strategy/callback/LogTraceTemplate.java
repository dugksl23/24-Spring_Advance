package hello.springAdvanced.trace.strategy.callback;

import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.domain.LogTrace;

import javax.security.auth.callback.Callback;

public interface LogTraceTemplate {

    // <T> : 메서드의 파라미터로 전달된 클래스의 반환타입
    // T  : 메서드의 반환 타입
    public <T> T execute(String message, TraceCallback<T> callback) throws TraceStatusException;
}

