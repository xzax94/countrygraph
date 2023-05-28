package gr.uoi.cse.countrygraph.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;

public class TableMetadataDao
{
	private static final String SELECT_QUERY = "SELECT * FROM table_metadata";
	
	public List<TableMetadata> getTableMetadataList()
	{
		final List<TableMetadata> tableMetadataList = new ArrayList<>();
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
				final ResultSet set = statement.executeQuery())
		{
			while (set.next())
			{
				final TableMetadata tableMetadata = TableMetadata
						.builder()
						.measureDescription(set.getString("measure_description"))
						.tableName(set.getString("table_name"))
						.statColumnName(set.getString("stat_column_name"))
						.build();
				
				tableMetadataList.add(tableMetadata);
			}
		}
		catch(final Exception e)
		{
			e.printStackTrace();
		}
		
		return tableMetadataList;
	}
}