package logics;

import application.*;
import controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open search result of librarian.
 * @author obl_Team13.
 *
 */
public class SR_SearchResultLibrarian implements Runnable{
	
	private Stage stage;

	/**
	 * constructor of SR_SearchResultLibrarian. 
	 * @param stage stage type.
	 */
	public SR_SearchResultLibrarian(Stage stage){
		this.stage = stage;
	}

	/**
	 * initialize the screen
	 */
	@Override
	public void run() {
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/SR_fxml_SearchResultLibrarian.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(SR_SearchResultLibrarian.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new SR_FXML_SearchResultLibrarian_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!!");
			e.printStackTrace();
		}
		
	}
	
}
