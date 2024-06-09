package hello.proxy.app.V3;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order/v3")
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;

    @GetMapping("/request")
    public String request(String itemId) {
        log.info("proxy 요청 옵니다.");
        return orderService.orderItem(itemId);
    }

    @GetMapping("/noLog")
    public String noLog() {
        return "ok";
    }
}
