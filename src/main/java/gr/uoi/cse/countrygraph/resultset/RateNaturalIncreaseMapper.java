package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.RateNaturalIncrease;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class RateNaturalIncreaseMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return RateNaturalIncrease
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.rateNaturalIncrease(resultSet.getFloat("rate_natural_increase"))
				.build();
	}
}