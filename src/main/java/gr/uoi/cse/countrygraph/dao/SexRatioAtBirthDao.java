package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.SexRatioAtBirth;

public final class SexRatioAtBirthDao implements StatisticDao<SexRatioAtBirth>
{
	private static final String SELECT_QUERY = "SELECT * FROM sex_ratio_at_birth WHERE country_id=? AND year BETWEEN ? and ?";

	@Override
	public List<SexRatioAtBirth> findAll(MeasureRequest measureRequest) 
	{
		final List<SexRatioAtBirth> sexRatioAtBirthList = new ArrayList<>();
		
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
					final SexRatioAtBirth sexRatioAtBirth = SexRatioAtBirth
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.sexRatioAtBirth(resultSet.getFloat("sex_ratio_at_birth"))
							.build();
					
					sexRatioAtBirthList.add(sexRatioAtBirth);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return sexRatioAtBirthList;
	}
	
}