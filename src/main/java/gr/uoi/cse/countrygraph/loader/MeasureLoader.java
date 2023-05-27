package gr.uoi.cse.countrygraph.loader;

import java.util.List;

import gr.uoi.cse.countrygraph.measure.Measure;
import gr.uoi.cse.countrygraph.measure.MeasureCache;
import gr.uoi.cse.countrygraph.measure.MeasureDao;

public final class MeasureLoader implements ApplicationLoader
{
	@Override
	public void load() 
	{
		final MeasureDao measureDao = new MeasureDao();
    	final List<Measure> measureList = measureDao.getMeasureList();
    	MeasureCache.getInstance().getMeasureList().addAll(measureList);
	}
}