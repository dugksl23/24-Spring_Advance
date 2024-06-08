package hello.proxy.app.V1;


import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class OrderServiceV1Impl implements OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;

    @Override
    public String orderItem(String itemId) {
        orderRepository.save(itemId);
        return "success";
    }
}
