package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GNIPerCapita implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float gniPerCapita;
	
	@Override
	public Float getStatValue() 
	{
		return gniPerCapita;
	}
}