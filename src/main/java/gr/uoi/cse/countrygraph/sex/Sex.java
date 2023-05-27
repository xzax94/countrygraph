package gr.uoi.cse.countrygraph.sex;

import java.util.stream.Stream;

public enum Sex
{
	MALE("Male"),
	FEMALE("Female");
	
	private final String name;
	
	Sex(String name)
	{
		this.name = name;
	}
	
	public final String getName()
	{
		return name;
	}
	
	public static Sex findByName(String name)
	{
		return Stream.of(values()).filter(sex -> sex.getName().equalsIgnoreCase(name)).findAny().orElse(MALE);
	}
}