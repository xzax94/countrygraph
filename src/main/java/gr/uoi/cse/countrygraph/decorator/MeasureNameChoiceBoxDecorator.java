package gr.uoi.cse.countrygraph.decorator;

import java.util.List;

import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import gr.uoi.cse.countrygraph.table.TableMetadata;

public final class MeasureNameChoiceBoxDecorator implements ViewDecorator<GraphController>
{
	@Override
	public void decorateView(GraphController graphController) 
	{
		final List<String> measureNames = TableMetadataCache
				.getInstance()
				.getTableMetadataList()
				.stream()
				.map(TableMetadata::getMeasureDescription)
				.toList();
		
		graphController.getMeasureNameChoiceBox().getItems().addAll(measureNames);
		graphController.getMeasureNameChoiceBox().getSelectionModel().select(0);
	}
}