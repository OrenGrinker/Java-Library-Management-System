package logics;

import controllers.*;
import application.*;
import fxml.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *Login class opens the Login screen. 
 * @author OBL Team13.
 *
 */
public class LI_Login implements Runnable{
	 
	private Stage stage;
	
	/**
	 * constructor  that opens the Login screen.
	 * @param	stage stage javafx.
	 */
	public LI_Login(Stage stage) {		
		this.stage = stage;				
	}
	
	@Override
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/LI_fxml_Login.fxml"));
			Scene scene = new Scene(root, 1240.0, 760.0);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(LI_Login.class.getResourceAsStream("/z_images/logo_black.png")));
			
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			
			stage.show();			
		} catch (Exception e) {	
			System.out.println("MUHAHAHAHHAHAHAH");
			e.printStackTrace();				
		}
		
	}
	
	/**
	 * function that delivery message by the data
	 * @param data	Contains information about the user.
	 */
	
	public static void DeliverMessage(Object data) {
		if(data == null) {
			//AlertBox.display("Search Results", "Sorry, we couldn't find anything that matches your search!", "Okay");
		}
		System.out.println("in DeliverMessage");	
		
		ArrayList<Object> userData = (ArrayList<Object>)data;
		
		System.out.println("SIZE: " + userData.size());
		
		try {
			if(userData.size() > 10) {
				User.setID(					(String)userData.get(0));
				User.setPass(				(String)userData.get(1));
				User.setFirstName(			(String)userData.get(2));
				User.setLastName(			(String)userData.get(3));
				User.setEmail(				(String)userData.get(4));
				User.setPhoneNumber(		(String)userData.get(5));
				User.setBirthdate(	  		(java.sql.Date)userData.get(6));
				User.setFaculty(			(String)userData.get(7));
				User.setGender(				(String)userData.get(8));
				User.setGetUpdatesToEmail(	(String)userData.get(9));
				User.setGetUpdatesToPhone(	(String)userData.get(10));
				User.setStatus(				(String)userData.get(11));
			}
			
			else {
				Employee.setID(				(String)userData.get(0));
				Employee.setRoll(			(String)userData.get(1));
				Employee.setPass(			(String)userData.get(2));
				Employee.setFirstName(		(String)userData.get(3));
				Employee.setLastName(		(String)userData.get(4));
				Employee.setEmail(			(String)userData.get(5));
				Employee.setDepartment(		(String)userData.get(6));
				Employee.setBirthdate(	  	(java.sql.Date)userData.get(7));
			}
			if(User.getFirstName() != null || Employee.getFirstName() != null) {
				if((User.getFirstName() != null) && (User.getStatus().toLowerCase().equals("locked") == false))
					Router.ViewProfile();
				else if(Employee.getFirstName() != null)
					Router.ViewProfile();
				else
					Platform.runLater(new AlertBox(1,"Login", "Couldn't log in", "OOF"));
			}
			else {
				LI_FXML_Login_Controller.updated = -1;
				Platform.runLater(new AlertBox(1,"Login", "Couldn't log in", "OOF"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
