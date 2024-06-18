package hello.proxy.app.V2;


import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 2024.06.10
 * 부모 구체 클래스에서 @RequestMapping 을 자식인 프록시 객체에게까지 상속되어서
 * requestHandlerMapping error 가 발생.
 * 부모 클래스의 매핑 정보를 프록시 객체로 옮기니 오류 해결...
 */
//@RequestMapping("/order/v2")
public class OrderControllerV2ClassProxy extends OrderControllerV2 {

    private final ProxyLogTrace logTrace;
    private final OrderControllerV2 orderControllerV2;

    public OrderControllerV2ClassProxy(OrderControllerV2 orderControllerV2, ProxyLogTrace logTrace) {
        super(null);
        this.logTrace = logTrace;
        this.orderControllerV2 = orderControllerV2;
    }

    @Override
    public String request(String itemId) {
        TraceStatusV2 statusV2 = logTrace.begin(this.getClass().getSimpleName());
        String result = orderControllerV2.request(itemId);
        logTrace.end(statusV2);
        return result;
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
