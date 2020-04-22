package logics;

import application.AlertBox;
import application.Pipe;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open page of search of students results.
 * @author obl_Team13.
 *
 */
public class SR_SearchResultStudent implements Runnable{

	private Stage stage;
	
	/**
	 * constructor for SR_SearchResultStudent.
	 * @param stage stage type.
	 */
	public SR_SearchResultStudent(Stage stage){
		this.stage = stage;
	}

	/**
	 * initialize the screen
	 */
	@Override
	public void run() {
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/SR_fxml_SearchResultStudent.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(SR_SearchResultStudent.class.getResourceAsStream("/z_images/logo_black.png")));
			
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
	 * alert the user of failure or success of making an order.
	 * @param data a variable of boolean type, presenets succesfull or failure of creating order.
	 */
	public static void DeliverMessage(Object data) {

		System.out.println("in SR_SearchResultStudent DeliverMessage");	
		
		boolean isSuccessful = (boolean)data;
		
		if(isSuccessful) {
			System.out.println("Added a order");
			Platform.runLater(new AlertBox(1, "Add A Order", "A new order created successfully", "Awesome!"));
		}
		else {
			System.out.println("The order is not success!");
			Platform.runLater(new AlertBox(1, "Add A Order", "The order is not success!", "OOF!"));
		}
		
	}
		
	
}
