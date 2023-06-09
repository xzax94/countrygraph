package gr.uoi.cse.countrygraph.chart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.exception.ScatterPlotMeasureException;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.measure.MeasureRequestFormatter;
import gr.uoi.cse.countrygraph.query.ScatterPlotQueryCreationStrategy;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public final class ScatterPlotGenerator extends ChartGenerator<Number, Number>
{
	private static final String TITLE = "Scatter Plot";
	private static final int SCATTER_PLOT_REQUIRED_MEASURES = 2;
	private static final int FIRST_MEASURE_INDEX = 0;
	private static final int SECOND_MEASURE_INDEX = 1;
	private static final String FIRST_MEASURE_STAT_NAME = "stat1";
	private static final String SECOND_MEASURE_STAT_NAME = "stat2";
	
	@Override
	public void validateMeasureRequestList(List<MeasureRequest> measureRequestList) 
	{
		if (measureRequestList.size() != SCATTER_PLOT_REQUIRED_MEASURES)
			throw new ScatterPlotMeasureException();
	}
	
	@Override
	public String getChartTitle() 
	{
		return TITLE;
	}

	@Override
	public Axis<Number> createXAxis(List<MeasureRequest> measureRequestList) 
	{
		final NumberAxis xAxis = new NumberAxis();
		final MeasureRequest firstMeasureRequest = measureRequestList.get(FIRST_MEASURE_INDEX);
		final MeasureRequestFormatter measureRequestFormmater = MeasureRequestFormatter.getInstance();
		final String xAxisTitle = measureRequestFormmater.formatMeasureRequest(firstMeasureRequest);
		xAxis.setLabel(xAxisTitle);
		return xAxis;
	}

	@Override
	public Axis<Number> createYAxis(List<MeasureRequest> measureRequestList) 
	{
		final NumberAxis yAxis = new NumberAxis();
		final MeasureRequest secondMeasureRequest = measureRequestList.get(SECOND_MEASURE_INDEX);
		final MeasureRequestFormatter measureRequestFormmater = MeasureRequestFormatter.getInstance();
		final String yAxisTitle = measureRequestFormmater.formatMeasureRequest(secondMeasureRequest);
		yAxis.setLabel(yAxisTitle);
		return yAxis;
	}
	
	@Override
	public XYChart<Number, Number> createChart(Axis<Number> xAxis, Axis<Number> yAxis, List<MeasureRequest> measureRequestList) 
	{
		return new ScatterChart<>(xAxis, yAxis);
	}

	@Override
	public List<Series<Number, Number>> createSeriesList(List<MeasureRequest> measureRequestList, int aggregateYearDivider) 
	{
		
		final List<XYChart.Data<Number, Number>> chartDataList = createChartDataList(measureRequestList, aggregateYearDivider);
		
		final XYChart.Series<Number, Number> series = new XYChart.Series<>();
		for (final XYChart.Data<Number, Number> chartData : chartDataList)
			series.getData().add(chartData);
		
		return List.of(series);
	}
	
	private final List<XYChart.Data<Number, Number>> createChartDataList(List<MeasureRequest> measureRequestList, int aggregateYearDivider)
	{
		final List<XYChart.Data<Number, Number>> chartDataList = new ArrayList<>();
		final ScatterPlotQueryCreationStrategy scatterPlotQueryCreationStrategy = new ScatterPlotQueryCreationStrategy();
		final String query = scatterPlotQueryCreationStrategy.createQuery(measureRequestList, aggregateYearDivider);
		
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