package hello.proxy.pureProxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {
    @Override
    public String process() throws InterruptedException {

        log.info("Real Component 호출");
        Sleep(1000);

        return "process";
    }

    private void Sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
