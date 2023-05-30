package gr.uoi.cse.countrygraph.chart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.measure.MeasureRequestFormatter;
import gr.uoi.cse.countrygraph.query.QueryFactory;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapper;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperCache;
import gr.uoi.cse.countrygraph.statement.PreparedStatementProcessorStrategy;
import gr.uoi.cse.countrygraph.statement.PreparedStatementProcessorStrategyFactory;
import gr.uoi.cse.countrygraph.statistic.Statistic;
import gr.uoi.cse.countrygraph.table.TableMetadata;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public final class BarChartGenerator extends ChartGenerator<String, Number>
{
	private static final String TITLE = "Bar Chart";
	private static final String X_AXIS_TITLE = "Year";
	private static final String Y_AXIS_TITLE = "Value";
	private static final int BAR_CHART_MIN_MEASURES = 1;
	
	@Override
	public String getChartTitle() 
	{
		return TITLE;
	}
	
	@Override
	public int getMinMeasures() 
	{
		return BAR_CHART_MIN_MEASURES;
	}

	@Override
	public int getMaxMeasures() 
	{
		return Integer.MAX_VALUE;
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
		return new BarChart<>(xAxis, yAxis);
	}

	@Override
	public List<Series<String, Number>> getSeriesList(List<MeasureRequest> measureRequestList) 
	{
		final List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
		
		try (final Connection connection = ConnectionFactory.getInstance().createConnection())
		{
			for (final MeasureRequest measureRequest : measureRequestList)
			{
				final XYChart.Series<String, Number> series = new XYChart.Series<>();
				series.setName(MeasureRequestFormatter.getInstance().formatMeasureRequest(measureRequest));
				final TableMetadata tableMetadata = TableMetadataCache.getInstance().getTableMetadataByDescription(measureRequest.getTableMetadata().getMeasureDescription());
				final String tableName = tableMetadata.getTableName();
				final QueryFactory queryFactory = new QueryFactory();
				final String query = queryFactory.createNewInstance(tableName);
				
				try(final PreparedStatement preparedStatement = connection.prepareStatement(query))
				{
					final List<Statistic> statisticList = new ArrayList<>();
					final PreparedStatementProcessorStrategyFactory preparedStatementProcessorStrategyFactory = new PreparedStatementProcessorStrategyFactory();
					final PreparedStatementProcessorStrategy preparedStatementProcessorStrategy = preparedStatementProcessorStrategyFactory.createNewInstance(tableName);
					preparedStatementProcessorStrategy.processPreparedStatement(preparedStatement, measureRequest);
					
					try(final ResultSet resultSet = preparedStatement.executeQuery())
					{
						while (resultSet.next())
						{
							final ResultSetMapper resultSetMapper = ResultSetMapperCache.getInstance().getResultSetMapperByTableName(tableName);
							final Statistic statistic = resultSetMapper.mapResultSetToStatistic(resultSet);
							statisticList.add(statistic);
						}
					}
					
					for (final Statistic statistic : statisticList)
						series.getData().add(new XYChart.Data<>(String.valueOf(statistic.getYear()), statistic.getStatValue()));
					
					seriesList.add(series);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		
		return seriesList;
	}
}