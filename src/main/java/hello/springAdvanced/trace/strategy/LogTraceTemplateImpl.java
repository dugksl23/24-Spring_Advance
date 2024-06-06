package hello.springAdvanced.trace.strategy;

import hello.springAdvanced.exception.TraceStatusException;
import hello.springAdvanced.trace.TraceStatus;
import hello.springAdvanced.trace.domain.LogTrace;
import hello.springAdvanced.trace.strategy.callback.LogTraceTemplate;
import hello.springAdvanced.trace.strategy.callback.TraceCallback;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;


@NoArgsConstructor
@AllArgsConstructor
public class LogTraceTemplateImpl implements LogTraceTemplate {

    private LogTrace logTrace;

    @Override
    public <T> T execute(String message, TraceCallback<T> callback) throws TraceStatusException {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T call = callback.call();
            logTrace.end(status);

            return call;

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw new TraceStatusException(e, status);
        }

    }


}
