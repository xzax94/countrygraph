package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.NetMigration;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class NetMigrationMapper implements ResultSetMapper
{
	@Override
	public Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException 
	{
		return NetMigration
				.builder()
				.countryId(resultSet.getInt("country_id"))
				.year(resultSet.getInt("year"))
				.netMigration(resultSet.getFloat("net_migration"))
				.build();
	}
}