package gr.uoi.cse.countrygraph.exception;

import gr.uoi.cse.countrygraph.exception.handler.ScatterPlotMeasureExceptionHandler;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Exception(handler = ScatterPlotMeasureExceptionHandler.class)
public final class ScatterPlotMeasureException extends RuntimeException
{
	private static final long serialVersionUID = 5839315563312944858L;
}