package hello.postProcessor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BasicTest {


    @Test
    public void basicTest() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        MemberA memberA = (MemberA) applicationContext.getBean("getMemberA", MemberA.class);
        MemberA memberB = applicationContext.getBean("getMemberB", MemberA.class);
        log.info("beanPostProcessor: {}", memberA);
        log.info("beanPostProcessor: {}", memberB);

    }

    @Configuration
    static class TestConfig {

        @Bean
        public MemberA getMemberA() {
            return new MemberA("memberA");
        }

        @Bean
        public MemberB getMemberB() {
            return new MemberB("memberB");
        }

        @Bean
        public BeanPostProcessor beanPostProcessor() {
            return new AtoBPostProcessor();
        }

    }

    @Getter
    @Setter
    @Slf4j
    static class MemberA {
        private String name;

        public MemberA(String name) {
            this.name = name;
            log.info("member name = {}", name);
        }
    }


    @Getter
    @Setter
    @Slf4j
    static class MemberB {
        private String name;

        public MemberB(String name) {
            this.name = name;
            log.info("member name = {}", name);
        }
    }


    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {
        // Java 8 이후에 default 키워드는 필수적으로 오버라이딩 하지 않아도 된다.


        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {} , bean = {}", beanName, bean);
            if (bean instanceof MemberB) {
                MemberA memberA = new MemberA("memberA");
                log.info("bean {} switched to {}", bean, memberA);
                return memberA;
            }

            return bean;
        }
    }

}
