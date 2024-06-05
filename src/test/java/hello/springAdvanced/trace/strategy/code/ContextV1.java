package hello.springAdvanced.trace.strategy.code;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 *  필드에 Strategy 를 보관하는 방식
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContextV1 {


    private Strategy strategy;

    public void execute(){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 위임
        strategy.call();
        // 비지니스 로직 위임 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}", resultTime);

    }



}
