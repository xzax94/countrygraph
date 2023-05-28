package gr.uoi.cse.countrygraph.chart.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.dialogue.DialogueDisplayer;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.measure.MeasureRequestFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import gr.uoi.cse.countrygraph.query.ScatterPlotQueryCreator;

public final class ScatterPlotCreationStrategy implements ChartCreationStrategy
{
	private static final ScatterPlotQueryCreator SCATTER_PLOT_QUERY_CREATOR = new ScatterPlotQueryCreator();
	private static final String SCATTER_PLOT_TITLE = "Scatter Plot";
	private static final int MAX_SCATTER_PLOT_MEASURES = 2;
	private static final int FIRST_MEASURE_INDEX = 0;
	private static final int SECOND_MEASURE_INDEX = 1;
	private static final String FIRST_MEASURE_STAT_NAME = "stat1";
	private static final String SECOND_MEASURE_STAT_NAME = "stat2";

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
		final Integer minYear = Math.max(firstMeasureRequest.getMinYear(), secondMeasureRequest.getMinYear());
		final Integer maxYear = Math.min(firstMeasureRequest.getMaxYear(), secondMeasureRequest.getMaxYear());
		
		if (minYear > maxYear)
		{
			DialogueDisplayer.getInstance().displayDialogue("These 2 measures have no year in common.");
			return;
		}
		
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(MeasureRequestFormatter.getInstance().formatMeasureRequest(firstMeasureRequest));
        yAxis.setLabel(MeasureRequestFormatter.getInstance().formatMeasureRequest(secondMeasureRequest));
        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle(SCATTER_PLOT_TITLE);
		final List<XYChart.Data<Number, Number>> chartDataList = createChartDataList(firstMeasureRequest, secondMeasureRequest);
		final XYChart.Series<Number, Number> series = new XYChart.Series<>();
		for (final XYChart.Data<Number, Number> chartData : chartDataList)
			series.getData().add(chartData);

		final ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList(series);
        scatterChart.setData(data);
        
        final Scene scene = new Scene(scatterChart, 500, 400);
        final Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(SCATTER_PLOT_TITLE);
        stage.show();
	}
	
	private final List<XYChart.Data<Number, Number>> createChartDataList(MeasureRequest firstMeasureRequest, MeasureRequest secondMeasureRequest)
	{
		final List<XYChart.Data<Number, Number>> chartDataList = new ArrayList<>();
		final String query = SCATTER_PLOT_QUERY_CREATOR.createQuery(firstMeasureRequest, secondMeasureRequest);
		
		try(final Connection connection = ConnectionFactory.getInstance().createConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(query);
				final ResultSet resultSet = preparedStatement.executeQuery())
		{
			while (resultSet.next())
			{
				final float firstMeasureStat = resultSet.getFloat(FIRST_MEASURE_STAT_NAME);
				final float secondMeasureStat = resultSet.getFloat(SECOND_MEASURE_STAT_NAME);
				chartDataList.add(new XYChart.Data<>(firstMeasureStat, secondMeasureStat));
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return chartDataList;
	}
}