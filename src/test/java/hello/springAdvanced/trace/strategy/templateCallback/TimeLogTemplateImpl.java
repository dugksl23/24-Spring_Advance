package hello.springAdvanced.trace.strategy.templateCallback;


import lombok.extern.slf4j.Slf4j;

@Slf4j
// Context
public class TimeLogTemplateImpl implements TimeLogTemplate {


    public void execute(Callback callback){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 위임
        callback.call();
        // 비지니스 로직 위임 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}", resultTime);

    }


}
