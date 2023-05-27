package gr.uoi.cse.countrygraph.country;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country
{
	private Integer id;
	private String iso;
	private String iso3;
	private Integer isoCode;
	private String fips;
	private String displayName;
	private String officialName;
	private String capitalName;
	private String continentName;
	private String currencyCode;
	private String currencyName;
	private Integer phone;
	private Integer regionCode;
	private String regionName;
	private Integer subRegionCode;
	private String subRegionName;
	private Integer intermediateRegionCode;
	private String intermediateRegionName;
	private String status;
	private String developmentStatus;
	private boolean sids;
	private boolean lldc;
	private boolean ldc;
	private Integer areaInSquaredKm;
	private Integer population;
}