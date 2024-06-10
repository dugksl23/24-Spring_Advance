package hello.proxy.pureProxy.proxy.concreateProxy;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ConcreteClient {

    private Concrete concreteLogic;  //ConcreteLogic, TimeProxy 모두 주입 가능

    public void execute(){
        concreteLogic.operation();
    }


}
