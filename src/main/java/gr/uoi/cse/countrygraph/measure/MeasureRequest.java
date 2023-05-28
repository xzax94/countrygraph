package gr.uoi.cse.countrygraph.measure;

import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.table.TableMetadata;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class MeasureRequest implements Comparable<MeasureRequest>
{
	private Integer countryId;
	private Integer minYear;
	private Integer maxYear;
	private Integer minAge;
	private Integer maxAge;
	private Sex sex;
	private TableMetadata tableMetadata;
	
	@Override
	public int compareTo(MeasureRequest measureRequest) 
	{
		return Integer.compare(getMinYear(), measureRequest.getMinYear());
	}
}