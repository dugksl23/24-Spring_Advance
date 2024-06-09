package hello.proxy.pureProxy.decorator.code;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Decorator implements Component {

    public final Component component;



}
