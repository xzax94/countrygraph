package gr.uoi.cse.countrygraph.statistic;

import gr.uoi.cse.countrygraph.sex.Sex;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstimatedGNI implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Sex sex;
	private Float estimatedGNI;
	
	@Override
	public Float getStatValue() 
	{
		return estimatedGNI;
	}
}