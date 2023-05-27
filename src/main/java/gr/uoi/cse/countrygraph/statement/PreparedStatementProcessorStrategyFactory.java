package gr.uoi.cse.countrygraph.statement;

import gr.uoi.cse.countrygraph.factory.Factory;

public final class PreparedStatementProcessorStrategyFactory implements Factory<String, PreparedStatementProcessorStrategy>
{
	@Override
	public PreparedStatementProcessorStrategy createNewInstance(String tableName)
	{
		return switch(tableName) {
		case "fertility_rate" -> new ProcessYearAgeStrategy();
		case "estimated_gni",
		"infant_mortality_by_sex",
		"life_expectancy_by_sex",
		"mortality_rate_1_to_4_by_sex",
		"mortality_rate_under_5_by_sex" -> new ProcessYearSexStrategy();
		case "midyear_population_by_age_sex" -> new ProcessYearAgeSexStrategy();
		default -> new ProcessYearStrategy();
		};
	}
}