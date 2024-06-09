package hello.proxy.app.V1;


import hello.proxy.exception.TraceStatusException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class OrderServiceV1Impl implements OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;

    @Override
    public String orderItem(String itemId) throws TraceStatusException {
        orderRepository.save(itemId);
        return "success";
    }
}
