package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.SexRatioAtBirth;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class SexRatioAtBirthMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return SexRatioAtBirth
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.sexRatioAtBirth(resultSet.getFloat("sex_ratio_at_birth"))
				.build();
	}
}