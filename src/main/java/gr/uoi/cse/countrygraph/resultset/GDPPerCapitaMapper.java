package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.GDPPerCapita;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class GDPPerCapitaMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return GDPPerCapita
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.gdpPerCapita(resultSet.getFloat("gdp_per_capita"))
				.build();
	}
}