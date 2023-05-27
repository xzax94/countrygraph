package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.MidyearPopulation;

public final class MidyearPopulationDao implements StatisticDao<MidyearPopulation>
{
	private static final String SELECT_QUERY = "SELECT * FROM midyear_population WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<MidyearPopulation> findAll(MeasureRequest measureRequest) 
	{
		final List<MidyearPopulation> midyearPopulationList = new ArrayList<>();
		
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
					final MidyearPopulation midyearPopulation = MidyearPopulation
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.midyearPopulation(resultSet.getFloat("midyear_population"))
							.build();
					
					midyearPopulationList.add(midyearPopulation);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return midyearPopulationList;
	}
	
}