package gr.uoi.cse.countrygraph.exception;

import gr.uoi.cse.countrygraph.exception.handler.BarChartMeasureExceptionHandler;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@UncaughtException(handler = BarChartMeasureExceptionHandler.class)
public final class BarChartMeasureException extends RuntimeException
{
	private static final long serialVersionUID = -4329191782922089592L;
}