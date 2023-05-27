package gr.uoi.cse.countrygraph.measure;

import gr.uoi.cse.countrygraph.country.Country;
import gr.uoi.cse.countrygraph.country.CountryCache;

public final class MeasureRequestFormatter
{
	public String formatMeasureRequest(MeasureRequest measureRequest)
	{
		final Country country = CountryCache.getInstance().getCountryById(measureRequest.getCountryId());
		
		if (measureRequest.getMinAge() != null && measureRequest.getSex() != null)
			return String.format("%s %s %s, ages(%d-%d), years(%d-%d)",
					country.getDisplayName(),
					measureRequest.getSex().getName(),
					measureRequest.getMeasureDescription(),
					measureRequest.getMinAge(),
					measureRequest.getMaxAge(),
					measureRequest.getMinYear(),
					measureRequest.getMaxYear()
					);  
		
		if (measureRequest.getMinAge() != null)
			return String.format("%s %s, ages(%d-%d), years(%d-%d)",
					country.getDisplayName(),
					measureRequest.getMeasureDescription(),
					measureRequest.getMinAge(),
					measureRequest.getMaxAge(),
					measureRequest.getMinYear(),
					measureRequest.getMaxYear()
					);  
		
		if (measureRequest.getSex() != null)
			return String.format("%s %s %s, years(%d-%d)",
					country.getDisplayName(),
					measureRequest.getSex().getName(),
					measureRequest.getMeasureDescription(),
					measureRequest.getMinYear(),
					measureRequest.getMaxYear()
					); 
		
		return String.format("%s %s, years(%d-%d)",
				country.getDisplayName(),
				measureRequest.getMeasureDescription(),
				measureRequest.getMinYear(),
				measureRequest.getMaxYear()
				); 
	}
}