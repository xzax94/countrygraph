package gr.uoi.cse.countrygraph.chart.strategy;

import java.util.HashMap;
import java.util.Map;

import gr.uoi.cse.countrygraph.chart.ChartType;

public final class ChartCreationStrategyCache
{
	private final Map<ChartType, ChartCreationStrategy> chartCreationStrategyMap;
	
	private ChartCreationStrategyCache(Map<ChartType, ChartCreationStrategy> chartCreationStrategyMap)
	{
		this.chartCreationStrategyMap = chartCreationStrategyMap;
	}
	
	public final void putAll(Map<ChartType, ChartCreationStrategy> chartCreationStrategyMap)
	{
		chartCreationStrategyMap.forEach((chartType, chartCreationStrategy) -> 
		{
			this.chartCreationStrategyMap.put(chartType, chartCreationStrategy);
		});
	}
	
	public final ChartCreationStrategy getChartCreationStrategyByChartType(ChartType chartType)
	{
		return chartCreationStrategyMap.get(chartType);
	}
	
	public static final ChartCreationStrategyCache getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final ChartCreationStrategyCache INSTANCE = new ChartCreationStrategyCache(new HashMap<>());
	}
}