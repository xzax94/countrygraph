package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.CrudeDeathRate;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class CrudeDeathRateMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return CrudeDeathRate
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.crudeDeathRate(resultSet.getFloat("crude_death_rate"))
				.build();
	}
}