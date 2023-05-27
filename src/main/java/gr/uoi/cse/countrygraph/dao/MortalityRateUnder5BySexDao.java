package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.MortalityRateUnder5BySex;

public final class MortalityRateUnder5BySexDao implements StatisticDao<MortalityRateUnder5BySex>
{
	private static final String SELECT_QUERY = "SELECT * FROM mortality_rate_under_5_by_sex WHERE country_id=? AND year BETWEEN ? AND ? AND sex=?";

	@Override
	public List<MortalityRateUnder5BySex> findAll(MeasureRequest measureRequest) 
	{
		final List<MortalityRateUnder5BySex> mortalityRateUnder5BySexList = new ArrayList<>();
		
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY))
		{
			preparedStatement.setInt(1, measureRequest.getCountryId());
			preparedStatement.setInt(2, measureRequest.getMinYear());
			preparedStatement.setInt(3, measureRequest.getMaxYear());
			preparedStatement.setString(4, measureRequest.getSex().toString());
			try (final ResultSet resultSet = preparedStatement.executeQuery())
			{
				while (resultSet.next())
				{
					final MortalityRateUnder5BySex mortalityRateUnder5BySex = MortalityRateUnder5BySex
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.sex(Enum.valueOf(Sex.class, resultSet.getString("sex")))
							.mortalityRateUnder5(resultSet.getFloat("mortality_rate_under_5"))
							.build();
					
					mortalityRateUnder5BySexList.add(mortalityRateUnder5BySex);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return mortalityRateUnder5BySexList;
	}
	
}