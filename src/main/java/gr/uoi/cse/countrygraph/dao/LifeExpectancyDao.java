package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.LifeExpectancy;

public final class LifeExpectancyDao implements StatisticDao<LifeExpectancy>
{
	private static final String SELECT_QUERY = "SELECT * FROM life_expectancy WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<LifeExpectancy> findAll(MeasureRequest measureRequest) 
	{
		final List<LifeExpectancy> lifeExpectancyList = new ArrayList<>();
		
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
					final LifeExpectancy lifeExpectancy = LifeExpectancy
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.lifeExpectancy(resultSet.getFloat("life_expectancy"))
							.build();
					
					lifeExpectancyList.add(lifeExpectancy);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return lifeExpectancyList;
	}
	
}