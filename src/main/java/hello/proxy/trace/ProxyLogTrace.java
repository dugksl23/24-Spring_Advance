package hello.proxy.trace;


public interface ProxyLogTrace {

    public TraceStatusV2 begin(String message);
    public void end(TraceStatusV2 status);
    public void exception(TraceStatusV2 status, Exception e);

}
