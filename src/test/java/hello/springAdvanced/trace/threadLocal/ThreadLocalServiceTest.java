package hello.springAdvanced.trace.threadLocal;

import hello.springAdvanced.trace.threadLocal.code.FieldService;
import hello.springAdvanced.trace.threadLocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalServiceTest {

    @Test
    public void no_concurrency_issues() throws InterruptedException {

        ThreadLocalService threadLocalService = new ThreadLocalService();

        Runnable userA = () -> threadLocalService.logic("userA", null);
        Thread threadA = new Thread(userA);
        threadA.setName("threadA");

        Runnable userB = () -> threadLocalService.logic("userB", "userB");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start();
        Thread.sleep(2000); // 동시성 문제 없음
        threadB.start();
        Thread.sleep(2000);

        log.info("thread currency test 종료");

    }

    @Test
    public void concurrency_issues() throws InterruptedException {

        ThreadLocalService threadLocalService = new ThreadLocalService();

        Runnable userA = () -> threadLocalService.logic("User A", "userA");
        Thread threadA = new Thread(userA);
        threadA.setName("threadA");

        Runnable userB = () -> threadLocalService.logic("User B", "userB");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start();
        Thread.sleep(1000); // 동시성 문제 있음
        threadB.start();
        Thread.sleep(2000);

        log.info("thread currency test 종료");

    }

}