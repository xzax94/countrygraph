package gr.uoi.cse.countrygraph.measure;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public final class MeasureCache
{
	private final List<Measure> measureList;
	
	private MeasureCache()
	{
		measureList = new ArrayList<>();
	}
	
	public final Measure getMeasureByDescription(String measureDescription)
	{
		return measureList
				.stream()
				.filter(measure -> measure.getMeasureDescription().equalsIgnoreCase(measureDescription))
				.findAny()
				.orElse(null);
	}
	
	public final Measure getMeasureByTableName(String tableName)
	{
		return measureList
				.stream()
				.filter(measure -> measure.getTableName().equalsIgnoreCase(tableName))
				.findAny()
				.orElse(null);
	}
	
	public static final MeasureCache getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final MeasureCache INSTANCE = new MeasureCache();
	}
}