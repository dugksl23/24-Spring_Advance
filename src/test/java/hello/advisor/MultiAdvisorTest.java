package hello.advisor;


import hello.common.ServiceImpl;
import hello.common.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

@Slf4j
public class MultiAdvisorTest {

    @Test
    @DisplayName("MultiAdvisorTest 1 ")
    public void multiAdvisorTest1() {

        final String[] PATTERN = new String[]{"save*", "request*", "order*"};

        // client -> proxy(addVisor2) -> proxy(advisor1) -> target
        // proxy 1 생성
        ServiceInterface service = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(service);

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        proxyFactory1.addAdvisor(new DefaultPointcutAdvisor(pointcut, new Advice1()));
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // proxy 2 생성 ->  내부에서 target(프록시 1) 호출
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        proxyFactory2.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();

    }

    @Test
    @DisplayName("하나의 프록시팩토리, MultiAdvisorTest 2 ")
    public void multiAdvisorTest2() {

        final String[] PATTERN = new String[]{"save*", "request*", "order*"};

        // client -> proxy -> addVisor2 -> advisor1 -> target
        // proxy 생성
        ServiceInterface service = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(service);

        // advisor 1, 2 생성 / template 지정
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERN);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(pointcut, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        proxyFactory.addAdvisors(advisor2, advisor1);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();

    }


    @Slf4j
    static class Advice1 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 execute");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 execute");
            return invocation.proceed();
        }
    }
}
