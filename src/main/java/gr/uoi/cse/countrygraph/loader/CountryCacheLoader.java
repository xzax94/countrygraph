package gr.uoi.cse.countrygraph.loader;

import java.util.List;

import gr.uoi.cse.countrygraph.country.Country;
import gr.uoi.cse.countrygraph.country.CountryCache;
import gr.uoi.cse.countrygraph.country.CountryDao;

public final class CountryCacheLoader implements ApplicationLoader
{
	@Override
	public void load() 
	{
		final CountryDao countryDao = new CountryDao();
    	final List<Country> countryList = countryDao.loadCountries();
    	CountryCache.getInstance().addAll(countryList);
	}
}