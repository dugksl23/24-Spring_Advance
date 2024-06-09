package hello.proxy.app.V1;

import hello.proxy.exception.TraceStatusException;

public interface OrderRepositoryV1 {

    void save(String itemId) throws TraceStatusException;

}
