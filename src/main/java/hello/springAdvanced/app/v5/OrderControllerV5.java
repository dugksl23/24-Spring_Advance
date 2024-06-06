package hello.springAdvanced.app.v5;

import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.strategy.LogTraceTemplateImpl;
import hello.springAdvanced.trace.strategy.callback.LogTraceTemplate;
import hello.springAdvanced.trace.strategy.callback.TraceCallback;
import hello.springAdvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final LogTraceTemplate template;

//    @Autowired
//    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
//        this.orderService = orderService;
//        this.template = new LogTraceTemplateImpl(logTrace);
//    }

    @GetMapping("/v5/request")
    public String saveOrder(@RequestParam String itemId) throws TraceStatusException {

        return template.execute(this.getClass().getName(), () -> {
                    orderService.orderItem(itemId);
                    return "Success";
        });
    }

}
