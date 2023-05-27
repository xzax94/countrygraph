package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabourShareOfGDP implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float labourShareOfGDP;
	
	@Override
	public Float getStatValue() 
	{
		return labourShareOfGDP;
	}
}