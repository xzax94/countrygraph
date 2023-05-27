package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.FertilityRate;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class FertilityRateMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return FertilityRate
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.age(resultSet.getInt("age"))
				.fertilityRate(resultSet.getFloat("fertility_rate"))
				.build();
	}
}