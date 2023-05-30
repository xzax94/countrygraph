package gr.uoi.cse.countrygraph.chart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.exception.LineChartMeasureException;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.measure.MeasureRequestFormatter;
import gr.uoi.cse.countrygraph.query.LineChartQueryCreator;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public final class LineChartGenerator extends ChartGenerator<String, Number>
{
	private static final String TITLE = "Line Chart";
	private static final String X_AXIS_TITLE = "Year";
	private static final String Y_AXIS_TITLE = "Value";
	private static final String MIN_YEAR_COLUMN_NAME = "min_year";
	private static final String MAX_YEAR_COLUMN_NAME = "max_year";
	private static final String AVERAGE_STAT_COLUMN_NAME = "average_stat";
	
	@Override
	public void validateMeasureRequestList(List<MeasureRequest> measureRequestList) 
	{
		if (measureRequestList.isEmpty())
			throw new LineChartMeasureException();
	}
	
	@Override
	public String getChartTitle() 
	{
		return TITLE;
	}
	
	@Override
	public Axis<String> createXAxis(List<MeasureRequest> measureRequestList) 
	{
		final CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel(X_AXIS_TITLE);
		return xAxis;
	}

	@Override
	public Axis<Number> createYAxis(List<MeasureRequest> measureRequestList) 
	{
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel(Y_AXIS_TITLE);
		return yAxis;
	}
	
	@Override
	public XYChart<String, Number> createChart(Axis<String> xAxis, Axis<Number> yAxis, List<MeasureRequest> measureRequestList) 
	{
		return new LineChart<>(xAxis, yAxis);
	}

	@Override
	public List<Series<String, Number>> createSeriesList(List<MeasureRequest> measureRequestList, int aggregateYearDivider) 
	{
		final List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
		
		try (final Connection connection = ConnectionFactory.getInstance().createConnection())
		{
			for (final MeasureRequest measureRequest : measureRequestList)
			{
				final List<XYChart.Data<String, Number>> chartDataList = new ArrayList<>();
				final LineChartQueryCreator lineChartQueryCreator = new LineChartQueryCreator();
				final String query = lineChartQueryCreator.createQuery(List.of(measureRequest), aggregateYearDivider);
				
				try(final PreparedStatement preparedStatement = connection.prepareStatement(query);
						final ResultSet resultSet = preparedStatement.executeQuery())
				{
					while (resultSet.next())
					{
						final int minYear = resultSet.getInt(MIN_YEAR_COLUMN_NAME);
						final int maxYear = resultSet.getInt(MAX_YEAR_COLUMN_NAME);
						final String yearString = formatYear(minYear, maxYear);
						final float averageStat = resultSet.getFloat(AVERAGE_STAT_COLUMN_NAME);
						chartDataList.add(new XYChart.Data<>(yearString, averageStat));
					}
					
					final XYChart.Series<String, Number> series = new XYChart.Series<>();
					series.setName(MeasureRequestFormatter.getInstance().formatMeasureRequest(measureRequest));
					
					for (final XYChart.Data<String, Number> chartData : chartDataList)
						series.getData().add(chartData);
					
					seriesList.add(series);
				}
				catch (final Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return seriesList;
	}
	
	private final String formatYear(int minYear, int maxYear) 
	{
		if (minYear == maxYear)
			return String.valueOf(minYear);
		
		return String.format("%d-%d", minYear, maxYear);
	}
}