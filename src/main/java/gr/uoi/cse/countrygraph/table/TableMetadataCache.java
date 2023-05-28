package gr.uoi.cse.countrygraph.table;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public final class TableMetadataCache
{
	private final List<TableMetadata> tableMetadataList;
	
	private TableMetadataCache()
	{
		tableMetadataList = new ArrayList<>();
	}
	
	public final TableMetadata getTableMetadataByDescription(String measureDescription)
	{
		return tableMetadataList
				.stream()
				.filter(measure -> measure.getMeasureDescription().equalsIgnoreCase(measureDescription))
				.findAny()
				.orElse(null);
	}
	
	public final TableMetadata getTableMetadataByTableName(String tableName)
	{
		return tableMetadataList
				.stream()
				.filter(measure -> measure.getTableName().equalsIgnoreCase(tableName))
				.findAny()
				.orElse(null);
	}
	
	public static final TableMetadataCache getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final TableMetadataCache INSTANCE = new TableMetadataCache();
	}
}