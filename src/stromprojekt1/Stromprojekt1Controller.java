/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stromprojekt1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
/**
 *
 * @author Administrator
 */
public class Stromprojekt1Controller implements Initializable{
    
   @FXML private Text actiontarget;
    @FXML private BarChart barchart;
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("fxml_stromprojekt1_Einstellungen.fxml"));
        Stage stage = new Stage(); 
        stage.setTitle("Stromprojekt Einstellungen");
        stage.setScene(new Scene(root));
        
        stage.show();
        
    }  
    @FXML protected void handleSubmitButtonActionBarChart (ActionEvent event) throws Exception {
   
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("XYChart.Series 1");
           
        series1.getData().add(new XYChart.Data("January", 100));
        barchart.getData().add(series1);
                
       
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
