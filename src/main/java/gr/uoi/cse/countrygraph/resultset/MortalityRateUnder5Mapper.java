package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.MortalityRateUnder5;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class MortalityRateUnder5Mapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return MortalityRateUnder5
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.mortalityRateUnder5(resultSet.getFloat("mortality_rate_under_5"))
				.build();
	}
}