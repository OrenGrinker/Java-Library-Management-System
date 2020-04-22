package application;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.stage.FileChooser;

/**
 * Class for working with PDF files.
 * @author OBL_Team13
 */
public class PdfSlave {

	/**
	 * Open the 'choose file' page.
	 * @return	path of file as string.
	 */
	public static String chooseFile()
	{
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Upload PDF File");
    	File selectedFile = fileChooser.showOpenDialog(null);
    	
    	if (selectedFile == null) {
    		Platform.runLater(new AlertBox(1, "PDF Chooser", "File selection cancelled.", "Close"));
    		return null;
    	}
    	
    	String path = selectedFile.toString();
    	System.out.println("path: " + path);
    	
    	return path;
	}
	
	/**
	 * Upload file as an array of bytes.
	 * @param filePath	string of file path.
	 * @return	an array of bytes.
	 */
	public static byte[] getDataAsByteArray(String filePath) {
		
		byte[] dataArray = null;
		
		if(filePath != null) {   	
	    	try {
	    		File initialFile = new File(filePath);            
	    		dataArray = Files.readAllBytes(initialFile.toPath());
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
    	}
		
		return dataArray;		
	}
	
	/**
	 * Download file to PC.
	 * @param fileName	string for name of file.
	 * @param fileData	array of bytes for file data.
	 * @return	path of file as string.
	 */
	public static String writeFileOnPC(String fileName, byte[] fileData) {
		String filePath = null;
		try {
			filePath = fileName + ".pdf";
			Files.write(Paths.get(filePath), fileData);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * Open file from a file path on PC.
	 * @param filePath	a string for file path.
	 */
	public static void openFile(String filePath) {
		if(Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(filePath);
				Desktop.getDesktop().open(myFile);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
