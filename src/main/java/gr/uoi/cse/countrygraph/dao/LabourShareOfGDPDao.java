package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.LabourShareOfGDP;

public final class LabourShareOfGDPDao implements StatisticDao<LabourShareOfGDP>
{
	private static final String SELECT_QUERY = "SELECT * FROM labour_share_of_gdp WHERE country_id=? AND year BETWEEN ? AND ?";

	@Override
	public List<LabourShareOfGDP> findAll(MeasureRequest measureRequest) 
	{
		final List<LabourShareOfGDP> labourShareOfGDPList = new ArrayList<>();
		
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
					final LabourShareOfGDP labourShareOfGDP = LabourShareOfGDP
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.labourShareOfGDP(resultSet.getFloat("labour_share_of_gdp"))
							.build();
					
					labourShareOfGDPList.add(labourShareOfGDP);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return labourShareOfGDPList;
	}
	
}