package logics;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * open page of student report.
 * @author Obl_Team13.
 *
 */
public class VASR_ViewAllStudentsReport implements Runnable{

	private Stage stage;
	
	/**
	 * constructor of the class.
	 * @param stage stage type.
	 */
	public VASR_ViewAllStudentsReport(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * initialize the screen.
	 */
	@Override
	public void run() {
		
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VASR_fxml_ViewAllStudentsReport.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(VASR_ViewAllStudentsReport.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! VASR_ViewAllStudentsReport");
			e.printStackTrace();
		}
		
	}

}
