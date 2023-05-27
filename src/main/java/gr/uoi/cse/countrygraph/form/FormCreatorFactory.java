package gr.uoi.cse.countrygraph.form;

import gr.uoi.cse.countrygraph.factory.Factory;
import gr.uoi.cse.countrygraph.measure.Measure;
import gr.uoi.cse.countrygraph.measure.MeasureCache;

public final class FormCreatorFactory implements Factory<String, FormCreator>
{
	@Override
	public FormCreator createNewInstance(String measureDescription) 
	{
		final Measure measure = MeasureCache.getInstance().getMeasureByDescription(measureDescription);
		final String tableName = measure.getTableName();
		
		return switch(tableName) {
		case "estimated_gni",
		"infant_mortality_by_sex",
		"life_expectancy_by_sex",
		"mortality_rate_1_to_4_by_sex",
		"mortality_rate_under_5_by_sex" -> new CountryYearSexFormCreator();
		case "fertility_rate" -> new CountryYearAgeFormCreator();
		case "midyear_population_by_age_sex" -> new CountryYearAgeSexFormCreator();
		default -> new CountryYearFormCreator();
		};
	}
	
}