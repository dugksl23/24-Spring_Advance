package hello.proxy.app.V1;


import hello.proxy.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class OrderServiceV1Proxy implements OrderServiceV1 {

    private final OrderServiceV1Impl orderServiceV1;
    private final ProxyLogTrace logTrace;

    @Override
    public String orderItem(String itemId) throws TraceStatusExceptionV2 {

        TraceStatusV2 status = logTrace.begin(this.getClass().getSimpleName());

        try {
            String result = orderServiceV1.orderItem(itemId);
            logTrace.end(status);
            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }

    }
}
