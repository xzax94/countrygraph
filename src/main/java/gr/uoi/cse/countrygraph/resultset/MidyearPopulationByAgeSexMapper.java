package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.MidYearPopulationByAgeSex;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class MidyearPopulationByAgeSexMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return MidYearPopulationByAgeSex
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.age(resultSet.getInt("age"))
				.sex(Enum.valueOf(Sex.class, resultSet.getString("sex")))
				.midyearPopulation(resultSet.getFloat("midyear_population"))
				.build();
	}
}