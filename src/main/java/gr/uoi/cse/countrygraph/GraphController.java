package gr.uoi.cse.countrygraph;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import gr.uoi.cse.countrygraph.chart.ChartGenerator;
import gr.uoi.cse.countrygraph.chart.ChartGeneratorFactory;
import gr.uoi.cse.countrygraph.chart.ChartType;
import gr.uoi.cse.countrygraph.decorator.ChartTypeChoiceBoxDecorator;
import gr.uoi.cse.countrygraph.decorator.MeasureNameChoiceBoxDecorator;
import gr.uoi.cse.countrygraph.decorator.ViewDecorator;
import gr.uoi.cse.countrygraph.form.FormCreator;
import gr.uoi.cse.countrygraph.form.FormCreatorFactory;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.measure.MeasureRequestFormatter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import lombok.Data;

@Data
public class GraphController implements Initializable
{
	private static final List<ViewDecorator<GraphController>> VIEW_DECORATORS = List.of(new MeasureNameChoiceBoxDecorator(), new ChartTypeChoiceBoxDecorator());
	private final List<MeasureRequest> measureRequestList = new ArrayList<>();
	
	@FXML
	private ChoiceBox<String> measureNameChoiceBox;
	
	@FXML
	private ListView<String> measureListView;
	
	@FXML
	private ChoiceBox<String> chartTypeChoiceBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		VIEW_DECORATORS.forEach(viewDecorator -> viewDecorator.decorateView(this));
	}
	
    @FXML
    private void onAddMeasureButtonClick() 
    {
    	final String selectedMeasureDescription = measureNameChoiceBox.getSelectionModel().getSelectedItem().trim().intern();
    	final FormCreatorFactory formCreatorFactory = new FormCreatorFactory();
    	final FormCreator formCreator = formCreatorFactory.createNewInstance(selectedMeasureDescription);
		formCreator.createFormWindow(this, measureNameChoiceBox.getSelectionModel().getSelectedItem());
    }
    
	public void addMeasureRequest(MeasureRequest measureRequest) 
	{
		measureRequestList.add(measureRequest);
		Collections.sort(measureRequestList);
		final String formattedMeasure = MeasureRequestFormatter.getInstance().formatMeasureRequest(measureRequest);
		measureListView.getItems().add(formattedMeasure);
	}
	
	@FXML
	private void onCreateGraphRequest()
	{
		final String chartTypeString = chartTypeChoiceBox.getSelectionModel().getSelectedItem();
		final ChartType chartType = ChartType.findByName(chartTypeString);
		final ChartGeneratorFactory chartGeneratorFactory = new ChartGeneratorFactory();
		final ChartGenerator<?, ?> chartGenerator = chartGeneratorFactory.createNewInstance(chartType);
		chartGenerator.generateChart(measureRequestList);
	}
	
	@FXML
	private void onClearMeasuresRequest()
	{
		measureRequestList.clear();
		measureListView.getItems().clear();
	}
}