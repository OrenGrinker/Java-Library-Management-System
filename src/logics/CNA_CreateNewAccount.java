package logics;

import controllers.*;

import java.util.ArrayList;
import java.util.Date;

import application.*;
import fxml.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *CNA_CreateNewAccount class that create new account. 
 * @author OBL Team13.
 *
 */

public class CNA_CreateNewAccount implements Runnable {

	private Stage stage;
	
	/**
	 * constructor  that create new account. 
	 * @param	stage stage javafx
	 */
	public CNA_CreateNewAccount(Stage stage) {		
		this.stage = stage;		
	}

	@Override
	public void run() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/CNA_fxml_CreateNewAccount.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(CNA_CreateNewAccount.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new CNA_FXML_CreateNewAccount_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! CNA_CreateNewAccount");
			e.printStackTrace();
		}
	}

	/**
	 *  function that delivery message for  displayed on the screen. 
	 * @param data	Contains information if the action was successful.
	 */ 
	public static void DeliverMessage(Object data) {
		System.out.println("in CNA_CreateNewAccount DeliverMessage");	
		
		boolean isSuccessful = (boolean)data;
		
		if(isSuccessful) {
			System.out.println("Added new account");
			Platform.runLater(new AlertBox(1, "Create New Account", "A new account created successfully", "Awesome!"));
		}
		else {
			System.out.println("User already exists!");
			Platform.runLater(new AlertBox(1, "Create New Account", "This user already exists!", "OOF!"));
		}		
	}	
}














