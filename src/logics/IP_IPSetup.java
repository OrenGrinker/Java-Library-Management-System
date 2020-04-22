package logics;

import controllers.*;

import java.io.IOException;

import application.*;
import fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import runUs.ClientConsole;

/**
 *IPSetup class that receives IP. 
 * @author OBL Team13.
 *
 */

public class IP_IPSetup {

	/**
	 * constructor  that receives IP.
	 * @param	stage stage javafx.
	 */
	
	public IP_IPSetup(Stage stage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/IP_fxml_SetUp.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(IP_IPSetup.class.getResourceAsStream("/z_images/logo_black.png")));
			
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
	 * A function that checks whether there is a connection between IP and PORT.
	 * @param ip	IP server.
	 * @param port	Name of the server.
	 * @return	Returns true if the connection succeeded, otherwise returns false.
	 */
	
	public static boolean TestConnection(String ip, String port) {
		
		try {
			ClientConsole chat= new ClientConsole(ip, Integer.parseInt(port));		
			AlertBox.display("Connection Test", "Connection succeeded!", "Yay!");
			return true;
		}
		catch(IOException e) {
			AlertBox.display("Connection Test", "Connection to " + ip + " : " + port + " failed!", "OOF");
		}
		
		return false;
		
	}
	
}
