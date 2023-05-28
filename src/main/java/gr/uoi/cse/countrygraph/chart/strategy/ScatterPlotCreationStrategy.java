package gr.uoi.cse.countrygraph.chart.strategy;

import java.sql.Connection;
import java.util.List;

import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.dialogue.DialogueDisplayer;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.statistic.Statistic;

public final class ScatterPlotCreationStrategy implements ChartCreationStrategy
{
	private static final int MAX_SCATTER_PLOT_MEASURES = 2;
	private static final int FIRST_MEASURE_INDEX = 0;
	private static final int SECOND_MEASURE_INDEX = 0;

	@Override
	public void createChart(GraphController graphController) 
	{
		final List<MeasureRequest> measureRequestList = graphController.getMeasureRequestList();
		if (measureRequestList.size() != MAX_SCATTER_PLOT_MEASURES)
		{
			DialogueDisplayer.getInstance().displayDialogue("Only 2 measures are allowed on scatter plots.");
			return;
		}
		
		final MeasureRequest firstMeasureRequest = measureRequestList.get(FIRST_MEASURE_INDEX);
		final MeasureRequest secondMeasureRequest = measureRequestList.get(SECOND_MEASURE_INDEX);
			
		//TODO implement tomorrow
	}
	
}