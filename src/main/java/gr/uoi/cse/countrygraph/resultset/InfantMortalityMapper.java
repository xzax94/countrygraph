package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.InfantMortality;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class InfantMortalityMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return InfantMortality
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.infantMortality(resultSet.getFloat("infant_mortality"))
				.build();
	}
}