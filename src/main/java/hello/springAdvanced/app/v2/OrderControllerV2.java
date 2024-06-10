package hello.springAdvanced.app.v2;

import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.LogCode.LogCode;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
//@RequestMapping("/order")
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final LogTraceV2 logTrace;

    @GetMapping("/v2/request")
    public String saveOrder(@RequestParam String itemId) throws Exception {

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController ");
            orderService.orderItem(status,itemId);
            logTrace.completeLog(LogCode.END, status, null);
            return "success";
        } catch (Exception e) {
            log.info("controller v2 예외 옴");
            logTrace.completeLog(LogCode.ERROR, status, e);
            TraceStatusException statusException = new TraceStatusException(e, status);
            throw statusException; //예외는 꼭 다시 던져야 한다.
        }
    }


}
