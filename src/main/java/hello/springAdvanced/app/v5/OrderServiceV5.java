package hello.springAdvanced.app.v5;

import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.strategy.LogTraceTemplateImpl;
import hello.springAdvanced.trace.strategy.callback.LogTraceTemplate;
import hello.springAdvanced.trace.strategy.callback.TraceCallback;
import hello.springAdvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final LogTraceTemplate template;

    @Autowired
    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace) {
        this.orderRepository = orderRepository;
        this.template = new LogTraceTemplateImpl(logTrace);
    }


    public void orderItem(String itemId) throws Exception {

        template.execute(this.getClass().getName(), new TraceCallback<Void>() {
            @Override
            public Void call() throws Exception {
                orderRepository.save(itemId);
                return null;
            }
        });

    }

}
