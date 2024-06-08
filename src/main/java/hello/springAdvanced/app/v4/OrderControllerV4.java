package hello.springAdvanced.app.v4;

import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.templateMethod.AbstractTemplate;
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
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v4/request")
    public String saveOrder(@RequestParam String itemId) throws Exception {

        // 1. logTrace(logger) 를 템플릿 메서드 패턴을 통해 추상 클래스의 객체를 생성한다.
        // 2. 상속을 받은 내부 익명 클래스를 생성한다.
        // 3. 익명 클래스의 콜백함수인 call()에 비지니스 로직을 정의한다.
        AbstractTemplate<String> template = new AbstractTemplate<String>(logTrace) {

            @Override
            protected String call() throws Exception {
                orderService.orderItem(itemId);
                return "success";
            }
        };

        return template.execute("orderController");

    }


}
