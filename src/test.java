import io.DataHandler;

import java.io.File;



public class test {
	public static void main(String[] args) {
		DataHandler test = new DataHandler();//new File("C:\\Users\\Tim\\Desktop\\export01.txt")
		test.setFile(new File("C:\\Users\\Tim\\Desktop\\export01.txt"));
		
		DataHandler test2 = new DataHandler();
		test2.setFile(new File("C:\\Users\\Tim\\Desktop\\export02.txt"));
		
//		try {
//			test.writeHTMLOutput();
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, e);
//		}
		
		try {
			test.readExportTxt();
			test2.readExportTxt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addList(test2.getDataHolder());
		System.out.println(test);
	}
}
