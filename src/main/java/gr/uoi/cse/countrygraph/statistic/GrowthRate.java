package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class GrowthRate implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float growthRate;
	
	@Override
	public Float getStatValue() 
	{
		return growthRate;
	}
}