package hello.jdkProxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BLogic {

    private Integer age;
    private String name;

    public Integer save(Integer age){

        this.age = age;
        log.info("age : {}", this.age);
        return this.age;

    }

    public Integer save(Integer age, String name){

        this.age = age;
        this.name = name;
        log.info("age : {}", this.age);
        return this.age;

    }
}
