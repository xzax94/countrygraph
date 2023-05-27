package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.InfantMortalityBySex;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class InfantMortalityBySexMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return InfantMortalityBySex
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.sex(Enum.valueOf(Sex.class, resultSet.getString("sex")))
				.infantMortality(resultSet.getFloat("infant_mortality"))
				.build();
	}
}