package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class FertilityRateTotal implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float fertilityRateTotal;
	
	@Override
	public Float getStatValue() 
	{
		return fertilityRateTotal;
	}
}