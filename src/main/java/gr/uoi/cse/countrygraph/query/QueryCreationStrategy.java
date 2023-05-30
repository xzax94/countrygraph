package gr.uoi.cse.countrygraph.query;

import java.util.List;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;

@FunctionalInterface
public interface QueryCreationStrategy
{
	String createQuery(List<MeasureRequest> measureRequestList, int aggregateDivider);
}