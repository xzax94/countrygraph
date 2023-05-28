package gr.uoi.cse.countrygraph.form;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gr.uoi.cse.countrygraph.GraphApplication;
import gr.uoi.cse.countrygraph.GraphController;
import gr.uoi.cse.countrygraph.country.Country;
import gr.uoi.cse.countrygraph.country.CountryCache;
import gr.uoi.cse.countrygraph.dialogue.DialogueDisplayer;
import gr.uoi.cse.countrygraph.measure.MeasureRequest;
import gr.uoi.cse.countrygraph.sex.Sex;
import gr.uoi.cse.countrygraph.table.TableMetadata;
import gr.uoi.cse.countrygraph.table.TableMetadataCache;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class CountryYearSexFormCreator implements FormCreator
{
	@Override
	public void createFormWindow(GraphController graphController, String measureName)
	{
        try
        {
            final FXMLLoader fxmlLoader = new FXMLLoader(GraphApplication.class.getResource("country_year_sex_form_view.fxml"));
            final Parent root = fxmlLoader.load();
            
			final ChoiceBox<String> countryChoiceBox = (ChoiceBox<String>) root.lookup("#countryChoiceBox");
			final List<String> countryNames = CountryCache
					.getInstance()
					.getAllCountries()
					.stream()
					.map(Country::getDisplayName)
					.collect(Collectors.toList());
			
			countryChoiceBox.getItems().addAll(countryNames);
			countryChoiceBox.getSelectionModel().select(0);
			
			final Label measureLabel = (Label) root.lookup("#measureLabel");
			measureLabel.setText(measureName);
			
			final ChoiceBox<String> sexChoiceBox = (ChoiceBox<String>) root.lookup("#sexChoiceBox");
			final List<String> sexNames = Stream.of(Sex.values()).map(Sex::getName).toList();
			sexChoiceBox.getItems().addAll(sexNames);
			sexChoiceBox.getSelectionModel().select(0);
			
			final Button addMeasureButton = (Button) root.lookup("#addMeasureButton");
			addMeasureButton.setOnAction(e -> {
				final TextField minYearTextField = (TextField) root.lookup("#minYearTextField");
				final String minYearString = minYearTextField.getText();
				
				if (minYearString == null || minYearString.isEmpty() || !isInteger(minYearString))
				{
					DialogueDisplayer.getInstance().displayDialogue("Invalid min year.");
					return;
				}
				
				final TextField maxYearTextField = (TextField) root.lookup("#maxYearTextField");
				final String maxYearString = maxYearTextField.getText();
				
				if (maxYearString == null || maxYearString.isEmpty() || !isInteger(maxYearString))
				{
					DialogueDisplayer.getInstance().displayDialogue("Invalid max year.");
					return;
				}
				
				final int minYear = Integer.parseInt(minYearString);
				final int maxYear = Integer.parseInt(maxYearString);
				
				if (maxYear < minYear)
				{
					DialogueDisplayer.getInstance().displayDialogue("Max year shouldn't be lower than min year");
					return;
				}
				
				if (countryChoiceBox.getSelectionModel().isEmpty())
				{
					DialogueDisplayer.getInstance().displayDialogue("You should select a country first.");
					return;
				}
				final String countryName = countryChoiceBox.getSelectionModel().getSelectedItem().trim().intern();
				final Country country = CountryCache.getInstance().getCountryByName(countryName);
				final String sexString = sexChoiceBox.getSelectionModel().getSelectedItem().trim().intern();
				final Sex sex = Sex.findByName(sexString);
				final TableMetadata tableMetadata = TableMetadataCache.getInstance().getTableMetadataByDescription(measureName);
				
				final MeasureRequest measureRequest = MeasureRequest
						.builder()
						.countryId(country.getId())
						.minYear(minYear)
						.maxYear(maxYear)
						.sex(sex)
						.tableMetadata(tableMetadata)
						//.measureDescription(measureName)
						.build();
				
				graphController.addMeasureRequest(measureRequest);
				
				final Stage formStage = (Stage) addMeasureButton.getScene().getWindow();
				formStage.close();
			});
			
            final Stage formStage = new Stage();
            formStage.setTitle("Form Window");
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.setScene(new Scene(root));
            formStage.showAndWait();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	private final boolean isInteger(String string)
	{
		try
		{
			Integer.parseInt(string);
			return true;
		}
		catch (final Exception e)
		{
			return false;
		}
	}
}