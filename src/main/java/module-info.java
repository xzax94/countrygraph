module gr.uoi.cse.countrygraph {
	requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires org.controlsfx.controls;
    requires lombok;
	requires java.sql;
    
    opens gr.uoi.cse.countrygraph to javafx.fxml;
    exports gr.uoi.cse.countrygraph;
}