package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.InfantMortality;

public final class InfantMortalityDao implements StatisticDao<InfantMortality>
{
	private static final String SELECT_QUERY = "SELECT * FROM infant_mortality WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<InfantMortality> findAll(MeasureRequest measureRequest) 
	{
		final List<InfantMortality> infantMortalityList = new ArrayList<>();
		
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
					final InfantMortality infantMortality = InfantMortality
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.infantMortality(resultSet.getFloat("infant_mortality"))
							.build();
					
					infantMortalityList.add(infantMortality);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return infantMortalityList;
	}
	
}