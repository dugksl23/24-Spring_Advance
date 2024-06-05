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

    /**
     * 람다 문법으로 내부 익명 클래스를 구현
     */
    @Test
    public void strategyTestV3() {

        ContextV1 context = new ContextV1(() -> log.info("비지니스 로직 1 실행"));
        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직 2 실행"));
        context.execute();
        context2.execute();

    }

}