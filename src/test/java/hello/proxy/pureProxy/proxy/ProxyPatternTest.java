package hello.proxy.pureProxy.proxy;


import hello.proxy.pureProxy.proxy.code.InheritedCashProxyRealSubject;
import hello.proxy.pureProxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureProxy.proxy.code.CasheProxySubject;
import hello.proxy.pureProxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {


    @Test
    public void noProxyPatternTest() throws InterruptedException {

        ProxyPatternClient client = new ProxyPatternClient(new RealSubject());
        client.execute();
        client.execute();
        client.execute();

    }


    @Test
    public void proxyPatternTest() throws InterruptedException {

        ProxyPatternClient client = new ProxyPatternClient(new CasheProxySubject(new RealSubject()));
        client.execute();
        client.execute();
        client.execute();

    }

    @Test
    public void InheritedCashProxyRealSubjectTest() throws InterruptedException {

        ProxyPatternClient client = new ProxyPatternClient(new InheritedCashProxyRealSubject());
        client.execute();
        client.execute();
        client.execute();

    }

}
