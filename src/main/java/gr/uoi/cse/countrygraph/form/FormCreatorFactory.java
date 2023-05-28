package gr.uoi.cse.countrygraph.form;

import gr.uoi.cse.countrygraph.factory.Factory;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import gr.uoi.cse.countrygraph.table.TableMetadata;

public final class FormCreatorFactory implements Factory<String, FormCreator>
{
	@Override
	public FormCreator createNewInstance(String measureDescription) 
	{
		final TableMetadata tableMetadata = TableMetadataCache.getInstance().getTableMetadataByDescription(measureDescription);
		final String tableName = tableMetadata.getTableName();
		
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