package hello.springAdvanced.trace.strategy;

import hello.springAdvanced.trace.strategy.code.ContextV1;
import hello.springAdvanced.trace.strategy.code.Strategy;
import hello.springAdvanced.trace.strategy.code.StrategyLogic1;
import hello.springAdvanced.trace.strategy.code.StrategyLogic2;
import hello.springAdvanced.trace.template.code.AbstractTemplateMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ContextV1Test {


    @Test
    public void templateMethodTest() {
        logic1();
        logic2();
    }

    /**
     * 핵심 기능 : logic1()
     * 부가 기능 : 시간을 측정하는 기능.
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
    public void strategyTestV1() {

        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context = new ContextV1(strategyLogic1);

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);

        context.execute();
        context2.execute();
    }

    @Test
    public void strategyTestV2() {

        Strategy strategyLogic1 = new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직 1 실행");
            }
        };

        ContextV1 context = new ContextV1(strategyLogic1);


        Strategy strategyLogic2 = new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직 2 실행");
            }
        };

        ContextV1 context2 = new ContextV1(strategyLogic2);

        context.execute();
        context2.execute();

    }

    @Test
    public void strategyTestV3() {

        ContextV1 context = new ContextV1(() -> log.info("비지니스 로직 1 실행"));
        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직 2 실행"));
        context.execute();
        context2.execute();

    }

}