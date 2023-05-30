package gr.uoi.cse.countrygraph.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;

import gr.uoi.cse.countrygraph.exception.handler.DefaultExceptionHandler;
import gr.uoi.cse.countrygraph.exception.handler.ExceptionHandler;

public final class ExceptionManager implements UncaughtExceptionHandler
{
	private static final ExceptionHandler DEFAULT_EXCEPTION_HANDLER = new DefaultExceptionHandler();
	private final Map<Class<?>, ExceptionHandler> exceptionHandlerMap;
	
	private ExceptionManager(Map<Class<?>, ExceptionHandler> exceptionHandlerMap)
	{
		this.exceptionHandlerMap = exceptionHandlerMap;
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable throwable) 
	{
		final Throwable rootThrowable = findRootCause(throwable);
		final ExceptionHandler exceptionHandler = exceptionHandlerMap.getOrDefault(rootThrowable.getClass(), DEFAULT_EXCEPTION_HANDLER);
		exceptionHandler.handleException(rootThrowable);
	}
	
	public Throwable findRootCause(Throwable throwable) 
	{
	    if (throwable == null)
	        return null;

	    Throwable rootCause = throwable;
	    final Throwable cause = throwable.getCause();

	    if (cause != null)
	        rootCause = findRootCause(cause);

	    return rootCause;
	}

	
	public final void putAll(Map<Class<?>, ExceptionHandler> exceptionHandlerMap)
	{
		exceptionHandlerMap.forEach((exceptionClass, exceptionHandler) -> {
			this.exceptionHandlerMap.put(exceptionClass, exceptionHandler);
		});
	}
	
	public static final ExceptionManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final ExceptionManager INSTANCE = new ExceptionManager(new HashMap<>());
	}
}