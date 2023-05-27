package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfantMortality implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float infantMortality;
	
	@Override
	public Float getStatValue()
	{
		return infantMortality;
	}
}