package hello.springAdvanced.app.v1;

import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTraceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final LogTraceV1 logTrace;

    @GetMapping("/v1/request")
    public String saveOrder(@RequestParam String itemId) {

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController " + LogCode.BEGIN.getValue());
            orderService.orderItem(itemId);
            logTrace.completeLog(LogCode.END, status, null);
            return "success";
        } catch (Exception e) {
            logTrace.completeLog(LogCode.ERROR, status, e);
            throw e; //예외는 꼭 다시 던져야 한다.
        }
    }


}
