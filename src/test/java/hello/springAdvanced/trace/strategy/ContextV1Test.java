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


    /**
     * @의존관계 선조립, 후실행
     * Context 생성자 주입 방식으로, 필드로 가진 strategy 와의 의존관계 조립 후 실행.
     */
    @Test
    public void strategyTestV1() {

        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context = new ContextV1(strategyLogic1);

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);

        context.execute();
        context2.execute();
    }


    /**
     * @위임
     * Context 객체 생성 이후, execute() 실행 시점에 의존관계가 없는 strategy를 파라미터로 전달.
     */
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