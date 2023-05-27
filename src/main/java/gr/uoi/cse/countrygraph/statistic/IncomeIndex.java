package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeIndex implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float incomeIndex;
	
	@Override
	public Float getStatValue() 
	{
		return incomeIndex;
	}
	
	
}