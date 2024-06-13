package hello.jdkProxy.code;

import java.util.function.Supplier;

public interface AInterface<T> {

    public String call(String name);
    public void call2();
}
