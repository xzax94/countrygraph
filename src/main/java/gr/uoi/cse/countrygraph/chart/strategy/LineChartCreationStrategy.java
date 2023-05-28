package gr.uoi.cse.countrygraph.chart.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.connection.ConnectionFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.measure.MeasureRequestFormatter;
import gr.uoi.cse.countrygraph.query.QueryFactory;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapper;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperCache;
import gr.uoi.cse.countrygraph.statement.PreparedStatementProcessorStrategy;
import gr.uoi.cse.countrygraph.statement.PreparedStatementProcessorStrategyFactory;
import gr.uoi.cse.countrygraph.statistic.Statistic;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import gr.uoi.cse.countrygraph.table.TableMetadata;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public final class LineChartCreationStrategy implements ChartCreationStrategy
{
	@Override
	public void createChart(GraphController graphController) 
	{
		final Stage stage = new Stage();
		final String chartTypeString = graphController.getChartTypeChoiceBox().getSelectionModel().getSelectedItem();
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
		final XYChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(chartTypeString);        
        final List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
        
		try (final Connection connection = ConnectionFactory.getInstance().createConnection())
		{
			for (final MeasureRequest measureRequest : graphController.getMeasureRequestList())
			{
				final XYChart.Series<String, Number> series = new XYChart.Series<>();
				series.setName(MeasureRequestFormatter.getInstance().formatMeasureRequest(measureRequest));
				final TableMetadata measure = TableMetadataCache.getInstance().getTableMetadataByDescription(measureRequest.getTableMetadata().getMeasureDescription());
				final String tableName = measure.getTableName();
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
		finally
		{
			final Scene scene  = new Scene(chart, 800, 600);
			chart.getData().addAll(seriesList);
			stage.setScene(scene);
			stage.show();
			graphController.getMeasureRequestList().clear();
			graphController.getMeasureListView().getItems().clear();
		}
	}
	
}