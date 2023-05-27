package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.GNIPerCapita;

public final class GNIPerCapitaDao implements StatisticDao<GNIPerCapita>
{
	private static final String SELECT_QUERY = "SELECT * FROM gni_per_capita WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<GNIPerCapita> findAll(MeasureRequest measureRequest) 
	{
		final List<GNIPerCapita> gniPerCapitaList = new ArrayList<>();
		
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
					final GNIPerCapita gniPerCapita = GNIPerCapita
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.gniPerCapita(resultSet.getFloat("gni_per_capita"))
							.build();
					
					gniPerCapitaList.add(gniPerCapita);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return gniPerCapitaList;
	}
	
}