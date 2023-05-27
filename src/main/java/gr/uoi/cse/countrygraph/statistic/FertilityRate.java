package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class FertilityRate implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Integer age;
	private Float fertilityRate;
	
	@Override
	public Float getStatValue() 
	{
		return fertilityRate;
	}
}