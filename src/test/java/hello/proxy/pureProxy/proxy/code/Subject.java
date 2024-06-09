package hello.proxy.pureProxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

public interface Subject {

    String process() throws InterruptedException;
}
