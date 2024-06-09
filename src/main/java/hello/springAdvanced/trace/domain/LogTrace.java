package hello.springAdvanced.trace.domain;

import hello.springAdvanced.trace.TraceStatus;

/**
 * 실질적 Logger
 */
public interface LogTrace {


    public TraceStatus begin(String message);
    public void end(TraceStatus status);
    public void exception(TraceStatus status, Exception ex);
}
