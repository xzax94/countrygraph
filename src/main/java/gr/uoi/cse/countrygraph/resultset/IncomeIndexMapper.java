package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.IncomeIndex;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class IncomeIndexMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return IncomeIndex
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.incomeIndex(resultSet.getFloat("income_index"))
				.build();
	}
}