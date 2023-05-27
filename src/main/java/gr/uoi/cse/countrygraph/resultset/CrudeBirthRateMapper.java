package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.CrudeBirthRate;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class CrudeBirthRateMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return CrudeBirthRate
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.crudeBirthRate(resultSet.getFloat("crude_birth_rate"))
				.build();
	}
}