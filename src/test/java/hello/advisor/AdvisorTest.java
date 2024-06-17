package hello.advisor;

import hello.common.ServiceImpl;
import hello.common.ServiceInterface;
import hello.proxyFactory.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.config.AdvisorEntry;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class AdvisorTest {

    @Test
    public void advisorTest1() {

        // 1. 구체 클래스 생성
        ServiceInterface service = new ServiceImpl();

        // 2. 프록시 생성을 위한 구체 클래스 지정
        ProxyFactory proxyFactory = new ProxyFactory(service);


        // 3. Advisor 생성
        // @파라미터 : Advice 및 PointCut
        // @   pointcut.TRUE : true 인 경에만 적용 대상, 아니면 false
        Advisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());

        // 4. advisor 등록
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setProxyTargetClass(true);

        // 5. 프록시 생성
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.find();
        proxy.save();

        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();


    }

}
