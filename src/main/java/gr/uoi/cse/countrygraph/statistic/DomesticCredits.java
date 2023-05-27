package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DomesticCredits implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float domesticCredits;
	
	@Override
	public Float getStatValue()
	{
		return domesticCredits;
	}
}