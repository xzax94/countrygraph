package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MortalityRateUnder5 implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float mortalityRateUnder5;
	
	@Override
	public Float getStatValue() 
	{
		return mortalityRateUnder5;
	}
}