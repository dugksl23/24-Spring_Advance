package hello.springAdvanced.trace.threadLocal.code;


import hello.springAdvanced.trace.threadLocal.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    @Test
    public void no_concurrency_issues() throws InterruptedException {

        FieldService fieldService = new FieldService();

        Runnable userA = () -> fieldService.logic("userA", null);
        Thread threadA = new Thread(userA);
        threadA.setName("threadA");

        Runnable userB = () -> fieldService.logic("userB", "userB");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start();
        Thread.sleep(2000);
        threadB.start();
        Thread.sleep(2000);

        log.info("thread currency test 종료");

    }

    @Test
    public void concurrency_issues() throws InterruptedException {

        FieldService fieldService = new FieldService();

        Runnable userA = () -> fieldService.logic("User A", null);
        Thread threadA = new Thread(userA);
        threadA.setName("threadA");

        Runnable userB = () -> fieldService.logic("User B", "userB");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start();
        Thread.sleep(1000);
        threadB.start();
        Thread.sleep(2000);

        log.info("thread currency test 종료");


    }

}
