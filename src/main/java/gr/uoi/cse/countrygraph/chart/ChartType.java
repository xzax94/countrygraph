package gr.uoi.cse.countrygraph.chart;

import java.util.stream.Stream;

public enum ChartType
{
	BAR_CHART("Bar Chart"),
	SCATTER_CHART("Scatter Plot"),
	LINE_CHART("Line Chart");
	
	private final String name;
	
	private ChartType(String name)
	{
		this.name = name;
	}
	
	public final String getName()
	{
		return name;
	}
	
	public static ChartType findByName(String name)
	{
		return Stream.of(values())
				.filter(chartType -> chartType.name.equalsIgnoreCase(name))
				.findAny()
				.orElse(BAR_CHART);
	}
}