package hello.jdkProxy.code;


import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class TimeProxyImpl implements TimeProxyInterface {

    @Override
    public <T, R> R executeByFunction(Function<T, R> function, T... args) {
        log.info("time proxy start");
        long start = System.currentTimeMillis();
        R result = function.apply(args);
        long end = System.currentTimeMillis();
        log.info("time proxy end time: {} ms", end - start);
        return result;
    }

    @Override
    public <T> T executeBySupply(Supplier<T> methodCallback) {

        log.info("time proxy start");
        long start = System.currentTimeMillis();
        T result = methodCallback.get();
        long end = System.currentTimeMillis();
        log.info("time proxy end time: {} ms", end - start);

        return result;

    }
}
