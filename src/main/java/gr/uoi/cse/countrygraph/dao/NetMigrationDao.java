package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.NetMigration;

public final class NetMigrationDao implements StatisticDao<NetMigration>
{
	private static final String SELECT_QUERY = "SELECT * FROM net_migration WHERE country_id=? AND year BETWEEN ? and ?";

	@Override
	public List<NetMigration> findAll(MeasureRequest measureRequest) 
	{
		final List<NetMigration> netMigrationList = new ArrayList<>();
		
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY))
		{
			preparedStatement.setInt(1, measureRequest.getCountryId());
			preparedStatement.setInt(2, measureRequest.getMinYear());
			preparedStatement.setInt(3, measureRequest.getMaxYear());
			try (final ResultSet resultSet = preparedStatement.executeQuery())
			{
				while (resultSet.next())
				{
					final NetMigration netMigration = NetMigration
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.netMigration(resultSet.getFloat("net_migration"))
							.build();
					
					netMigrationList.add(netMigration);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return netMigrationList;
	}
	
}