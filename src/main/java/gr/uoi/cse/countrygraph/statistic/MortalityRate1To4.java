package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MortalityRate1To4 implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float mortalityRate1To4;
	
	@Override
	public Float getStatValue() 
	{
		return mortalityRate1To4;
	}
}