package hello.jdkProxy.code;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class BImpl implements AInterface {

    @Override
    public String call(String name) {
        log.info("BImpl call");
        return name;
    }

    @Override
    public void call2() {
        log.info("B call2");
    }
}
