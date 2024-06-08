package hello.springAdvanced.trace.templateMethod;

import hello.springAdvanced.trace.templateMethod.code.AbstractTemplateMethod;
import hello.springAdvanced.trace.templateMethod.code.SubClassLogic1;
import hello.springAdvanced.trace.templateMethod.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class templateMethodTest {

    @Test
    public void templateMethodTest() {
        logic1();
        logic2();
    }
    /**
     *  핵심 기능 : logic1()
     *  부가 기능 : 시간을 측정하는 기능.
     */
    private void logic1() {

        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}", resultTime);

    }

    private void logic2() {

        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}", resultTime);

    }

    @Test
    public void templateMethodV1() {

        AbstractTemplateMethod templateLogic1 = new SubClassLogic1();
        AbstractTemplateMethod templateLogic2 = new SubClassLogic2();

        templateLogic1.execute();
        templateLogic2.execute();

    }

    @Test
    public void templateMethodV2() {


        AbstractTemplateMethod template1 = new AbstractTemplateMethod() {
            @Override
            protected void call() {
                log.info("비지니스 로직 1 실행");
            }
        };
        template1.execute();
        log.info("template 1 name : {}", template1.getClass());

        AbstractTemplateMethod template2 = new AbstractTemplateMethod() {
            @Override
            protected void call() {
                log.info("비지니스 로직 2 실행");
            }
        };

        template2.execute();
        log.info("template 2 name : {}", template2.getClass());
        template1.execute();
    }

}
