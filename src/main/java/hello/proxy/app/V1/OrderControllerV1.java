package hello.proxy.app.V1;

import hello.proxy.app.exception.TraceStatusExceptionV2;
import hello.springAdvanced.exception.TraceStatusException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order/v1")
@ResponseBody
@RestController
// 스프링은 @Controller 혹은 @RequestMapping 이 있으면 Controller 로 인식하여 스프링 빈으로 등록한다.
public interface OrderControllerV1 {

    @GetMapping("/request")
    String request(@RequestParam("itemId") String itemId) throws TraceStatusExceptionV2;

    @GetMapping("/noLog")
    String noLog() throws TraceStatusException, TraceStatusExceptionV2;

}
