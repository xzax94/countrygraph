package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.DomesticCredits;

public final class DomesticCreditsDao implements StatisticDao<DomesticCredits>
{
	private static final String SELECT_QUERY = "SELECT * FROM domestic_credits WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<DomesticCredits> findAll(MeasureRequest measureRequest) 
	{
		final List<DomesticCredits> domesticCreditsList = new ArrayList<>();
		
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
					final DomesticCredits domesticCredits = DomesticCredits
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.domesticCredits(resultSet.getFloat("domestic_credits"))
							.build();
					
					domesticCreditsList.add(domesticCredits);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return domesticCreditsList;
	}
	
}