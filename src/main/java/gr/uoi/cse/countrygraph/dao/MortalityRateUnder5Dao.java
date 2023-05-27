package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.MortalityRateUnder5;

public final class MortalityRateUnder5Dao implements StatisticDao<MortalityRateUnder5>
{
	private static final String SELECT_QUERY = "SELECT * FROM mortality_rate_under_5 WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<MortalityRateUnder5> findAll(MeasureRequest measureRequest) 
	{
		final List<MortalityRateUnder5> mortalityRateUnder5List = new ArrayList<>();
		
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
					final MortalityRateUnder5 mortalityRateUnder5 = MortalityRateUnder5
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.mortalityRateUnder5(resultSet.getFloat("mortality_rate_under_5"))
							.build();
					
					mortalityRateUnder5List.add(mortalityRateUnder5);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return mortalityRateUnder5List;
	}
	
}