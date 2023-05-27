package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class GrossFixedCapitalFormation implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float grossFixedCapitalFormation;
	
	@Override
	public Float getStatValue() 
	{
		return grossFixedCapitalFormation;
	}
}