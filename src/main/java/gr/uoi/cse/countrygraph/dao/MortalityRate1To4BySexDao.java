package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.MortalityRate1To4BySex;

public final class MortalityRate1To4BySexDao implements StatisticDao<MortalityRate1To4BySex>
{
	private static final String SELECT_QUERY = "SELECT * FROM mortality_rate_1_to_4_by_sex WHERE country_id=? AND year BETWEEN ? AND ? AND sex=?";

	@Override
	public List<MortalityRate1To4BySex> findAll(MeasureRequest measureRequest) 
	{
		final List<MortalityRate1To4BySex> mortalityRate1To4BySexList = new ArrayList<>();
		
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
					final MortalityRate1To4BySex mortalityRate1To4BySex = MortalityRate1To4BySex
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.sex(Enum.valueOf(Sex.class, "sex"))
							.mortalityRate1To4(resultSet.getFloat("mortality_rate_1_to_4"))
							.build();
					
					mortalityRate1To4BySexList.add(mortalityRate1To4BySex);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return mortalityRate1To4BySexList;
	}
	
}