package gr.uoi.cse.countrygraph.decorator;

import java.util.List;
import java.util.stream.Stream;

import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.chart.ChartType;

public final class ChartTypeChoiceBoxDecorator implements ViewDecorator<GraphController>
{
	@Override
	public void decorateView(GraphController graphController) 
	{
		final List<String> chartTypeList = Stream.of(ChartType.values()).map(ChartType::getName).toList();
		graphController.getChartTypeChoiceBox().getItems().addAll(chartTypeList);
		graphController.getChartTypeChoiceBox().getSelectionModel().select(0);
	}
}