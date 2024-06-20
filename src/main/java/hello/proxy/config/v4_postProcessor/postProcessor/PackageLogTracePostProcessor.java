package hello.proxy.config.v4_postProcessor.postProcessor;


import hello.springAdvanced.trace.domain.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


@Slf4j
@RequiredArgsConstructor
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String packageName;
    private final Advisor advisor;


    // 생성자 초기화 이후에 객체 조작을 할 수 있기에 AfterInitialization 함수를 오버라이딩
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        /**
         * 프록시 대상 여부 체크 = 자동 스캔용
         * 프록시 적용 대상이 아니면 원본을 그래도 진행
         * App 폴더 하위에 있는 class 만 빈 후처리기로 프록시를 대체 생성하기 위한 분기점
         */
        if (!bean.getClass().getPackageName().startsWith(packageName)) {
//            log.info("bean packageName = {}", bean.getClass().getPackageName());
            return bean;
        }

        /**
         * 프록시 대상 여부 체크 = 수동 스캔용
         * 프록시 적용 대상이면 대체 생성하여 반환
         * App 폴더 하위에 있는 class 만 빈 후처리기로 프록시를 대체 생성하기 위한 분기점
         */
        if (bean.getClass().getPackageName().startsWith(packageName)) {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvisor(advisor);
            proxyFactory.setProxyTargetClass(true); // cglib 를 통한 프록시 생성 옵션
            Object proxy = proxyFactory.getProxy();
            log.info("proxy target = {}, proxy = {}", bean.getClass(), proxy.getClass());
            return proxy;
        }
        return bean;
    }
}
