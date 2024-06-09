package hello.proxy.pureProxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator {

    public MessageDecorator(Component component) {
        super(component);
    }

//    private final Component target;

    @Override
    public String process() throws InterruptedException {

        log.info("message Decorator 호출");
        String process = component.process();
        String result = "****" + process + "****";
        return result;
    }
}
