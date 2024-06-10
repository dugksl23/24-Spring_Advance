package hello.proxy.app.V1;

import hello.proxy.app.exception.TraceStatusExceptionV2;

public interface OrderRepositoryV1 {

    void save(String itemId) throws TraceStatusExceptionV2;

}
