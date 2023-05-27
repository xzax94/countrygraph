package gr.uoi.cse.countrygraph.resultset;

import java.util.HashMap;
import java.util.Map;

public final class ResultSetMapperCache
{
	private final Map<String, ResultSetMapper> resultSetMapperMap;
	
	private ResultSetMapperCache(Map<String, ResultSetMapper> resultSetMapperMap)
	{
		this.resultSetMapperMap = resultSetMapperMap;
	}
	
	public final void putAll(Map<String, ResultSetMapper> resultSetMapperMap) 
	{
		resultSetMapperMap.forEach((tableName, resultSetMapper) -> 
		{
			this.resultSetMapperMap.put(tableName.intern(), resultSetMapper);
		});
	}
	
	public final ResultSetMapper getResultSetMapperByTableName(String tableName)
	{
		return resultSetMapperMap.get(tableName.intern());
	}
	
	public static final ResultSetMapperCache getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final ResultSetMapperCache INSTANCE = new ResultSetMapperCache(new HashMap<>());
	}
}