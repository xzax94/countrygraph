package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class MidyearPopulation implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float midyearPopulation;
	
	@Override
	public Float getStatValue() 
	{
		return midyearPopulation;
	}
}