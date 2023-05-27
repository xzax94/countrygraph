package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.MidyearPopulation;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class MidyearPopulationMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return MidyearPopulation
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.midyearPopulation(resultSet.getFloat("midyear_population"))
				.build();
	}
}