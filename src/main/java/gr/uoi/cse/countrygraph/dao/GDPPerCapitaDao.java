package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.GDPPerCapita;

public final class GDPPerCapitaDao implements StatisticDao<GDPPerCapita>
{
	private static final String SELECT_QUERY = "SELECT * FROM gdp_per_capita WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<GDPPerCapita> findAll(MeasureRequest measureRequest) 
	{
		final List<GDPPerCapita> gdpPerCapitaList = new ArrayList<>();
		
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
					final GDPPerCapita gdpPerCapita = GDPPerCapita
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.gdpPerCapita(resultSet.getFloat("gdp_per_capita"))
							.build();
					
					gdpPerCapitaList.add(gdpPerCapita);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return gdpPerCapitaList;
	}
	
}