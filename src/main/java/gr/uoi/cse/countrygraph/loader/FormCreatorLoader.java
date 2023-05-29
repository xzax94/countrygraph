package gr.uoi.cse.countrygraph.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.uoi.cse.countrygraph.form.FormCreator;
import gr.uoi.cse.countrygraph.form.FormCreatorCache;
import gr.uoi.cse.countrygraph.form.FormCreatorFactory;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import gr.uoi.cse.countrygraph.table.TableMetadata;

public final class FormCreatorLoader implements ApplicationLoader
{
	@Override
	public void load()
	{
		final FormCreatorFactory formCreatorFactory = new FormCreatorFactory();
    	final List<TableMetadata> measureList = TableMetadataCache.getInstance().getTableMetadataList();
    	final Map<String, FormCreator> formCreatorMap = new HashMap<>();
    	for (final TableMetadata measure : measureList)
    	{
    		final String measureDescription = measure.getMeasureDescription();
    		final FormCreator formCreator = formCreatorFactory.createNewInstance(measureDescription);
    		formCreatorMap.put(measureDescription, formCreator);
    	}
    	
    	FormCreatorCache.getInstance().putAll(formCreatorMap);
	}
}