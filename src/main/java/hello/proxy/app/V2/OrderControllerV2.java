package hello.proxy.app.V2;


import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/order/v2")
@RestController
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    @GetMapping("/request")
    public String request(String itemId) {
        return orderService.orderItem(itemId);
    }

    @GetMapping("/noLog")
    public String noLog() {
        return "ok";
    }
}
