package gr.uoi.cse.countrygraph.exception;

import gr.uoi.cse.countrygraph.exception.handler.LineChartMeasureExceptionHandler;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@UncaughtException(handler = LineChartMeasureExceptionHandler.class)
public final class LineChartMeasureException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
}