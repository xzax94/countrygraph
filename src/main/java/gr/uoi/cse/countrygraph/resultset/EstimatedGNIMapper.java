package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.EstimatedGNI;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class EstimatedGNIMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return EstimatedGNI
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.estimatedGNI(resultSet.getFloat("estimated_gni"))
				.build();
	}
}