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
 * open page of returning a book.
 * @author Obl_Team13.
 *
 */
public class RAB_ReturnABook implements Runnable{

	private Stage stage;
	
	/**
	 * constructor for RAB_ReturnABook.
	 * @param stage stage type.
	 */
	public RAB_ReturnABook(Stage stage) {		
		this.stage = stage;		
	}
	
	/**
	 * initialize screen.
	 */
	@Override
	public void run() {
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/RAB_fxml_ReturnABook.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(RAB_ReturnABook.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!!");
			e.printStackTrace();
		}		
	}

	/**
	 * alert the user status or load information of book renting.
	 * @param pipe, object of Pipe class, contains command-string and data.
	 */
	public static void DeliverMessage(Object pipe) {		
		
		String message = ((Pipe)pipe).getCommand();
		Object data = ((Pipe)pipe).getData();
		
		if(message.equals("fetchReturnDataResult")) {
			if(data == null)
				Platform.runLater(new AlertBox(1, "Return", "Sorry, I couldn't find a match!", "Close"));
			else {
				fetchedDateForReturnABook.setData((ArrayList<Object>)(data));
				System.out.println("DeliverMessage is: " + fetchedDateForReturnABook.getData());
				Router.ReturnABook();
			}
		}
		
		else if(message.equals("returnABookResult")) {
			String status = (String)data;			
			Platform.runLater(new AlertBox(1,"Return", "Success! Your account status is: " + status + ".", "Awesome!"));			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
