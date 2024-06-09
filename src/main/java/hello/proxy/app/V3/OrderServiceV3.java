package hello.proxy.app.V3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;

    public String orderItem(String itemId) {
        orderRepository.save(itemId);
        return "success";
    }
}
