package hello.springAdvanced.app.v3;

import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.domain.LogTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace logTrace;

    public void orderItem(TraceStatus status, String itemId) throws Exception {

        try {
            status = logTrace.begin("OrderService ");
            orderRepository.save(status, itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e; //예외는 꼭 다시 던져야 한다.
        }
    }
}
