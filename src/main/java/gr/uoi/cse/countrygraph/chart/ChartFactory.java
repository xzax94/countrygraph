package gr.uoi.cse.countrygraph.chart;

import gr.uoi.cse.countrygraph.factory.Factory;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public final class ChartFactory implements Factory<ChartType, XYChart<String, Number>>
{
	@Override
	public XYChart<String, Number> createNewInstance(ChartType chartType)
	{
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
		return switch(chartType) {
		case BAR_CHART -> new BarChart<>(xAxis, yAxis);
		case LINE_CHART -> new LineChart<>(xAxis, yAxis);
		case SCATTER_CHART -> new ScatterChart<>(xAxis, yAxis);
		};
	}
}