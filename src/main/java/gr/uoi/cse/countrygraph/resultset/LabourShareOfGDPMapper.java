package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.LabourShareOfGDP;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class LabourShareOfGDPMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return LabourShareOfGDP
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.labourShareOfGDP(resultSet.getFloat("labour_share_of_gdp"))
				.build();
	}
}