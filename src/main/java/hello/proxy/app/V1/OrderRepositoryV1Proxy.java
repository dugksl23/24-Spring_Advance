package hello.proxy.app.V1;


import hello.proxy.app.exception.TraceStatusExceptionV2;
import hello.proxy.trace.ProxyLogTrace;
import hello.proxy.trace.TraceStatusV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryV1Proxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1Impl orderRepositoryV1;
    private final ProxyLogTrace logTrace;

    @Override
    public void save(String itemId) throws TraceStatusExceptionV2 {

        TraceStatusV2 status = logTrace.begin(this.getClass().getSimpleName());

        try{
            orderRepositoryV1.save(itemId);
            logTrace.end(status);
        } catch(Exception e){
            logTrace.exception(status,e);
            throw e;
        }

    }
}
