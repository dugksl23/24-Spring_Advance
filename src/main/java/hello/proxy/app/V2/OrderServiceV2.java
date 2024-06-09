package hello.proxy.app.V2;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    public String orderItem(String itemId) {
        orderRepository.save(itemId);
        return "success";
    }
}
