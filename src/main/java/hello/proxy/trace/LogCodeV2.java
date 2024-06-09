package hello.proxy.trace;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum LogCodeV2 {

    BEGIN("begin"),
    END("END"),
    EXCEPTION("EXCEPTION");

    private String code;


}


