package gr.uoi.cse.countrygraph.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.uoi.cse.countrygraph.classcollector.ClassCollector;
import gr.uoi.cse.countrygraph.exception.ExceptionManager;
import gr.uoi.cse.countrygraph.exception.UncaughtException;
import gr.uoi.cse.countrygraph.exception.handler.ExceptionHandler;

public final class ExceptionHandlerLoader implements ApplicationLoader
{
	private static final String EXCEPTION_PATH = "gr.uoi.cse.countrygraph.exception";

	@Override
	public void load()
	{
		final ExceptionManager exceptionManager = ExceptionManager.getInstance();
		final Map<Class<?>, ExceptionHandler> exceptionHandlerMap = new HashMap<>();
		
		final List<Class<?>> throwableClassList = collectThrowableClassList();
		for (final Class<?> throwableClass : throwableClassList)
		{
			final UncaughtException uncaughtExceptionAnnotation = throwableClass.getAnnotation(UncaughtException.class);
			
			final Class<? extends ExceptionHandler> exceptionHandlerClass = uncaughtExceptionAnnotation.handler();
			try
			{
				final ExceptionHandler exceptionHandler = exceptionHandlerClass.getDeclaredConstructor().newInstance();
				exceptionHandlerMap.put(throwableClass, exceptionHandler);
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}
		
		exceptionManager.putAll(exceptionHandlerMap);
		Thread.setDefaultUncaughtExceptionHandler(exceptionManager);
	}
	
	private final List<Class<?>> collectThrowableClassList()
	{
		final ClassCollector classCollector = new ClassCollector();
		return classCollector
				.collectClasses(EXCEPTION_PATH)
				.stream()
				.filter(c -> c.isAnnotationPresent(UncaughtException.class))
				.toList();
	}
}