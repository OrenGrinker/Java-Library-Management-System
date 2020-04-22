package logics;

import application.Router;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class P_Password extends Application{


	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/P_fxml_Password.fxml"));						
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.getIcons().add(new Image(P_Password.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
			primaryStage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! P_Password");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
