package hello.proxy.app.V1;

import hello.proxy.app.exception.TraceStatusExceptionV2;

public interface OrderServiceV1 {

    String orderItem(String itemId) throws TraceStatusExceptionV2;

}
