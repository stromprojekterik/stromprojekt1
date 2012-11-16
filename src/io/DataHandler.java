package io;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import sort.Sort;
import data.Date;
import data.StromWert;
import exception.IlligalDateException;



public class DataHandler {
	private File file;
	private File htmlDir;
	private LinkedList<StromWert> DataHolder;
	
	/**
	 * Konstruktor der DateHandler Klasse.
	 */
	public DataHandler() {
		DataHolder = new LinkedList<StromWert>();
	}
	
	/**
	 * Liest die Datei aus welcher die Anwendung die Daten nimmt und speichert diese in einer Data Objekt Liste.
	 * 
	 * @throws IlligalDateException 
	 * @throws Exception
	 */
	public void readExportTxt() throws IOException, IlligalDateException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		
		line = reader.readLine();
		
		while( (line = reader.readLine()) != null){
			addNewData(line);
		}
		reader.close();
	}
	
	/**
	 * Interne Klasse zum hinzufügen neuer Datensätze zur existierenden Liste und interpretieren des übergebenen Strings.
	 * 
	 * @param exL - String bestehend aus einer Zeile der export Datei
	 * @throws IlligalDateException - Wird geworfen wenn ein falsches Datum initialisiert wurde.
	 */
	private void addNewData(String exL) throws IlligalDateException {
		int s=0;
		
		String tmpS = exL.substring(s, s = exL.indexOf(';', s));
		int s2=0;
		
		int day   =	Integer.parseInt(tmpS.substring(s2, s2 = tmpS.indexOf('.', s2))),
			month = Integer.parseInt(tmpS.substring(++s2, s2 = tmpS.indexOf('.', ++s2))),
			year, hour, minute, second;
		if(10>=tmpS.length()){
			year  = Integer.parseInt(tmpS.substring(++s2));
			hour  = 0;
			minute= 0;
			second= 0;
		}
		else{
			year  = Integer.parseInt(tmpS.substring(++s2, s2 = tmpS.indexOf(' ', ++s2)));
			hour  = Integer.parseInt(tmpS.substring(++s2, s2 = tmpS.indexOf(':', ++s2)));
			minute= Integer.parseInt(tmpS.substring(++s2, s2 = tmpS.indexOf(':', ++s2)));
			second= Integer.parseInt(tmpS.substring(++s2, tmpS.length()));
		}
		
		Date tmp = new Date(day, month, year, hour, minute, second);
		Float wert_orgi = Float.parseFloat(exL.substring(++s, s = exL.indexOf(';', ++s)).replace(',', '.'));
		s = exL.indexOf(';', ++s);		
		
		DataHolder.add(new StromWert(tmp, wert_orgi, 0,	Integer.parseInt(exL.substring(++s, s = exL.indexOf(';', ++s)))));
		//FIXME Add Fehlermeldung bei Konvertierung
	}
	
	/**
	 * @throws IOException 
	 * 
	 * 
	 */
	public void writeHTMLOutput() throws IOException{
		//TODO Take HTML, CSS and JS Files
		
		String path = "C:\\Users\\Tim\\Desktop\\testFX.jar"; 
	    
		java.net.URL url = getClass().getResource("/htmlSources/test.html");
		InputStream is = url.openStream();
		DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
		

		String line;
		StringBuffer tmp = new StringBuffer();
		int i=0;
		while ((line = dis.readLine()) != null) {
			tmp.append(line);
			if((++i)>10)break;
	    }
		JOptionPane.showMessageDialog(null, tmp.toString(),"Ausgabe", JOptionPane.INFORMATION_MESSAGE);
		
		
		//TODO ADD dynamic Datas
		
		
	}
	
	/**
	 * @throws IOException - Wenn es nicht möglich ist die Datei zu schreiben.
	 * 
	 */
	public void writeSAVFile(File savDir) throws IOException{
		ObjectOutputStream oOStream = new ObjectOutputStream(new FileOutputStream(savDir));
		
		oOStream.writeObject(DataHolder);
		oOStream.close();
	}
	
	/**
	 * Liest eine gespeicherte Datei ein, welche über ein File Objekt definiert ist.
	 * 
	 * @throws IOException - Sollte die Datei nicht gefunden werden.
	 * @throws ClassNotFoundException - Sollte die Datei keine Klasse des Typs StromWert enthalten.
	 * 
	 */
	public void readSAVFile(File savFile) throws IOException, ClassNotFoundException{
		ObjectInputStream oIStream = new ObjectInputStream(new FileInputStream(savFile));
		
		@SuppressWarnings("unchecked")
		LinkedList<StromWert> temp = (LinkedList<StromWert>) oIStream.readObject();
		
		DataHolder = Sort.sortTwoList(DataHolder, temp);
		
		oIStream.close();
	}
	
	/**
	 * Für Testzwecke erstellte Klasse.
	 * Es wird explicit das zusammen führen zweier Listen getestet.
	 * 
	 * @param other - Die hinzuzufügende Liste
	 */
	public void addList(LinkedList<StromWert> other){
		DataHolder = Sort.sortTwoList(DataHolder, other);
	}
	
	/** TODO
	 * 
	 * @param file
	 */
	private void checkFile(File file){
		if(!file.canRead()){
			//throw Exeption und implimentierung, nötig?
		}
	}	
	
	
	@Override
	public String toString(){
		String tmp="";
		for (Iterator<StromWert> iterator = DataHolder.iterator(); iterator.hasNext();) {
			tmp += iterator.next().toString()+"\n";
		}
		return tmp;
	}
	
	/**
	 * Setter für das Verzeichnis der Export Datei
	 * 
	 * @param file 
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Setter für das Verzeichnis der HTML Dateien
	 * 
	 * @param htmlDir - Verzeichnis
	 */
	public void setHtmlDir(File htmlDir) {
		this.htmlDir = htmlDir;
	}
		
	/**
	 * Getter des DataHolder Objekts
	 * 
	 * @return Referenz einer Liste aus Data Objekten
	 */
	public LinkedList<StromWert> getDataHolder() {
		return DataHolder;
	}
}
