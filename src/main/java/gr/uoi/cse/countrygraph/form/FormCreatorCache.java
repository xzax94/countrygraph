package gr.uoi.cse.countrygraph.form;

import java.util.HashMap;
import java.util.Map;

public final class FormCreatorCache
{
	private static final FormCreator DEFAULT_FORM_CREATOR = new CountryYearFormCreator();
	private final Map<String, FormCreator> formCreatorMap;
	
	private FormCreatorCache(Map<String, FormCreator> formCreatorMap)
	{
		this.formCreatorMap = formCreatorMap;
	}
	
	public final void putAll(Map<String, FormCreator> formCreatorMap) 
	{
		formCreatorMap.forEach((measureDescription, formCreator) -> 
		{
			this.formCreatorMap.put(measureDescription.intern(), formCreator);
		});
	}
	
	public final FormCreator getFormCreatorByMeasureDescription(String measureDescription)
	{
		return formCreatorMap.getOrDefault(measureDescription.intern(), DEFAULT_FORM_CREATOR);
	}
	
	public static final FormCreatorCache getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final FormCreatorCache INSTANCE = new FormCreatorCache(new HashMap<>());
	}
}