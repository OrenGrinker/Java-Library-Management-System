package logics;

import controllers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import application.*;
import fxml.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *Add A Book class that add new  book. 
 * @author OBL Team13.
 *
 */
public class AAB_AddABook implements Runnable {
	
	private static Stage stage;
	
	/**
	 * constructor  to add new book.
	 * @param	stage stage javafx.
	 */
	
	public AAB_AddABook(Stage stage) {		
		this.stage = stage;		
	}

	@Override
	
	
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/AAB_fxml_AddABook.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(AAB_AddABook.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new AAB_FXML_AddABook_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! AAB_AddABook");
			e.printStackTrace();
		}
		
	}

	/**
	 * function that delivery message for  displayed on the screen. 
	 * @param data	Contains information if the action was successful.
	 */
	public static void DeliverMessage(Object data) {

		System.out.println("in AAB_AddABook DeliverMessage");	
		
		
		boolean isSuccessful = (boolean)data;
		
		if(isSuccessful) {
			System.out.println("Added a book");
			Platform.runLater(new AlertBox(1, "Add A Book", "A new book created successfully", "Awesome!"));
		}
		else {
			System.out.println("Book already exists!");
			Platform.runLater(new AlertBox(1, "Add A Book", "This book already exists!", "OOF!"));
		}
		
	}
	
	
	
	
	
}
