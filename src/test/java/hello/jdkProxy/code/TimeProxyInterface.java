package hello.jdkProxy.code;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.Supplier;

public interface TimeProxyInterface {


    // Function 인터페이스를 제네릭으로 정의
//    @FunctionalInterface
//    public interface Function<T, R> {
//        R apply(T... args);
//    }

    @FunctionalInterface
    public interface Function<T> {
        T apply(T... args);
    }

    // Function 인터페이스를 사용하여 메서드 실행
    // T 메서드에 전달되는 타입
    // R 메서드 반환 타입
//    public <T, R> R executeByFunction(Function<T, R> function, T... args);
    public <T> T executeByFunction(Function<T> function, T... args);

    // Supplier 인터페이스는 인자를 받지 않고 값을 반환하는 함수형 인터페이스입니다.
    // 따라서, Supplier를 사용하면 인자를 전달할 수 없습니다. 만약 인자를 전달해야 한다면, 다른 함수형 인터페이스를 사용하거나, 람다 내부에서 인자를 고정하여 사용할 수 있습니다.
    public <T> T executeBySupply(Supplier<T> methodCallback);


}
