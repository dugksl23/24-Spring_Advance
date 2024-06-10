package hello.proxy.app.V1;


import hello.proxy.app.exception.TraceStatusExceptionV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class OrderControllerV1Impl implements OrderControllerV1 {

    private final OrderServiceV1 orderService;

    @Override
    public String request(String itemId) throws TraceStatusExceptionV2 {
        return orderService.orderItem(itemId);
    }

    @Override
    public String noLog() {
        throw new IllegalArgumentException("error 발생");
    }
}
