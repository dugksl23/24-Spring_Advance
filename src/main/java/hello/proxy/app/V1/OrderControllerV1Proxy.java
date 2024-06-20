package hello.proxy.app.V1;

import hello.proxy.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderControllerV1Proxy implements OrderControllerV1 {

    private final OrderControllerV1Impl orderControllerV1;
    private final ProxyLogTrace logTrace;

    @Override
    public String request(String itemId) throws TraceStatusExceptionV2 {

        TraceStatusV2 status = logTrace.begin(this.getClass().getSimpleName());

        try {
            String request = orderControllerV1.request(itemId);
            logTrace.end(status);
            return request;
        } catch (Exception e) {
            throw new TraceStatusExceptionV2(e, status);
        }

    }

    @Override
    public String noLog() throws TraceStatusExceptionV2 {
//        TraceStatusV2 status = logTrace.begin(this.getClass().getSimpleName());
//        try {
//
//            String result = orderControllerV1.noLog();
//            logTrace.end(status);
//            return result;
//
//        } catch (Exception e) {
//
//            logTrace.exception(status,e);
//            throw new TraceStatusExceptionV2(e);
//        }
        return orderControllerV1.noLog();

    }
}
