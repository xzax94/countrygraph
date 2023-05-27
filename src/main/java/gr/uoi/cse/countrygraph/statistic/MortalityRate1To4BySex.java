package gr.uoi.cse.countrygraph.statistic;

import gr.uoi.cse.countrygraph.sex.Sex;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MortalityRate1To4BySex implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Sex sex;
	private Float mortalityRate1To4;
	
	@Override
	public Float getStatValue() 
	{
		return mortalityRate1To4;
	}
}