package gr.uoi.cse.countrygraph.chart.strategy;

import gr.uoi.cse.countrygraph.chart.ChartType;
import gr.uoi.cse.countrygraph.factory.Factory;

public final class ChartCreationStrategyFactory implements Factory<ChartType, ChartCreationStrategy>
{

	@Override
	public ChartCreationStrategy createNewInstance(ChartType chartType) 
	{
		return switch(chartType) {
		case BAR_CHART -> new BarChartCreationStrategy();
		case LINE_CHART -> new LineChartCreationStrategy();
		case SCATTER_CHART -> new ScatterPlotCreationStrategy();
		};
	}
	
}