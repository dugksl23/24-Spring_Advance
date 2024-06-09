package hello.proxy.pureProxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator {

//    private final Component component;
    private Long startTime;
    private Long endTime;

    public TimeDecorator(Component component) {
        super(component);
    }

    @Override
    public String process() throws InterruptedException {

        log.info("time decorator 호출");
        startTime = System.currentTimeMillis();
        String process = component.process();
        endTime = System.currentTimeMillis();

        log.info("duration : {}ms", endTime - startTime);

        return process;
    }
}
