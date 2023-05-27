package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.GrowthRate;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class GrowthRateMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return GrowthRate
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.growthRate(resultSet.getFloat("growth_rate"))
				.build();
	}
}