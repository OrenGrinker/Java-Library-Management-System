package logics;

import controllers.*;

import java.util.ArrayList;

import application.*;
import fxml.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *Profile class that open the profile of student. 
 * @author OBL Team13.
 *
 */

public class P_Profile implements Runnable {
	
	private Stage stage;
	
	/**
	 * constructor  that open the profile of student.
	 * @param	stage stage javafx.
	 */
	
	public P_Profile(Stage stage) {		
		this.stage = stage;		
	}

	@Override
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/P_fxml_Profile.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(P_Profile.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new P_FXML_Profile_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! P_Profile");
			e.printStackTrace();
		}
		
	}

	/**
	 * function that delivery message by the data.
	 * @param data	contain data about the student.
	 */
	
	public static void DeliverMessage(Object data) {

		String id		= ((ArrayList<String>)(data)).get(0).toString();
		String email	= ((ArrayList<String>)(data)).get(1).toString();
		String phone	= ((ArrayList<String>)(data)).get(2).toString();
		String newPass	= ((ArrayList<String>)(data)).get(3).toString();		

		User.setEmail(email);
		User.setPhoneNumber(phone);
		User.setPass(newPass);		
		
		Platform.runLater(new AlertBox(1, "Profile", "Information Changed Succesfully", "Awesome!"));
		
	}
	
}
