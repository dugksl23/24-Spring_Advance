package hello.proxy.pureProxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ComponentPatternClient {

    private final Component component;

    public String execute() throws InterruptedException {
        String result = component.process();
        log.info("result : {}", result);
        return result;
    }
}
