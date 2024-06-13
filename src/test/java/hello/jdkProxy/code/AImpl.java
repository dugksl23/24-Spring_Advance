package hello.jdkProxy.code;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class AImpl implements AInterface {

    @Override
    public String call(String name) {
        log.info("A call");
        return name;
    }

    @Override
    public void call2() {
        log.info("A call2");
    }
}
