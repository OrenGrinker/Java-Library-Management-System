package logics;

import application.AlertBox;
import controllers.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *Edit A Book class to edit a book for librarian. 
 * @author OBL Team13.
 *
 */

public class EAB_EditABook implements Runnable{

	private Stage stage;
	
	/**
	 * constructor  to edit a book.
	 * @param	stage stage javafx.
	 */
	
	public EAB_EditABook(Stage stage) {		
		this.stage = stage;		
	}
	
	@Override
	public void run() {
		
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/EAB_fxml_EditABook.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(EAB_EditABook.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! EAB_EditABook");
			e.printStackTrace();
		}
		
	}

	/**
	 * function that delivery message for  displayed on the screen. 
	 * @param data	Contains information if the action was successful.
	 */
	
	public static void DeliverMessage(Object data) {

		System.out.println("in EAB_EditABook DeliverMessage");	
		
		boolean isSuccessful = (boolean)data;
		
		if(isSuccessful) {
			System.out.println("Edit a book");
			Platform.runLater(new AlertBox(1, "Edit A Book", "The book has been edited successfully", "Awesome!"));
		}
		else {
			System.out.println("Book couldn't be edited");
			Platform.runLater(new AlertBox(1, "Edit A Book", "This book couldn't be edited for some reason", "OOF!"));
		}
		
	}
	
	
	
	
}
