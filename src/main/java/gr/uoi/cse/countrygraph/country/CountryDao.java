package gr.uoi.cse.countrygraph.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;

public class CountryDao
{
	private static final String SELECT_QUERY = "SELECT * FROM countries";
	
	public List<Country> loadCountries()
	{
		final List<Country> countryList = new ArrayList<>();
		try (final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
				final ResultSet set = statement.executeQuery())
		{
			while (set.next())
			{
				final Country country = Country
						.builder()
						.id(set.getInt("country_id"))
						.displayName(set.getString("display_name"))
						.build();
				
				countryList.add(country);
			}
		}
		catch (final Exception e) 
		{
			e.printStackTrace();
		}
		
		return countryList;
	}
}