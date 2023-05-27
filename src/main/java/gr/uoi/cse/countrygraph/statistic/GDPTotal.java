package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GDPTotal implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float gdpTotal;
	
	@Override
	public Float getStatValue() 
	{
		return gdpTotal;
	}
}