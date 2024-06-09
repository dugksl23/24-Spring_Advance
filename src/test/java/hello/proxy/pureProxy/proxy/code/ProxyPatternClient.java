package hello.proxy.pureProxy.proxy.code;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProxyPatternClient {

    private final Subject subject;

    public String execute() throws InterruptedException {
        return subject.process();
    }

}
