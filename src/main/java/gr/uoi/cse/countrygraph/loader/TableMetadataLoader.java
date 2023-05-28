package gr.uoi.cse.countrygraph.loader;

import java.util.List;

import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import gr.uoi.cse.countrygraph.table.TableMetadataDao;
import gr.uoi.cse.countrygraph.table.TableMetadata;

public final class TableMetadataLoader implements ApplicationLoader
{
	@Override
	public void load()
	{
		final TableMetadataDao tableMetadataDao = new TableMetadataDao();
    	final List<TableMetadata> tableMetadataList = tableMetadataDao.getTableMetadataList();
    	TableMetadataCache.getInstance().getTableMetadataList().addAll(tableMetadataList);
	}
}