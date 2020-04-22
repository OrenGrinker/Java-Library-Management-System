package logics;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *  open page of rents.
 * @author Obl_Team13.
 *
 */

public class VR_ViewRents implements Runnable{

	private Stage stage;
	
	/**
	 * constructor of VR_ViewRents.
	 * @param stage stage type.
	 */
	
	public VR_ViewRents(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * initialize the screen.
	 */
	@Override
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VR_fxml_ViewRents.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(VR_ViewRents.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! VR_ViewRents");
			e.printStackTrace();
		}
		
	}

}
