package gr.uoi.cse.countrygraph.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class NetMigration implements Statistic
{
	private Integer countryId;
	private Integer year;
	private Float netMigration;
	
	@Override
	public Float getStatValue()
	{
		return netMigration;
	}
}