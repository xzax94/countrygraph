package gr.uoi.cse.countrygraph.dao;

import gr.uoi.cse.countrygraph.factory.Factory;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class StatisticDaoFactory implements Factory<String, StatisticDao<? extends Statistic>>
{
	@Override
	public StatisticDao<? extends Statistic> createNewInstance(String tableName) 
	{
		return switch (tableName) {
		case "crude_birth_rate" -> new CrudeBirthRateDao();
		case "crude_death_rate" -> new CrudeDeathRateDao();
		case "domestic_credits" -> new DomesticCreditsDao();
		case "estimated_gni" -> new EstimatedGNIDao();
		case "fertility_rate" -> new FertilityRateDao();
		case "gdp_per_capita" -> new GDPPerCapitaDao();
		case "gdp_total" -> new GDPTotalDao();
		case "gni_per_capita" -> new GNIPerCapitaDao();
		case "gross_fixed_capital_formation" -> new GrossFixedCapitalFormationDao();
		case "gross_reproduction_rate" -> new GrossReproductionRateDao();
		case "growth_rate" -> new GrowthRateDao();
		case "income_index" -> new IncomeIndexDao();
		case "infant_mortality" -> new InfantMortalityDao();
		case "infant_mortality_by_sex" -> new InfantMortalityBySexDao();
		case "labour_share_of_gdp" -> new LabourShareOfGDPDao();
		case "life_expectancy" -> new LifeExpectancyDao();
		case "life_expectancy_by_sex" -> new LifeExpectancyBySexDao();
		case "midyear_population" -> new MidyearPopulationDao();
		case "midyear_population_by_age_sex" -> new MidyearPopulationByAgeSexDao();
		case "mortality_rate_1_to_4" -> new MortalityRate1To4Dao();
		case "mortality_rate_1_to_4_by_sex" -> new MortalityRate1To4BySexDao();
		case "mortality_rate_under_5" -> new MortalityRateUnder5Dao();
		case "mortality_rate_under_5_by_sex" -> new MortalityRateUnder5BySexDao();
		case "net_migration" -> new NetMigrationDao();
		case "rate_natural_increase" -> new RateNaturalIncreaseDao();
		case "sex_ratio_at_birth" -> new SexRatioAtBirthDao();
		case "total_fertility_rate" -> new FertilityRateTotalDao();
		default -> null;
		};
	}
	
}