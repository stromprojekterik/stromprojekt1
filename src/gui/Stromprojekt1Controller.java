/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.DataHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import data.Date;
import data.StromWert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
/**
 *
 * @author Administrator
 */
public class Stromprojekt1Controller implements Initializable{
    
	   @FXML private Text actiontarget;
	   @FXML private LineChart linechart;
	   @FXML private Button input;
	   @FXML private Button output;
	   @FXML private TextField inputTextField;
	   @FXML private TextField outputTextField;	   
	   @FXML public ScrollBar scrollbar;
	   
	   
	   
	   
    @FXML protected void handleSubmitButtonActionBarChart (ActionEvent event) throws Exception {
    	if(!linechart.getData().isEmpty())linechart.getData().clear();
    	DataHandler test = new DataHandler();
    	
    	test.setFile(new File("C:\\Dokumente und Einstellungen\\Administrator\\Eigene Dateien\\Dropbox\\Powermeter\\export01.txt"));
    	test.readExportTxt();
    	
        XYChart.Series<String, Float> series1 = new XYChart.Series();
//        series1.setName("XYChart.Series 1");
        linechart.getData().add(series1);
        
        Iterator<StromWert> it = verringernDerWerte(test.getDataHolder()).iterator();
        while (it.hasNext()) {
			StromWert stromwert = (StromWert) it.next();
			series1.getData().add(new XYChart.Data(stromwert.getZeitpunkt().toString(), stromwert.getWert()));
		}
    }
    
    
    @FXML protected void handelChangeButtonProfil (ActionEvent event) throws Exception {
    	String config=(((ComboBox<String>)event.getSource()).getSelectionModel().selectedItemProperty()).getValue();
    	Calendar today = new GregorianCalendar();
    	int date = today.get( Calendar.DATE  );
		int year = today.get( Calendar.YEAR  );
		int month = today.get( Calendar.MONTH ) + 1; 
		int minute = today.get(Calendar.MINUTE);
		int second = today.get(Calendar.SECOND);
		int hour = today.get(Calendar.HOUR_OF_DAY);
		Date heute= new Date(date,month,year,hour,minute,second);
		Date jetzt= new Date(date,month,year,0,0,0);
		DataHandler test1 = new DataHandler();
		
//		String str = JOptionPane.showInputDialog("GIB ZAHL EIN");
//		int temp = Integer.parseInt(str);
//		System.out.println(temp);
		if(!linechart.getData().isEmpty())linechart.getData().clear();
    	switch (config){
    	case "heutiger Tag":
    		
        	
        	test1.setFile(new File("C:\\Dokumente und Einstellungen\\Administrator\\Eigene Dateien\\Dropbox\\Powermeter\\export01.txt"));
        	test1.readExportTxt();
        	
            XYChart.Series<String, Float> series1 = new XYChart.Series();
//            series1.setName("XYChart.Series 1");
            linechart.getData().add(series1);
            
            Iterator<StromWert> it = test1.getDataHolder().iterator();
            LinkedList<StromWert> tmp = new LinkedList<StromWert>();
            while (it.hasNext()) {
	            StromWert stromwert = it.next();
	            if (!stromwert.getZeitpunkt().isBefore(jetzt))tmp.add(stromwert);
            }
           it = verringernDerWerte(tmp).iterator();
            while (it.hasNext()) {
            	StromWert stromwert = it.next();
            	System.out.println("Zeitpunkt: "+stromwert.getZeitpunkt());
    			series1.getData().add(new XYChart.Data(stromwert.getZeitpunkt().toString(), stromwert.getWert()));
            }
            break;
    	case "letzte 7 Tage":
    		int x=7;
    		
    		//!zeitpunkt.isBefore(heute.calc(-x))
        	test1.setFile(new File("C:\\Dokumente und Einstellungen\\Administrator\\Eigene Dateien\\Dropbox\\Powermeter\\export01.txt"));
        	test1.readExportTxt();
        	
            XYChart.Series<String, Float> series2 = new XYChart.Series();
//            series1.setName("XYChart.Series 1");
            linechart.getData().add(series2);
            
            Iterator<StromWert> it2 = test1.getDataHolder().iterator();
            LinkedList<StromWert> tmp7 = new LinkedList<StromWert>();
            while (it2.hasNext()) {
	            StromWert stromwert = it2.next();
	            if (!stromwert.getZeitpunkt().isBefore(heute.calcDay(-x)))tmp7.add(stromwert);
            }	
            it2 = verringernDerWerte(tmp7).iterator();
            while (it2.hasNext()) {
            	StromWert stromwert = it2.next();
            	series2.getData().add(new XYChart.Data(stromwert.getZeitpunkt().toString(), stromwert.getWert()));
            	
            	}
            break;
            
            /*while (it2.hasNext()) {
    			StromWert stromwert = it2.next();
    			
    			
    			if (!stromwert.getZeitpunkt().isBefore(heute.calcDay(-x)))series2.getData().add(new XYChart.Data(stromwert.getZeitpunkt().toString(), stromwert.getWert()));
            }
            break;*/
    	}
    }
    
    @FXML protected void handelSubmitButton(ActionEvent event) throws IOException{
    	String id=((Button)event.getSource()).getId();
    	switch (id) {
		case "input": inputTextField.setText(getDirectory(JFileChooser.FILES_ONLY));break;
		case "output":outputTextField.setText(getDirectory(JFileChooser.DIRECTORIES_ONLY));break;
		case "einstellungen": 
			Parent root = FXMLLoader.load(getClass().getResource("fxml_stromprojekt1_Einstellungen.fxml"));
			Stage stage = new Stage(); 
		    stage.setTitle("Stromprojekt Einstellungen");
		    stage.setScene(new Scene(root));
		    stage.show();
		    
		    break;
		case "saveConfig": saveConfig(event); break;
		default:
			break;
		}
    }
    
    private String getDirectory(int selectionMod){
    	JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(selectionMod);
		int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	File file = fc.getSelectedFile();
            return file.getAbsolutePath();
        } else {
        	return getDirectory(selectionMod);
        }
    }
    
    private void saveConfig(ActionEvent event){
    	System.out.println(inputTextField.getText());
    	inputTextField.getText();
    	
//    	((Button)event.getSource()).getScene().getWindow().;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	/*try {
			 final VBox root1 = FXMLLoader.load(getClass().getResource("fxml_stromprojekt1.fxml"));
			scrollbar.setValue(10);
	    	scrollbar.valueProperty().addListener(new ChangeListener<Number>() {
	            public void changed(ObservableValue<? extends Number> ov,
	                    Number old_val, Number new_val) {
	                        root1.setLayoutX(-new_val.doubleValue());
	                }
	        // TODO
	    } ) ; 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	
        // TODO
    
    }
    
    private LinkedList<StromWert> verringernDerWerte(LinkedList<StromWert> tmp){
    	int size = tmp.size();
    	int divider = 2;
    	int rest = size%divider;
    	Iterator<StromWert> it = tmp.iterator();
    	
    	LinkedList<StromWert> end = new LinkedList<StromWert>();
    	float tmpDurchschnnitt=0;
    	Date zeitDurchschnitt=null;
//    	System.out.println();
    	for(int i=0; i<((size-rest)/divider); i++){
    		for(int j=0; j<divider;j++){
    			StromWert tmp3 = it.next();
    			tmpDurchschnnitt+=tmp3.getWert();
    			if(Math.round(divider/2)==j)zeitDurchschnitt = tmp3.getZeitpunkt();
    		}
    		end.add(new StromWert(zeitDurchschnitt, tmpDurchschnnitt/(float)divider, tmpDurchschnnitt/(float)divider, 0));
    		tmpDurchschnnitt=0;
    	}
    	
    	if(rest==1)end.add(it.next());
    	else if(rest>0)System.out.println("BÄÄM");
    	
    	if(end.size()>40){
    		System.out.println("Dividor:"+divider+" Size:"+end.size());
    		return verringernDerWerte(end);
    	}
    	System.out.println("Dividor:"+divider+" Size:"+end.size());
		return end;
    }
}
