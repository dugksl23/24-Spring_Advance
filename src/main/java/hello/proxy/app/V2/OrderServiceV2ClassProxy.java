package hello.proxy.app.V2;

import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;

public class OrderServiceV2ClassProxy extends OrderServiceV2 {

    private final OrderServiceV2 orderServiceV2;
    private final ProxyLogTrace logTrace;


    // 부모 타입의 생성자가 있으면 자바 문법상, 자식 타입에서 호출해줘야 한다.
    // 다만 프록시에서는 부모의 생성자를 채워줄 필요가 없기에 null 을 넘긴다.
    public OrderServiceV2ClassProxy(OrderServiceV2 orderServiceV2, ProxyLogTrace logTrace) {
        super(null);
        this.orderServiceV2 = orderServiceV2;
        this.logTrace = logTrace;
    }


    public String orderItem(String itemId) {
        TraceStatusV2 statusV2 = logTrace.begin("orderItem");
        String result = orderServiceV2.orderItem(itemId);
        logTrace.end(statusV2);
        return result;
    }
}
