package hello.proxy.app.V1;


import hello.proxy.exception.TraceStatusException;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryV1Proxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1Impl orderRepositoryV1;
    private final LogTrace logTrace;

    @Override
    public void save(String itemId) throws TraceStatusException {

        TraceStatus status = logTrace.begin(this.getClass().getSimpleName());

        try{
            orderRepositoryV1.save(itemId);
            logTrace.end(status);
        } catch(Exception e){
            logTrace.exception(status,e);
            throw e;
        }

    }
}
