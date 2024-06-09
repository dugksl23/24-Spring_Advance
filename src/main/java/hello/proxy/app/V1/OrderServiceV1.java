package hello.proxy.app.V1;

import hello.proxy.exception.TraceStatusException;

public interface OrderServiceV1 {

    String orderItem(String itemId) throws TraceStatusException;

}
