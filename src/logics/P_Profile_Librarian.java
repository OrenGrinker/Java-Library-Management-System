package logics;

import java.util.ArrayList;

import application.*;
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
 *Profile_Librarian class that open the profile of librarian. 
 * @author OBL Team13.
 *
 */
public class P_Profile_Librarian implements Runnable {

	private Stage stage;
	
	/**
	 * constructor  that open the profile of librarian.
	 * @param	stage stage javafx.
	 */
	public P_Profile_Librarian(Stage stage) {		
		this.stage = stage;		
	}

	@Override
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/P_fxml_Profile_Librarian.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(P_Profile_Librarian.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new P_FXML_Profile_Librarian_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! P_Profile_Librarian");
			e.printStackTrace();
		}
		
	}

	/**
	 * function that delivery message by the data.
	 * @param data	contain data about the librarian.
	 */
	public static void DeliverMessage(Object data) {
		
		String id		= ((ArrayList<String>)(data)).get(0).toString();
		String newPass	= ((ArrayList<String>)(data)).get(1).toString();
		
		Employee.setPass(newPass);
		
		Platform.runLater(new AlertBox(1, "Profile-Librarian", "Information Changed Succesfully", "Awesome!"));		
	}
	
}
