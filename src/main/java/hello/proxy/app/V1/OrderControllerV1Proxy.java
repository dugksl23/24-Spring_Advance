package hello.proxy.app.V1;

import hello.proxy.exception.TraceStatusException;

import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderControllerV1Proxy implements OrderControllerV1 {

    private final OrderControllerV1Impl orderControllerV1;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) throws TraceStatusException {
        TraceStatus status = logTrace.begin(this.getClass().getSimpleName());

        try {
            String request = orderControllerV1.request(itemId);
            logTrace.end(status);
            return request;
        } catch (Exception e) {
            throw new TraceStatusException(e, status);
        }

    }

    @Override
    public String noLog() throws TraceStatusException {
        TraceStatus status = logTrace.begin(this.getClass().getSimpleName());
        try {

            String result = orderControllerV1.noLog();
            logTrace.end(status);
            return result;

        } catch (Exception e) {
            logTrace.exception(status,e);
            throw new TraceStatusException(e, status);
        }

    }
}
