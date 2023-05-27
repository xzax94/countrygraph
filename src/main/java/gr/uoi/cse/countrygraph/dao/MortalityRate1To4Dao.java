package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.MortalityRate1To4;

public final class MortalityRate1To4Dao implements StatisticDao<MortalityRate1To4>
{
	private static final String SELECT_QUERY = "SELECT * FROM mortality_rate_1_to_4 WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<MortalityRate1To4> findAll(MeasureRequest measureRequest) 
	{
		final List<MortalityRate1To4> mortalityRate1To4List = new ArrayList<>();
		
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
					final MortalityRate1To4 mortalityRate1To4 = MortalityRate1To4
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.mortalityRate1To4(resultSet.getFloat("mortality_rate_1_to_4"))
							.build();
					
					mortalityRate1To4List.add(mortalityRate1To4);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return mortalityRate1To4List;
	}
	
}