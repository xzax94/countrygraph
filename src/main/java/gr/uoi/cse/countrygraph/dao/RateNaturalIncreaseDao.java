package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.RateNaturalIncrease;

public final class RateNaturalIncreaseDao implements StatisticDao<RateNaturalIncrease>
{
	private static final String SELECT_QUERY = "SELECT * FROM rate_natural_increase WHERE country_id=? AND year BETWEEN ? and ?";

	@Override
	public List<RateNaturalIncrease> findAll(MeasureRequest measureRequest) 
	{
		final List<RateNaturalIncrease> rateNaturalIncreaseList = new ArrayList<>();
		
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
					final RateNaturalIncrease rateNaturalIncrease = RateNaturalIncrease
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.rateNaturalIncrease(resultSet.getFloat("rate_natural_increase"))
							.build();
					
					rateNaturalIncreaseList.add(rateNaturalIncrease);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return rateNaturalIncreaseList;
	}
	
}