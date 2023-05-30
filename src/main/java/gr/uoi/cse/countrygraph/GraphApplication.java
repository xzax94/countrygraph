package gr.uoi.cse.countrygraph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import gr.uoi.cse.countrygraph.loader.CountryCacheLoader;
import gr.uoi.cse.countrygraph.loader.ExceptionHandlerLoader;
import gr.uoi.cse.countrygraph.loader.ApplicationLoader;
import gr.uoi.cse.countrygraph.loader.TableMetadataLoader;
import gr.uoi.cse.countrygraph.loader.ResultSetMapperLoader;

public class GraphApplication extends Application 
{
	private static final List<ApplicationLoader> APPLICATION_LOADERS = List.of(new TableMetadataLoader(),
			new CountryCacheLoader(),
			new ResultSetMapperLoader(),
			new ExceptionHandlerLoader());
	
    @Override
    public void start(Stage stage) throws IOException 
    {
        final FXMLLoader fxmlLoader = new FXMLLoader(GraphApplication.class.getResource("main-window.fxml"));
        final Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Graph Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
    	APPLICATION_LOADERS.forEach(ApplicationLoader::load);
    	Application.launch(GraphApplication.class, args);
    }
}