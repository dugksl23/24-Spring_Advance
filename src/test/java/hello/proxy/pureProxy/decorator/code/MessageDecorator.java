package hello.proxy.pureProxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageDecorator implements Component {

    private final Component target;

    @Override
    public String process() throws InterruptedException {

        log.info("message Decorator 객체 호출");
        String process = target.process();
        String result = "****" + process + "****";

        return result;
    }
}
