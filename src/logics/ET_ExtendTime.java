package logics;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *ET_ExtendTime class  for  Extend book rent time. 
 * @author OBL Team13.
 *
 */
public class ET_ExtendTime {

	/**
	 * constructor   for  Extend book rent time.
	 * @param	stage stage javafx.
	 */
	
	public ET_ExtendTime(Stage stage){
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/ET_fxml_ExtendTime.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(SAB_SearchABook_Student.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!!");
			e.printStackTrace();
		}
	}
	
}
