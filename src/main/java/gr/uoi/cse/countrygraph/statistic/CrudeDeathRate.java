package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrudeDeathRate implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float crudeDeathRate;
	
	@Override
	public Float getStatValue() 
	{
		return crudeDeathRate;
	}
}