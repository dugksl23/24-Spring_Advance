package hello.springAdvanced.app.controller;

import hello.springAdvanced.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/v0/request")
    public String saveOrder(@RequestParam String itemId){
        orderService.orderItem(itemId);
        return "saved order";
    }



}
