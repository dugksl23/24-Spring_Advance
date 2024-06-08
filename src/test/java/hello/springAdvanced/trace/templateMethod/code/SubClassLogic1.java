package hello.springAdvanced.trace.templateMethod.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic1 extends AbstractTemplateMethod {
    @Override
    protected void call() {
        log.info("비지니스 로직 1 실행");
    }
}
