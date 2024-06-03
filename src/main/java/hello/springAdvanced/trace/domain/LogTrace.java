package hello.springAdvanced.trace.domain;

import hello.springAdvanced.trace.TraceStatus;

public interface LogTrace {


    public TraceStatus begin(String message);
    /**
     * 실질적 LogBody
     */
    public void end(TraceStatus status);
    public void exception(TraceStatus status, Exception ex);
}
