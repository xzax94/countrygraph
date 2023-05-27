package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.MortalityRate1To4;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class MortalityRate1To4Mapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return MortalityRate1To4
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.mortalityRate1To4(resultSet.getFloat("mortality_rate_1_to_4"))
				.build();
	}
}