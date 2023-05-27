package gr.uoi.cse.countrygraph.measure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Measure
{
	private final String measureDescription;
	private final String tableName;
}