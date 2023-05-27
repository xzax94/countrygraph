package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.DomesticCredits;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class DomesticCreditsMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return DomesticCredits
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.domesticCredits(resultSet.getFloat("domestic_credits"))
				.build();
	}
}