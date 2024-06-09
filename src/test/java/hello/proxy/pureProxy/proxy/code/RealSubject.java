package hello.proxy.pureProxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    @Override
    public String process() throws InterruptedException {

        log.info("실제 객제 호출");
        sleep(1000);

        return "process";
    }

    private void sleep(int milliSecond) throws InterruptedException {
        Thread.sleep(milliSecond);
    }
}
