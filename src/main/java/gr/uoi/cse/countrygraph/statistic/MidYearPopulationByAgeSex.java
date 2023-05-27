package gr.uoi.cse.countrygraph.statistic;

import gr.uoi.cse.countrygraph.sex.Sex;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class MidYearPopulationByAgeSex implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Integer age;
	private Sex sex;
	private Float midyearPopulation;
	
	@Override
	public Float getStatValue() 
	{
		return midyearPopulation;
	}
}