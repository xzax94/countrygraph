package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.GDPTotal;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class GDPTotalMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return GDPTotal
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.gdpTotal(resultSet.getFloat("gdp_total"))
				.build();
	}
}