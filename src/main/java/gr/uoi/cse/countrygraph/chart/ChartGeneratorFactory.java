package gr.uoi.cse.countrygraph.chart;

import gr.uoi.cse.countrygraph.factory.Factory;

public final class ChartGeneratorFactory implements Factory<ChartType, ChartGenerator<?, ?>>
{
	@Override
	public ChartGenerator<?, ?> createNewInstance(ChartType chartType) 
	{
		return switch(chartType) {
		case BAR_CHART -> new BarChartGenerator();
		case LINE_CHART -> new LineChartGenerator();
		case SCATTER_CHART -> new ScatterPlotGenerator();
		};
	}
}