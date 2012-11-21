/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



/**
 *
 * @author Administrator
 */
public class Stromprojekt1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	 
         Parent root = FXMLLoader.load(getClass().getResource("fxml_stromprojekt1.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Stromprojekt Hauptmenü");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
