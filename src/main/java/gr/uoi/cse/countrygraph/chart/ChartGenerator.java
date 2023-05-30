package gr.uoi.cse.countrygraph.chart;

import java.util.List;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public abstract class ChartGenerator<X, Y>
{
	public final void generateChart(List<MeasureRequest> measureRequestList, int aggregateYearDivider)
	{
		validateMeasureRequestList(measureRequestList);
		
		final Stage stage = new Stage();
		final Axis<X> xAxis = createXAxis(measureRequestList);
		final Axis<Y> yAxis = createYAxis(measureRequestList);
		final XYChart<X, Y> chart = createChart(xAxis, yAxis, measureRequestList);
		chart.setTitle(getChartTitle()); 
		
		final List<XYChart.Series<X, Y>> seriesList = createSeriesList(measureRequestList, aggregateYearDivider);
		for (final XYChart.Series<X, Y> series : seriesList)
			chart.getData().add(series);
		
		final Scene scene  = new Scene(chart, 800, 600);
		stage.setScene(scene);
		stage.show();
	}
	
	public abstract void validateMeasureRequestList(List<MeasureRequest> measureRequestList);
	
	public abstract String getChartTitle();
	
	public abstract Axis<X> createXAxis(List<MeasureRequest> measureRequestList);
	
	public abstract Axis<Y> createYAxis(List<MeasureRequest> measureRequestList);
	
	public abstract XYChart<X, Y> createChart(Axis<X> xAxis, Axis<Y> yAxis, List<MeasureRequest> measureRequestList);
	
	public abstract List<XYChart.Series<X, Y>> createSeriesList(List<MeasureRequest> measureRequestList, int aggregateYearDivider);
	
}