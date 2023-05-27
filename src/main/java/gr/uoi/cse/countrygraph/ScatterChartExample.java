package gr.uoi.cse.countrygraph;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ScatterChartExample extends Application {

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage primaryStage) 
    {
        // Create x and y axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("gni per capita");
        yAxis.setLabel("gdp per capita");

        // Create scatter chart
        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Scatter Chart Example");

        // Create data series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data Points");

        // Add data to the series
        series.getData().add(new XYChart.Data<>(1, 4));
        series.getData().add(new XYChart.Data<>(2, 6));
        series.getData().add(new XYChart.Data<>(3, 8));
        series.getData().add(new XYChart.Data<>(4, 5));
        series.getData().add(new XYChart.Data<>(5, 10));

        // Add the series to the chart
        
		ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList(series);
        scatterChart.setData(data);

        // Create and set the scene
        Scene scene = new Scene(scatterChart, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scatter Chart Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}