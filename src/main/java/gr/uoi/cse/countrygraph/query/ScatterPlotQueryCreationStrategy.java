package gr.uoi.cse.countrygraph.query;

import java.util.List;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;

public final class ScatterPlotQueryCreationStrategy implements QueryCreationStrategy
{
	private static final String QUERY_FORMAT = "SELECT m1.year AS year, m1.%s AS stat1, m2.%s AS stat2 FROM %s m1 JOIN %s m2 ON m1.year = m2.year WHERE %s AND %s AND m1.year BETWEEN %d AND %d;";
	private static final int FIRST_MEASURE_INDEX = 0;
	private static final int SECOND_MEASURE_INDEX = 1;
	
	@Override
	public String createQuery(List<MeasureRequest> measureRequestList, int aggregateDivider)
	{
		final MeasureRequest firstMeasureRequest = measureRequestList.get(FIRST_MEASURE_INDEX);
		final MeasureRequest secondMeasureRequest = measureRequestList.get(SECOND_MEASURE_INDEX);
		
		final Integer minYear = Math.max(firstMeasureRequest.getMinYear(), secondMeasureRequest.getMinYear());
		final Integer maxYear = Math.min(firstMeasureRequest.getMaxYear(), secondMeasureRequest.getMaxYear());
		final WhereClauseFormatter whereClauseFormatter = new WhereClauseFormatter();
		
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format(QUERY_FORMAT,
				firstMeasureRequest.getTableMetadata().getStatColumnName(),
				secondMeasureRequest.getTableMetadata().getStatColumnName(),
				firstMeasureRequest.getTableMetadata().getTableName(),
				secondMeasureRequest.getTableMetadata().getTableName(),
				whereClauseFormatter.formatWhereClause(firstMeasureRequest, "m1"),
				whereClauseFormatter.formatWhereClause(secondMeasureRequest, "m2"),
				minYear,
				maxYear));
		
		return stringBuilder.toString();
	}
}