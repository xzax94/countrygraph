package gr.uoi.cse.countrygraph.resultset;

import gr.uoi.cse.countrygraph.factory.Factory;

public final class ResultSetMapperFactory implements Factory<String, ResultSetMapper>
{

	@Override
	public ResultSetMapper createNewInstance(String tableName) 
	{
		return switch(tableName.intern()) {
		case "crude_birth_rate" -> new CrudeBirthRateMapper();
		case "crude_death_rate" -> new CrudeDeathRateMapper();
		case "domestic_credits" -> new DomesticCreditsMapper();
		case "estimated_gni" -> new EstimatedGNIMapper();
		case "fertility_rate" -> new FertilityRateMapper();
		case "total_fertility_rate" -> new FertilityRateTotalMapper();
		case "gdp_per_capita" -> new GDPPerCapitaMapper();
		case "gdp_total" -> new GDPTotalMapper();
		case "gni_per_capita" -> new GNIPerCapitaMapper();
		case "gross_fixed_capital_formation" -> new GrossFixedCapitalFormationMapper();
		case "gross_reproduction_rate" -> new GrossReproductionRateMapper();
		case "growth_rate" -> new GrowthRateMapper();
		case "income_index" -> new IncomeIndexMapper();
		case "infant_mortality_by_sex" -> new InfantMortalityBySexMapper();
		case "infant_mortality" -> new InfantMortalityMapper();
		case "labour_share_of_gdp" -> new LabourShareOfGDPMapper();
		case "life_expectancy_by_sex" -> new LifeExpectancyBySexMapper();
		case "life_expectancy" -> new LifeExpectancyMapper();
		case "midyear_population_by_age_sex" -> new MidyearPopulationByAgeSexMapper();
		case "midyear_population" -> new MidyearPopulationMapper();
		case "mortality_rate_1_to_4_by_sex" -> new MortalityRate1To4BySexMapper();
		case "mortality_rate_1_to_4" -> new MortalityRate1To4Mapper();
		case "mortality_rate_under_5_by_sex" -> new MortalityRateUnder5BySexMapper();
		case "mortality_rate_under_5" -> new MortalityRateUnder5Mapper();
		case "net_migration" -> new NetMigrationMapper();
		case "rate_natural_increase" -> new RateNaturalIncreaseMapper();
		case "sex_ratio_at_birth" -> new SexRatioAtBirthMapper();
		default -> null;
		};
	}
}