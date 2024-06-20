package hello.proxy.app.V2;


import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;

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
        return orderControllerV2.noLog();
    }
}

