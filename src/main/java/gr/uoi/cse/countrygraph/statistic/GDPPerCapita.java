package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GDPPerCapita implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float gdpPerCapita;
	
	@Override
	public Float getStatValue() 
	{
		return gdpPerCapita;
	}
}