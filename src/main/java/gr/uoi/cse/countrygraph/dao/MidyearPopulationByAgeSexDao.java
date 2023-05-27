package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.MidYearPopulationByAgeSex;

public final class MidyearPopulationByAgeSexDao implements StatisticDao<MidYearPopulationByAgeSex>
{
	private static final String SELECT_QUERY = "SELECT * FROM midyear_population_by_age_sex WHERE country_id=? AND year BETWEEN ? AND ? AND age BETWEEN ? AND ? AND sex=?";

	@Override
	public List<MidYearPopulationByAgeSex> findAll(MeasureRequest measureRequest) 
	{
		final List<MidYearPopulationByAgeSex> midYearPopulationList = new ArrayList<>();
		
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY))
		{
			preparedStatement.setInt(1, measureRequest.getCountryId());
			preparedStatement.setInt(2, measureRequest.getMinYear());
			preparedStatement.setInt(3, measureRequest.getMaxYear());
			preparedStatement.setInt(4, measureRequest.getMinAge());
			preparedStatement.setInt(5, measureRequest.getMaxAge());
			preparedStatement.setString(6, measureRequest.getSex().toString());
			
			try (final ResultSet resultSet = preparedStatement.executeQuery())
			{
				while (resultSet.next())
				{
					final MidYearPopulationByAgeSex midYearPopulation = MidYearPopulationByAgeSex
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.age(resultSet.getInt("age"))
							.sex(Enum.valueOf(Sex.class, resultSet.getString("sex")))
							.midyearPopulation(resultSet.getFloat("midyear_population"))
							.build();
					
					midYearPopulationList.add(midYearPopulation);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return midYearPopulationList;
	}
	
}