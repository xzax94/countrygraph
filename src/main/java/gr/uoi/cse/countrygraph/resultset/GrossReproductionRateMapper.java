package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.GrossReproductionRate;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class GrossReproductionRateMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return GrossReproductionRate
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.grossReproductionRate(resultSet.getFloat("gross_reproduction_rate"))
				.build();
	}
}