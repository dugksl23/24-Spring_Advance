package hello.springAdvanced.trace.strategy.callback;

import hello.springAdvanced.exception.TraceStatusException;

public interface LogTraceTemplate {

    // <T> : 메서드의 파라미터로 전달된 클래스의 반환타입
    // T  : 메서드의 반환 타입
    <T> T execute(String message, TraceCallback<T> callback) throws TraceStatusException;
}

