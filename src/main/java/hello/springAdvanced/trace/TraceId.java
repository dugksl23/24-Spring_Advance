package hello.springAdvanced.trace;


import hello.springAdvanced.trace.LogCode.LogCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Getter
@Slf4j
public class TraceId {

    private String transactionId;
    private int depthLevel;

    public TraceId() {
        this.transactionId = createTransactionId();
        this.depthLevel = 0;
    }

    private TraceId(int depthLevel, String transactionId, LogCode code) {
        this.transactionId = transactionId;
        this.depthLevel = depthLevel;
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
        // 현재 날짜 + uuid* 8자리)
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = format.format(new Date());
        String uuid = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        uuid = "-" + uuid.substring(uuid.length() - 8, uuid.length());
        log.info("request uuid, {}", uuid);

        return now + uuid;
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

    public boolean isFirstDepthLevel() {
        return depthLevel == 0;
    }


}
