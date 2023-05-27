package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class SexRatioAtBirth implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float sexRatioAtBirth;
	
	@Override
	public Float getStatValue() 
	{
		return sexRatioAtBirth;
	}
}