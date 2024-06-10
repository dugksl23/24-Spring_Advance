package hello.proxy.pureProxy.proxy;

import hello.proxy.pureProxy.proxy.concreateProxy.Concrete;
import hello.proxy.pureProxy.proxy.concreateProxy.ConcreteClient;
import hello.proxy.pureProxy.proxy.concreateProxy.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {


    @Test
    public void concreteProxyTest() {

        Concrete concrete = new Concrete();
        ConcreteClient client = new ConcreteClient(concrete);
        client.execute();

    }

    @Test
    public void addTimeProxyTest() {

        Concrete concrete = new Concrete();
        TimeProxy timeProxy = new TimeProxy(concrete);
        // ConcreteClient 는 Concrete 를 참조하고 있다.
        // TimeProxy 는 concrete 의 자식 클래스.
        // 다형성에 의해서 부모는 자식까지 품을 수 있다.
        // 상속을 써도 다형성을 적용되고, 인터페이를 구현해도 다형성 적용.
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();

    }
}
