package hello.proxy.pureProxy.proxy.concreateProxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Concrete {

    public void operation(){
        log.info("Concrete 구체 클래스 실행");
    }
}
