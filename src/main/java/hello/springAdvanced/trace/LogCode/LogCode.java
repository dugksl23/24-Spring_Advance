package hello.springAdvanced.trace.LogCode;

public enum LogCode {

    BEGIN("BEGIN"),
    END("END"),
    ERROR("EXCEPTION");

    private final String value;

    LogCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
