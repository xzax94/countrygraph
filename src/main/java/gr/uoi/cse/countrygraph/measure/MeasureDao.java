package gr.uoi.cse.countrygraph.measure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;

public class MeasureDao
{
	private static final String SELECT_QUERY = "SELECT * FROM measures";
	
	public List<Measure> getMeasureList()
	{
		final List<Measure> measureList = new ArrayList<>();
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
				final ResultSet set = statement.executeQuery())
		{
			while (set.next())
			{
				final Measure measure = Measure
						.builder()
						.measureDescription(set.getString("measure_description"))
						.tableName(set.getString("table_name"))
						.build();
				
				measureList.add(measure);
			}
		}
		catch(final Exception e)
		{
			e.printStackTrace();
		}
		
		return measureList;
	}
}