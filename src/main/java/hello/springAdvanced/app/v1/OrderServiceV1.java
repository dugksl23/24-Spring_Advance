package hello.springAdvanced.app.v1;

import hello.springAdvanced.app.v0.repository.OrderRepositoryV0;
import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final LogTraceV1 logTrace;

    public void orderItem(String itemId){

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService " + LogCode.BEGIN.getValue());
            orderRepository.save(itemId);
            logTrace.completeLog(LogCode.END, status, null);
        } catch (Exception e) {
            logTrace.completeLog(LogCode.ERROR, status, e);
            throw e; //예외는 꼭 다시 던져야 한다.
        }



    }
}
