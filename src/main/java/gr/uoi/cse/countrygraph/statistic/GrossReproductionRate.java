package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class GrossReproductionRate implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float grossReproductionRate;
	
	@Override
	public Float getStatValue() 
	{
		return grossReproductionRate;
	}
}