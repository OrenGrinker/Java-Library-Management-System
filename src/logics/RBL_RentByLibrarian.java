package logics;

import application.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open page of renting.
 * @author Obl_Team13.
 *
 */
public class RBL_RentByLibrarian implements Runnable{

	private Stage stage;
	
	/**
	 * Constructor for RBL_RentByLibrarian.
	 * @param stage stage type.
	 */
	public RBL_RentByLibrarian(Stage stage){
		this.stage = stage;
	}

	/**
	 * initialize the screen.
	 */
	@Override
	public void run() {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/RBL_fxml_RentByLibrarian.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(RBL_RentByLibrarian.class.getResourceAsStream("/z_images/logo_black.png")));
			
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
	 * load book info or alert on failure or success of renting attempt.
	 * @param pipe object of Pipe class.
	 */
	public static void DeliverMessage(Object pipe) {

		String command	= ((Pipe)pipe).getCommand();
		Object data		= ((Pipe)pipe).getData();
		
		if(command.equals("loadBookBySerialResult")) {		
			Book book = (Book)data;			
			ManyBooks.clearStaticbooks();
			ManyBooks.addBookToStatic(book);
			ManyBooks.lookForThisBookBySerial = book.getSerial();
			Router.RentByLibrarian();
		}
		else if(command.equals("attemptRentBookResult")) {
			boolean answer = (boolean)data;
			String body = answer? "Book was rented succesfully!" : "Book couldn't be rented for some reason!";
			Platform.runLater(new AlertBox(1, "Rent A Book", body, "okay"));
		}
	}
	
}
