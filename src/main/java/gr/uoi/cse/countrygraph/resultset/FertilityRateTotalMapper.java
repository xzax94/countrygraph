package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.FertilityRateTotal;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class FertilityRateTotalMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return FertilityRateTotal
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.fertilityRateTotal(resultSet.getFloat("total_fertility_rate"))
				.build();
	}
}