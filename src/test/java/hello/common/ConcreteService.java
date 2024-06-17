package hello.common;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class ConcreteService {

    public void call() {
        log.info("ConcreteService 호출");
    }
}
