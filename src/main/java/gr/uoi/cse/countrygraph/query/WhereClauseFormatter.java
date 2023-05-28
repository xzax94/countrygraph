package gr.uoi.cse.countrygraph.query;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;

public final class WhereClauseFormatter
{
	private static final String COUNTRY_ID_FORMAT = "%s.country_id=%d ";
	private static final String SEX_FORMAT = "AND %s.sex=%s ";
	private static final String AGE_FORMAT = "AND %s.age BETWEEN %d and %d";
	
	public String formatWhereClause(MeasureRequest measureRequest, String tableAlias) 
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(String.format(COUNTRY_ID_FORMAT, tableAlias, measureRequest.getCountryId()));
		
		if (measureRequest.getSex() != null)
			builder.append(String.format(SEX_FORMAT, tableAlias, measureRequest.getSex().toString()));
		
		if (measureRequest.getMinAge() != null)
			builder.append(String.format(AGE_FORMAT, tableAlias, measureRequest.getMinAge(), measureRequest.getMaxAge()));
		
		return builder.toString();
	}
}