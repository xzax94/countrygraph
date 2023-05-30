package gr.uoi.cse.countrygraph.query;

import java.util.List;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;

public final class LineChartQueryCreationStrategy implements QueryCreationStrategy
{
	private static final String QUERY_FORMAT = "SELECT country_id, MIN(year) AS min_year, MAX(year) AS max_year,AVG(%s) AS average_stat FROM %s measure WHERE %s AND measure.year BETWEEN %d AND %d GROUP BY country_id, FLOOR((year-1)/%d) ORDER BY country_id, FLOOR((year-1)/%d)";
	private static final String TABLE_ALIAS = "measure";

	@Override
	public final String createQuery(List<MeasureRequest> measureRequestList, int aggregateDivider)
	{
		final MeasureRequest measureRequest = measureRequestList.get(0);
		final StringBuilder stringBuilder = new StringBuilder();
		final String statName = measureRequest.getTableMetadata().getStatColumnName();
		final String tableName = measureRequest.getTableMetadata().getTableName();
		final WhereClauseFormatter whereClauseFormatter = new WhereClauseFormatter();
		final String whereClause = whereClauseFormatter.formatWhereClause(measureRequest, TABLE_ALIAS);
		stringBuilder.append(String.format(QUERY_FORMAT, statName, tableName, whereClause, measureRequest.getMinYear(), measureRequest.getMaxYear(), aggregateDivider, aggregateDivider));

		return stringBuilder.toString();
	}
}