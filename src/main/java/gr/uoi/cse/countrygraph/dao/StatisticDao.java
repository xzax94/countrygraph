package gr.uoi.cse.countrygraph.dao;

import java.util.List;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.Statistic;

@FunctionalInterface
public interface StatisticDao<T extends Statistic>
{
	List<T> findAll(MeasureRequest measureRequest);
}