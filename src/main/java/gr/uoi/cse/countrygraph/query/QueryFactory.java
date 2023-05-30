package gr.uoi.cse.countrygraph.query;

import gr.uoi.cse.countrygraph.factory.Factory;

public final class QueryFactory implements Factory<String, String>
{
	private static final String QUERY_BY_YEAR_FORMAT = "SELECT * FROM %s WHERE country_id=? AND year BETWEEN ? and ?";
	private static final String QUERY_BY_YEAR_AGE_FORMAT = "SELECT * FROM %s WHERE country_id=? AND year BETWEEN ? AND ? AND age=?";
	private static final String QUERY_BY_YEAR_SEX_FORMAT = "SELECT * FROM %s WHERE country_id=? AND year BETWEEN ? AND ? AND sex=?";
	private static final String QUERY_BY_YEAR_AGE_SEX_FORMAT = "SELECT * FROM midyear_population_by_age_sex WHERE country_id=? AND year BETWEEN ? AND ? AND age=? AND sex=?";
	
	@Override
	public String createNewInstance(String tableName)
	{
		return switch(tableName) {
		case "fertility_rate" -> String.format(QUERY_BY_YEAR_AGE_FORMAT, tableName);
		case "estimated_gni",
		"infant_mortality_by_sex",
		"life_expectancy_by_sex",
		"mortality_rate_1_to_4_by_sex",
		"mortality_rate_under_5_by_sex" -> String.format(QUERY_BY_YEAR_SEX_FORMAT, tableName);
		case "midyear_population_by_age_sex" -> String.format(QUERY_BY_YEAR_AGE_SEX_FORMAT, tableName);
		default -> String.format(QUERY_BY_YEAR_FORMAT, tableName);
		};
	}
}