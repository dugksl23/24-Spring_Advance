package hello.proxy.app.V1;

import hello.proxy.exception.TraceStatusException;

import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class OrderServiceV1Proxy implements OrderServiceV1 {

    private final OrderServiceV1Impl orderServiceV1;
    private final LogTrace logTrace;

    @Override
    public String orderItem(String itemId) throws TraceStatusException {

        TraceStatus status = logTrace.begin(this.getClass().getSimpleName());

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
