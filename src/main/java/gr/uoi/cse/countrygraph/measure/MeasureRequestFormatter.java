package gr.uoi.cse.countrygraph.measure;

import gr.uoi.cse.countrygraph.country.Country;
import gr.uoi.cse.countrygraph.country.CountryCache;

public final class MeasureRequestFormatter
{
	private MeasureRequestFormatter()
	{
	}
	
	public String formatMeasureRequest(MeasureRequest measureRequest)
	{
		final Country country = CountryCache.getInstance().getCountryById(measureRequest.getCountryId());
		
		if (measureRequest.getMinAge() != null && measureRequest.getSex() != null)
			return String.format("%s %s %s, ages(%d-%d), years(%d-%d)",
					country.getDisplayName(),
					measureRequest.getSex().getName(),
					measureRequest.getTableMetadata().getMeasureDescription(),
					measureRequest.getMinAge(),
					measureRequest.getMaxAge(),
					measureRequest.getMinYear(),
					measureRequest.getMaxYear()
					);  
		
		if (measureRequest.getMinAge() != null)
			return String.format("%s %s, ages(%d-%d), years(%d-%d)",
					country.getDisplayName(),
					measureRequest.getTableMetadata().getMeasureDescription(),
					measureRequest.getMinAge(),
					measureRequest.getMaxAge(),
					measureRequest.getMinYear(),
					measureRequest.getMaxYear()
					);  
		
		if (measureRequest.getSex() != null)
			return String.format("%s %s %s, years(%d-%d)",
					country.getDisplayName(),
					measureRequest.getSex().getName(),
					measureRequest.getTableMetadata().getMeasureDescription(),
					measureRequest.getMinYear(),
					measureRequest.getMaxYear()
					); 
		
		return String.format("%s %s, years(%d-%d)",
				country.getDisplayName(),
				measureRequest.getTableMetadata().getMeasureDescription(),
				measureRequest.getMinYear(),
				measureRequest.getMaxYear()
				); 
	}
	
	public static final MeasureRequestFormatter getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final MeasureRequestFormatter INSTANCE = new MeasureRequestFormatter();
	}
}