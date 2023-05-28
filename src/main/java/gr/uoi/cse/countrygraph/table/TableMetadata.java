package gr.uoi.cse.countrygraph.table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class TableMetadata
{
	private final String measureDescription;
	private final String tableName;
	private final String statColumnName;
}