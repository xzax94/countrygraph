package gr.uoi.cse.countrygraph.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.uoi.cse.countrygraph.resultset.ResultSetMapper;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperCache;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperFactory;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import gr.uoi.cse.countrygraph.table.TableMetadata;

public final class ResultSetMapperLoader implements ApplicationLoader
{
	@Override
	public void load() 
	{
		final ResultSetMapperFactory resultSetMapperFactory = new ResultSetMapperFactory();
    	final List<TableMetadata> tableMetadataList = TableMetadataCache.getInstance().getTableMetadataList();
    	final Map<String, ResultSetMapper> resultSetMapperMap = new HashMap<>();
    	for (final TableMetadata tableMetadata : tableMetadataList)
    	{
    		final String tableName = tableMetadata.getTableName();
    		final ResultSetMapper resultSetMapper = resultSetMapperFactory.createNewInstance(tableName);
    		resultSetMapperMap.put(tableName, resultSetMapper);
    	}
    	
    	ResultSetMapperCache.getInstance().putAll(resultSetMapperMap);
	}
}