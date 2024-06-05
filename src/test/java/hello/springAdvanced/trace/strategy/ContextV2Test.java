package hello.springAdvanced.trace.strategy;

import hello.springAdvanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ContextV2Test {

    /**
     * V2 전략 패턴 사용
     */
    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * V2 전략 패턴 사용 - 내부 익명 클래스
     */
    @Test
    void strategyV2(){
        ContextV2 context = new ContextV2();
        context.execute(new Strategy(){

            @Override
            public void call() {
                log.info("비지니스 로직 1 실행");
            }
        });

        context.execute(new Strategy(){

            @Override
            public void call() {
                log.info("비지니스 로직 2 실행");
            }
        });
    }

    /**
     * V2 전략 패턴 사용 - 람
     */
    @Test
    void strategyV3(){
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비지니스 로직 1 실행"));
        context.execute(() -> log.info("비지니스 로직 2 실행"));
    }


}