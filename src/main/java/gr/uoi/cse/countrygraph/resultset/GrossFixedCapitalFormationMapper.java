package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.GrossFixedCapitalFormation;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class GrossFixedCapitalFormationMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return GrossFixedCapitalFormation
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.grossFixedCapitalFormation(resultSet.getFloat("gross_fixed_capital_formation"))
				.build();
	}
}