package hello.springAdvanced.trace.strategy.callback;

public interface TraceCallback<T> {

    T call() throws Exception;
}
