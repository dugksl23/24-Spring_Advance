package hello.proxy.app.V1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/order/v1")
@ResponseBody
@Controller
// 스프링은 @Controller 혹은 @RequestMapping 이 있으면 Controller 로 인식하여 스프링 빈으로 등록한다.
public interface OrderControllerV1 {


    @GetMapping("/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/noLog")
    String noLog();

}
