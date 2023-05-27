package gr.uoi.cse.countrygraph.country;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CountryCache
{
	private final Map<Integer, Country> countryMap;
	
	private CountryCache(Map<Integer, Country> countryMap)
	{
		this.countryMap = countryMap;
	}
	
	public final void addAll(List<Country> countryList)
	{
		countryList.forEach(country -> countryMap.put(country.getId(), country));
	}
	
	public final Country getCountryById(int countryId)
	{
		return countryMap.get(countryId);
	}
	
	public final Country getCountryByIso(String iso)
	{
		if (iso == null || iso.isEmpty())
			return null;
		
		return getAllCountries()
				.stream()
				.filter(country -> country.getIso() != null && !country.getIso().isEmpty() && country.getIso().equals(iso))
				.findAny()
				.orElse(null);
	}
	
	public final Country getCountryByFips(String fips)
	{
		if (fips == null || fips.isEmpty())
			return null;
		
		return getAllCountries()
				.stream()
				.filter(country -> country.getFips() != null && !country.getFips().isEmpty() && country.getFips().equals(fips))
				.findAny()
				.orElse(null);
	}
	
	public final Country getCountryByIso3(String iso3)
	{
		if (iso3 == null || iso3.isEmpty())
			return null;
		
		return getAllCountries()
				.stream()
				.filter(country -> country.getIso3() != null && !country.getIso3().isEmpty() && country.getIso3().equals(iso3))
				.findAny()
				.orElse(null);
	}
	
	public final Country getCountryByIsoCode(Integer isoCode)
	{
		if (isoCode == null)
			return null;
		
		return getAllCountries()
				.stream()
				.filter(country -> country.getIsoCode() != null && country.getIsoCode().equals(isoCode))
				.findAny()
				.orElse(null);
	}
	
	public final Collection<Country> getAllCountries()
	{
		return countryMap.values();
	}
	
	public final Country getCountryByName(String name)
	{
		if (name == null || name.isEmpty())
			return null;
		
		return getAllCountries()
				.stream()
				.filter(country -> name.equalsIgnoreCase(country.getDisplayName()) || name.equalsIgnoreCase(country.getOfficialName()))
				.findAny()
				.orElse(null);
	}
	
	public static final CountryCache getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final CountryCache INSTANCE = new CountryCache(new HashMap<>());
	}
}