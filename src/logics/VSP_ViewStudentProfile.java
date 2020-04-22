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
 * open page of student profile.
 * @author Obl_Team13.
 * 
 *
 */
public class VSP_ViewStudentProfile implements Runnable{

	private Stage stage;
	
	/**
	 * constructor of the class.
	 * @param stage stage type.
	 */
	public VSP_ViewStudentProfile(Stage stage) {		
		this.stage = stage;		
	}

	/**
	 * initialize the screen.
	 */
	
	@Override
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VSP_fxml_ViewStudentProfile.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(VSP_ViewStudentProfile.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! VSP_ViewStudentProfile");
			e.printStackTrace();
		}
		
	}

	/**
	 * set user details to user class.
	 * @param pipe is instance of Pipe, contains command-string and array list.
	 */
	
	public static void DeliverMessage(Object pipe) {
		
		System.out.println("data is: " + pipe);
		
		String command	= ((Pipe)pipe).getCommand();
		Object data		= ((Pipe)pipe).getData();
		
		try {
			
			User.setID(					(String)((ArrayList<Object>)data).get(0));
			User.setPass(				(String)((ArrayList<Object>)data).get(1)); 
			User.setFirstName(			(String)((ArrayList<Object>)data).get(2));
			User.setLastName(			(String)((ArrayList<Object>)data).get(3));
			User.setEmail(				(String)((ArrayList<Object>)data).get(4));
			User.setPhoneNumber(		(String)((ArrayList<Object>)data).get(5));
			User.setBirthdate(			(java.sql.Date)((ArrayList<Object>)data).get(6));
			User.setFaculty(			(String)((ArrayList<Object>)data).get(7));
			User.setGender(				(String)((ArrayList<Object>)data).get(8));			
			User.setGetUpdatesToEmail(	(String)((ArrayList<Object>)data).get(9));
			User.setGetUpdatesToPhone(	(String)((ArrayList<Object>)data).get(10));					          
			User.setStatus(				(String)((ArrayList<Object>)data).get(11));
			
			if(command.equals("SaveChanges_LSResult"))
				Platform.runLater(new AlertBox(1,"View Student Profile", "Account succesfully updated!", "Yay!"));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		User.isLoaded = true;
		Router.ViewStudentProfile();		
	}
	
}
