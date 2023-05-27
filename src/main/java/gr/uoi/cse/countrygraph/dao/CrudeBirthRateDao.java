package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.CrudeBirthRate;

public final class CrudeBirthRateDao implements StatisticDao<CrudeBirthRate>
{
	private static final String SELECT_QUERY = "SELECT * FROM crude_birth_rate WHERE country_id=? AND year BETWEEN ? and ?";

	@Override
	public List<CrudeBirthRate> findAll(MeasureRequest measureRequest) 
	{
		final List<CrudeBirthRate> crudeBirthRateList = new ArrayList<>();
		
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
					final CrudeBirthRate crudeBirthRate = CrudeBirthRate
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.crudeBirthRate(resultSet.getFloat("crude_birth_rate"))
							.build();
					
					crudeBirthRateList.add(crudeBirthRate);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return crudeBirthRateList;
	}
	
}