package gr.uoi.cse.countrygraph.exception.handler;

@FunctionalInterface
public interface ExceptionHandler
{
	void handleException(Throwable exception);
}