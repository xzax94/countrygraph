package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.MortalityRateUnder5BySex;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class MortalityRateUnder5BySexMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return MortalityRateUnder5BySex
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.sex(Enum.valueOf(Sex.class, resultSet.getString("sex")))
				.mortalityRateUnder5(resultSet.getFloat("mortality_rate_under_5"))
				.build();
	}
}