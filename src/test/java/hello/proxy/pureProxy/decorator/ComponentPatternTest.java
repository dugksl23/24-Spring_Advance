package hello.proxy.pureProxy.decorator;

import hello.proxy.pureProxy.decorator.code.ComponentPatternClient;
import hello.proxy.pureProxy.decorator.code.MessageDecorator;
import hello.proxy.pureProxy.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

public class ComponentPatternTest {


    @Test
    public void noDecoratorTest() throws InterruptedException {

        ComponentPatternClient client = new ComponentPatternClient(new RealComponent());
        client.execute();
        client.execute();
        client.execute();

    }


    @Test
    public void decoratorPatternTest1() throws InterruptedException {

        ComponentPatternClient client = new ComponentPatternClient(new MessageDecorator(new RealComponent()));
        client.execute();

    }

}
