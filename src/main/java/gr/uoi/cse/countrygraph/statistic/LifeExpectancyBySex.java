package gr.uoi.cse.countrygraph.statistic;

import gr.uoi.cse.countrygraph.sex.Sex;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LifeExpectancyBySex  implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Sex sex;
	private Float lifeExpectancy;
	
	@Override
	public Float getStatValue() 
	{
		return lifeExpectancy;
	}
}