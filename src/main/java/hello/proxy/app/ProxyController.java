package hello.proxy.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProxyController {

    @RequestMapping("/")
    public String index() {
        return "proxy hello";
    }


}
