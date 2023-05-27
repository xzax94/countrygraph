package gr.uoi.cse.countrygraph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.uoi.cse.countrygraph.chart.ChartType;
import gr.uoi.cse.countrygraph.chart.strategy.ChartCreationStrategy;
import gr.uoi.cse.countrygraph.chart.strategy.ChartCreationStrategyCache;
import gr.uoi.cse.countrygraph.chart.strategy.ChartCreationStrategyFactory;
import gr.uoi.cse.countrygraph.country.Country;
import gr.uoi.cse.countrygraph.country.CountryCache;
import gr.uoi.cse.countrygraph.country.CountryDao;
import gr.uoi.cse.countrygraph.form.FormCreator;
import gr.uoi.cse.countrygraph.form.FormCreatorCache;
import gr.uoi.cse.countrygraph.form.FormCreatorFactory;
import gr.uoi.cse.countrygraph.measure.Measure;
import gr.uoi.cse.countrygraph.measure.MeasureCache;
import gr.uoi.cse.countrygraph.measure.MeasureDao;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapper;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperCache;
import gr.uoi.cse.countrygraph.resultset.ResultSetMapperFactory;

public class GraphApplication extends Application 
{
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
    	loadMeasures();
    	loadCountries();
    	loadFormCreators();
    	loadResultSetMappers();
    	loadBarChartCreationStrategies();
        launch();
    }
    
    private static final void loadMeasures()
    {
    	final MeasureDao measureDao = new MeasureDao();
    	final List<Measure> measureList = measureDao.getMeasureList();
    	MeasureCache.getInstance().getMeasureList().addAll(measureList);
    }
    
    private static final void loadCountries()
    {
    	final CountryDao countryDao = new CountryDao();
    	final List<Country> countryList = countryDao.loadCountries();
    	CountryCache.getInstance().addAll(countryList);
    }
    
    private static final void loadFormCreators()
    {
    	final FormCreatorFactory formCreatorFactory = new FormCreatorFactory();
    	final List<Measure> measureList = MeasureCache.getInstance().getMeasureList();
    	final Map<String, FormCreator> formCreatorMap = new HashMap<>();
    	for (final Measure measure : measureList)
    	{
    		final String measureDescription = measure.getMeasureDescription();
    		final FormCreator formCreator = formCreatorFactory.createNewInstance(measureDescription);
    		formCreatorMap.put(measureDescription, formCreator);
    	}
    	
    	FormCreatorCache.getInstance().putAll(formCreatorMap);
    }
    
    private static final void loadResultSetMappers()
    {
    	final ResultSetMapperFactory resultSetMapperFactory = new ResultSetMapperFactory();
    	final List<Measure> measureList = MeasureCache.getInstance().getMeasureList();
    	final Map<String, ResultSetMapper> resultSetMapperMap = new HashMap<>();
    	for (final Measure measure : measureList)
    	{
    		final String tableName = measure.getTableName();
    		final ResultSetMapper resultSetMapper = resultSetMapperFactory.createNewInstance(tableName);
    		resultSetMapperMap.put(tableName, resultSetMapper);
    	}
    	
    	ResultSetMapperCache.getInstance().putAll(resultSetMapperMap);
    }
    
    private static final void loadBarChartCreationStrategies()
    {
    	final ChartCreationStrategyFactory chartCreationStrategyFactory = new ChartCreationStrategyFactory();
    	final Map<ChartType, ChartCreationStrategy> chartCreationStrategyMap = new HashMap<>();
    	for (final ChartType chartType : ChartType.values())
    	{
    		final ChartCreationStrategy chartCreationStrategy = chartCreationStrategyFactory.createNewInstance(chartType);
    		chartCreationStrategyMap.put(chartType, chartCreationStrategy);
    	}
    	
    	ChartCreationStrategyCache.getInstance().putAll(chartCreationStrategyMap);
    }
}