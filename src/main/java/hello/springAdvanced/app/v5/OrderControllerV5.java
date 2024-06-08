package hello.springAdvanced.app.v5;

import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.strategy.callback.LogTraceTemplate;
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

    @GetMapping("/v5/request")
    public String saveOrder(@RequestParam String itemId) throws TraceStatusException {

        return template.execute(this.getClass().getName(), () -> {
            orderService.orderItem(itemId);
            return "Success";
        });
    }

}
