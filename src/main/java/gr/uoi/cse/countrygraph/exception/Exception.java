package gr.uoi.cse.countrygraph.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gr.uoi.cse.countrygraph.exception.handler.ExceptionHandler;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Exception
{
	Class<? extends ExceptionHandler> handler();
}