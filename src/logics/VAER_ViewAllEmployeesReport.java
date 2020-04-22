package logics;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open page of reports of employee.
 * @author obl_Team13.
 *
 */
public class VAER_ViewAllEmployeesReport implements Runnable{

	private Stage stage;
	
	/**
	 * constructor of the class.
	 * @param stage stage type.
	 */
	public VAER_ViewAllEmployeesReport(Stage stage) {
		this.stage = stage;
	}

	/**
	 * initialize the screen.
	 */
	@Override
	public void run() {
		
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VAER_fxml_ViewAllEmployeesReport.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(VAER_ViewAllEmployeesReport.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! VAER_ViewAllEmployeesReport");
			e.printStackTrace();
		}
		
	}
	
}
