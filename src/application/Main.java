package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Main class to run Client side.
 * @author OBL_Team13
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
				
		try{
			new Router(primaryStage);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
