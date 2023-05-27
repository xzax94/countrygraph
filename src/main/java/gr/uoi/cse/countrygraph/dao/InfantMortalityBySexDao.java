package gr.uoi.cse.countrygraph.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.statistic.InfantMortalityBySex;

public final class InfantMortalityBySexDao implements StatisticDao<InfantMortalityBySex>
{
	private static final String SELECT_QUERY = "SELECT * FROM infant_mortality_by_sex WHERE country_id=? AND year BETWEEN ? AND ? AND sex=?";

	@Override
	public List<InfantMortalityBySex> findAll(MeasureRequest measureRequest) 
	{
		final List<InfantMortalityBySex> infantMortalityList = new ArrayList<>();
		
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
					final InfantMortalityBySex infantMortality = InfantMortalityBySex
							.builder()
							.countryId(resultSet.getInt("country_id"))
							.year(resultSet.getInt("year"))
							.sex(Enum.valueOf(Sex.class, resultSet.getString("sex")))
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