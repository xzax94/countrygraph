package gr.uoi.cse.countrygraph;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class BarChartDemo extends Application 
{
	@SuppressWarnings("unchecked")
	@Override
    public void start(Stage stage) 
    {
        stage.setTitle("Bar Chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final XYChart<String, Number> barChart = new LineChart<String, Number>(xAxis, yAxis);
        barChart.setTitle("Country Summary");
        xAxis.setLabel("Year");
        yAxis.setLabel("Amount");
        
        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Greece Births");
        series1.getData().add(new XYChart.Data<>("1992", 25601.34));
        series1.getData().add(new XYChart.Data<>("1993", 20000.34));
        series1.getData().add(new XYChart.Data<>("1994", 29000.34));
        
        
        final XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Greece Deaths");
        series2.getData().add(new XYChart.Data<>("1992", 15601.34));
        series2.getData().add(new XYChart.Data<>("1993", 10000.34));
        series2.getData().add(new XYChart.Data<>("1994", 19000.34));
        
        final XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("England Deaths");
        series3.getData().add(new XYChart.Data<>("1992", 17601.34));
        series3.getData().add(new XYChart.Data<>("1993", 14000.34));
        series3.getData().add(new XYChart.Data<>("1994", 19000.34));
        
        final Scene scene  = new Scene(barChart, 800, 600);
        barChart.getData().addAll(List.of(series1, series2, series3));
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) 
    {
        launch(args);
    }
}