package hello.springAdvanced.trace.strategy;

import hello.springAdvanced.trace.strategy.template.Callback;
import hello.springAdvanced.trace.strategy.template.TimeLogTemplate;
import hello.springAdvanced.trace.strategy.template.TimeLogTemplateImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {


    /**
     * 템플릿 콜백 패턴 and 내부 익명 함수
     * 내부 익명함수를 사용할 경우에는 Override 이기에, 기존 구현체를 사용하지 않고, 실행할 코드 조각을 넘긴다.
     */
    @Test
    public void templateCallbackV1() {

        // context 생성
        TimeLogTemplate logTemplate = new TimeLogTemplateImpl();

        // callback 함수 람다 이용하여 익명함수 전달.
        logTemplate.execute(() -> new TimeLogTemplate() {
            @Override
            public void execute(Callback callback) {
                log.info("비지니스 로직 1 실행");
            }
        });

        logTemplate.execute(() -> new TimeLogTemplate() {
            @Override
            public void execute(Callback callback) {
                log.info("비지니스 로직 2 실행");
            }
        });

    }

    /**
     * 템플릿 콜백 패턴 and 람다 문법
     * 람다 문법을 사용할 경우에는 Override 가 아니고, 전달하는 것이기에 기존 구현체를 사용하여 코드 조각을 넘긴다.
     */
    @Test
    public void templateCallbackV2() {

        // context 생성
        TimeLogTemplate logTemplate = new TimeLogTemplateImpl();

        // callback 함수 람다 이용하여 익명함수 전달.
        logTemplate.execute(() -> log.info("비지니스 로직 1 실행"));
        logTemplate.execute(() -> log.info("비지니스 로직 2 실행"));


    }


}
