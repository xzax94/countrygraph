package gr.uoi.cse.countrygraph.loader;

import java.util.HashMap;
import java.util.Map;

import gr.uoi.cse.countrygraph.chart.ChartType;
import gr.uoi.cse.countrygraph.chart.strategy.ChartCreationStrategy;
import gr.uoi.cse.countrygraph.chart.strategy.ChartCreationStrategyCache;
import gr.uoi.cse.countrygraph.chart.strategy.ChartCreationStrategyFactory;

public final class BarChartCreationStrategyLoader implements ApplicationLoader
{
	@Override
	public void load() 
	{
		final ChartCreationStrategyFactory chartCreationStrategyFactory = new ChartCreationStrategyFactory();
    	final Map<ChartType, ChartCreationStrategy> chartCreationStrategyMap = new HashMap<>();
    	for (final ChartType chartType : ChartType.values())
    	{
    		final ChartCreationStrategy chartCreationStrategy = chartCreationStrategyFactory.createNewInstance(chartType);
    		chartCreationStrategyMap.put(chartType, chartCreationStrategy);
    	}
    	
    	ChartCreationStrategyCache.getInstance().putAll(chartCreationStrategyMap);
	}
}