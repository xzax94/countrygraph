package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.EstimatedGNI;

public final class EstimatedGNIDao implements StatisticDao<EstimatedGNI>
{
	private static final String SELECT_QUERY = "SELECT * FROM estimated_gni WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<EstimatedGNI> findAll(MeasureRequest measureRequest) 
	{
		final List<EstimatedGNI> estimatedGniList = new ArrayList<>();
		
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
					final EstimatedGNI estimatedGni = EstimatedGNI
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.estimatedGNI(resultSet.getFloat("estimated_gni"))
							.build();
					
					estimatedGniList.add(estimatedGni);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return estimatedGniList;
	}
	
}