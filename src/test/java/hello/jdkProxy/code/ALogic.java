package hello.jdkProxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ALogic {

    private String name;

    public String save(String name){

        this.name = name;
        log.info("name : {}", this.name);
        return this.name;

    }
}
