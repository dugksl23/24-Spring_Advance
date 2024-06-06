package hello.springAdvanced.trace.strategy.template;


import org.aspectj.weaver.ast.Call;

public interface TimeLogTemplate {

    public void execute(Callback callback);
}
