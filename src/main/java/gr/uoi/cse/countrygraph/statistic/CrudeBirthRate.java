package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrudeBirthRate implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float crudeBirthRate;
	
	@Override
	public Float getStatValue() 
	{
		return crudeBirthRate;
	}
}