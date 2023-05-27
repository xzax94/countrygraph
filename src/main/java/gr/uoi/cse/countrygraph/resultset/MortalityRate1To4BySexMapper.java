package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.MortalityRate1To4BySex;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class MortalityRate1To4BySexMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return MortalityRate1To4BySex
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.sex(Enum.valueOf(Sex.class, "sex"))
				.mortalityRate1To4(resultSet.getFloat("mortality_rate_1_to_4"))
				.build();
	}
}