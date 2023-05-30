package gr.uoi.cse.countrygraph.exception.handler;

public final class DefaultExceptionHandler implements ExceptionHandler
{
	@Override
	public void handleException(Throwable throwable) 
	{
		throwable.printStackTrace();
	}
}