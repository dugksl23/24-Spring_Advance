package hello.springAdvanced.trace;


import lombok.Getter;

import java.util.UUID;


@Getter
public class TraceId {

    private String transactionId;
    private int depthLevel;


    public TraceId() {
        this.transactionId = createTransactionId();
        this.depthLevel = 0;
    }

    private TraceId(int depthLevel, String transactionId) {
        this.transactionId = transactionId;
        this.depthLevel = depthLevel;
    }

    /**
     * @return String uuId
     * uuid 의 8번째 자리까지 substring 해서 반환.
    */
    private String createTransactionId() {
        // * 생성된 UUID
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * LOG depthLevel 를 표현하기 위한 방법(?)
     */
    public TraceId createNextDepthLevel() {
        return new TraceId(depthLevel + 1, transactionId);
    }

    /**
     * LOG depthLevel 를 표현하기 위한 방법(?)
     */
    public TraceId createPreviousDepthLevel() {
        return new TraceId(depthLevel - 1, transactionId);
    }

    public boolean isFirstDepthLevel(){
        return depthLevel == 0;
    }





}
