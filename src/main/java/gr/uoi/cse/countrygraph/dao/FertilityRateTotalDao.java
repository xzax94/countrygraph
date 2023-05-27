package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.FertilityRateTotal;

public final class FertilityRateTotalDao implements StatisticDao<FertilityRateTotal>
{
	private static final String SELECT_QUERY = "SELECT * FROM total_fertility_rate WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<FertilityRateTotal> findAll(MeasureRequest measureRequest) 
	{
		final List<FertilityRateTotal> fertilityRateTotalList = new ArrayList<>();
		
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
					final FertilityRateTotal fertilityRateTotal = FertilityRateTotal
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.fertilityRateTotal(resultSet.getFloat("total_fertility_rate"))
							.build();
					
					fertilityRateTotalList.add(fertilityRateTotal);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return fertilityRateTotalList;
	}
	
}