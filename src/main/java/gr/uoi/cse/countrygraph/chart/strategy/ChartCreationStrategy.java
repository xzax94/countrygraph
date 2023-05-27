package gr.uoi.cse.countrygraph.chart.strategy;

import gr.uoi.cse.countrygraph.GraphController;

@FunctionalInterface
public interface ChartCreationStrategy
{
	void createChart(GraphController graphController);
}