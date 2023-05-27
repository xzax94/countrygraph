package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.LifeExpectancy;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class LifeExpectancyMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return LifeExpectancy
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.lifeExpectancy(resultSet.getFloat("life_expectancy"))
				.build();
	}
}