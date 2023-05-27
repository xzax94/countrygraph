package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.GNIPerCapita;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class GNIPerCapitaMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return GNIPerCapita
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.gniPerCapita(resultSet.getFloat("gni_per_capita"))
				.build();
	}
}