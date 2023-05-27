package gr.uoi.cse.countrygraph.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.uoi.cse.countrygraph.measure.Measure;
import gr.uoi.cse.countrygraph.measure.MeasureCache;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapper;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperCache;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperFactory;

public final class ResultSetMapperLoader implements ApplicationLoader
{
	@Override
	public void load() 
	{
		final ResultSetMapperFactory resultSetMapperFactory = new ResultSetMapperFactory();
    	final List<Measure> measureList = MeasureCache.getInstance().getMeasureList();
    	final Map<String, ResultSetMapper> resultSetMapperMap = new HashMap<>();
    	for (final Measure measure : measureList)
    	{
    		final String tableName = measure.getTableName();
    		final ResultSetMapper resultSetMapper = resultSetMapperFactory.createNewInstance(tableName);
    		resultSetMapperMap.put(tableName, resultSetMapper);
    	}
    	
    	ResultSetMapperCache.getInstance().putAll(resultSetMapperMap);
	}
}