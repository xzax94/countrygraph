package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.GrossReproductionRate;

public final class GrossReproductionRateDao implements StatisticDao<GrossReproductionRate>
{
	private static final String SELECT_QUERY = "SELECT * FROM gross_reproduction_rate WHERE country_id=? AND year BETWEEN ? and ?";

	@Override
	public List<GrossReproductionRate> findAll(MeasureRequest measureRequest) 
	{
		final List<GrossReproductionRate> grossReproductionRateList = new ArrayList<>();
		
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
					final GrossReproductionRate grossReproductionRate = GrossReproductionRate
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.grossReproductionRate(resultSet.getFloat("gross_reproduction_rate"))
							.build();
					
					grossReproductionRateList.add(grossReproductionRate);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return grossReproductionRateList;
	}
	
}