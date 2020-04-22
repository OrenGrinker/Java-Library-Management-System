package logics;

import java.util.ArrayList;

//import application.Book;
//import application.ManyBooks;
import application.ManyRents;
import application.Rent;
import application.Pipe;
import application.Router;
import controllers.CurrentlyRentedBooks_Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CurrentlyRentedBooks implements Runnable {

private static Rent testRent;// = new Rent();
	
	private Stage stage;
	
	public CurrentlyRentedBooks (Stage stage) {
		this.stage = stage;		
	}
	
	@Override
	public void run() {
		
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/CurrentlyRentedBooks.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(CurrentlyRentedBooks.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!!");
			e.printStackTrace();
		}
	}
	
	public static void DeliverMessage(Object pipe) {
		
		System.out.println("In DeliverMessage of book list");
		System.out.println("A");
		String command	= ((Pipe)pipe).getCommand();
		Object data		= ((Pipe)pipe).getData();
		
		System.out.println("Command: " + command + " data: " + data);
		if(command.equals("loadMyCurrentBooksResult")){
			System.out.println("c");
			System.out.println("in the if statment");
			ArrayList<Rent> MyRents = new ArrayList<Rent>();
			MyRents  = ((ManyRents)data).getAllRents();
			
			System.out.println("MyRents  is: " + MyRents );
			ManyRents.clearStaticRents();
			
			for(Rent rent: MyRents) {
				try {
					System.out.println("Adding Rent" + rent + " to MyRents");
					ManyRents.addRentToStatic(rent);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("before");
			ManyRents.isLoaded = true;
			Router.MyCurrentBook();
			
		}
		System.out.println("End message");
	}

}
