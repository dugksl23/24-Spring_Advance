package hello.proxy.trace;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Getter
public class TraceIdV2 {

    private String traceId;
    private int depthLevel;

    public TraceIdV2() {
        this.traceId = createTransactionId();
        this.depthLevel = 0;
    }

    public TraceIdV2(String traceId, int depthLevel) {
        this.traceId = traceId;
        this.depthLevel = depthLevel;
    }

    private String createTransactionId() {
        // 현재 날짜 + uuid* 8자리)
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = format.format(new Date());
        String uuid = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        uuid = uuid.substring(uuid.length() - 8, uuid.length());
        log.info("Request UUID : {}", uuid);

        return now + "-" + uuid;
    }

    public TraceIdV2 createNextDepthLevel() {
        return new TraceIdV2(traceId, depthLevel + 1).createNextDepthLevel();
    }

    public TraceIdV2 createPreviousDepthLevel() {
        return new TraceIdV2(traceId, depthLevel - 1);
    }

    public boolean isFirstDepthLevel() {
        return depthLevel == 0;
    }
}
