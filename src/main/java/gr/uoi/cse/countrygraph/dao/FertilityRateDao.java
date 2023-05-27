package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.FertilityRate;

public final class FertilityRateDao implements StatisticDao<FertilityRate>
{
	private static final String SELECT_QUERY = "SELECT * FROM fertility_rate WHERE country_id=? AND year BETWEEN ? AND ? AND AGE BETWEEN ? AND ?";

	@Override
	public List<FertilityRate> findAll(MeasureRequest measureRequest) 
	{
		final List<FertilityRate> fertilityRateList = new ArrayList<>();
		
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY))
		{
			preparedStatement.setInt(1, measureRequest.getCountryId());
			preparedStatement.setInt(2, measureRequest.getMinYear());
			preparedStatement.setInt(3, measureRequest.getMaxYear());
			preparedStatement.setInt(4, measureRequest.getMinAge());
			preparedStatement.setInt(5, measureRequest.getMaxAge());
			try (final ResultSet resultSet = preparedStatement.executeQuery())
			{
				while (resultSet.next())
				{
					final FertilityRate fertilityRate = FertilityRate
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.age(resultSet.getInt("age"))
							.fertilityRate(resultSet.getFloat("fertility_rate"))
							.build();
					
					fertilityRateList.add(fertilityRate);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return fertilityRateList;
	}
	
}