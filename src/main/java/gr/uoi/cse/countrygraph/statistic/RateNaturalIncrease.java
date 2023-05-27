package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class RateNaturalIncrease implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float rateNaturalIncrease;
	
	@Override
	public Float getStatValue() 
	{
		return rateNaturalIncrease;
	}
}