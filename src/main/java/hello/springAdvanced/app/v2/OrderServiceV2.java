package hello.springAdvanced.app.v2;

import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTraceV1;
import hello.springAdvanced.trace.domain.LogTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final LogTraceV2 logTrace;

    public void orderItem(TraceStatus status, String itemId) throws Exception {

        try {
            status = logTrace.beginSync(status.getTraceId(), "OrderService " + LogCode.BEGIN.getValue());
            orderRepository.save(status, itemId);
            logTrace.completeLog(LogCode.END, status, null);
        } catch (Exception e) {
            logTrace.completeLog(LogCode.ERROR, status, e);
            throw e; //예외는 꼭 다시 던져야 한다.
        }
    }
}
