package gr.uoi.cse.countrygraph.decorator;

import java.util.List;

import gr.uoi.cse.countrygraph.GraphController;

public final class AggregateTimeChoiceBoxDecorator implements ViewDecorator<GraphController>
{
	private static final List<String> AGGREGATE_TIME_LIST = List.of("1", "5", "10");
	
	@Override
	public void decorateView(GraphController graphController) 
	{
		graphController.getAggregateTimeChoiceBox().getItems().addAll(AGGREGATE_TIME_LIST);
		graphController.getAggregateTimeChoiceBox().getSelectionModel().select(0);
	}
}