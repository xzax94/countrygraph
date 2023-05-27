package gr.uoi.cse.countrygraph.decorator;

import java.util.List;

import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.measure.Measure;
import gr.uoi.cse.countrygraph.measure.MeasureCache;

public final class MeasureNameChoiceBoxDecorator implements ViewDecorator<GraphController>
{
	@Override
	public void decorateView(GraphController graphController) 
	{
		final List<String> measureNames = MeasureCache
				.getInstance()
				.getMeasureList()
				.stream()
				.map(Measure::getMeasureDescription)
				.toList();
		
		graphController.getMeasureNameChoiceBox().getItems().addAll(measureNames);
		graphController.getMeasureNameChoiceBox().getSelectionModel().select(0);
	}
}