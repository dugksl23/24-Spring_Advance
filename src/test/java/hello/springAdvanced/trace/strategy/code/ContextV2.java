
package hello.springAdvanced.trace.strategy.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 *  필드에 Strategy 를 보관하는 방식
 */
@Slf4j
@NoArgsConstructor
@Getter
public class ContextV2 {

    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 위임
        strategy.call();
        // 비지니스 로직 위임 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}", resultTime);

    }


}
