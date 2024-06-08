package hello.springAdvanced.trace.templateMethod.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplateMethod {

    public void execute(){

        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        call();
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}", resultTime);

    }

    // private 은 상속되지 않기에, protected 으로 해야 하며,
    // 추상 클래스를 상속받은 클래스에서 메서드 구현을 하게끔 하도록
    // 메서드도 모두 추상화 메서드로 지정해야 한다.
    // ex) abstract void call();
    protected abstract void call();

}
