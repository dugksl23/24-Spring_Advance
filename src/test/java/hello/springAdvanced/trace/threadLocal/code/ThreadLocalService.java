package hello.springAdvanced.trace.threadLocal.code;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.Generated;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ThreadLocalService {

    private ThreadLocal<String> threadName = new ThreadLocal<>();

    public void logic(String threadUser, String name) {

        this.threadName.set(name);
        log.info("Thread User : {}, name 저장 로직 실행 : {}", threadUser, threadName.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Thread User : {}, name 조회 로직 실행 : {}", threadUser, this.threadName.get());
    }

}
