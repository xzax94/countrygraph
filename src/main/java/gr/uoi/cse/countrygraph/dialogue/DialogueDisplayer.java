package gr.uoi.cse.countrygraph.dialogue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class DialogueDisplayer
{
	public final void displayDialogue(String message) 
    {
    	final Stage dialogStage = new Stage();
    	dialogStage.initModality(Modality.WINDOW_MODAL);

    	final Button okButton = new Button("Ok");
    	okButton.setOnAction(e -> {
    		dialogStage.close();
    	});
    	final VBox vbox = new VBox(new Text(message), okButton);
    	vbox.setAlignment(Pos.CENTER);
    	vbox.setPadding(new Insets(15));

    	dialogStage.setScene(new Scene(vbox));
    	dialogStage.show();
    }
}