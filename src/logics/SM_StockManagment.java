package logics;

import java.util.ArrayList;

import application.*;
import controllers.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open page of stock managnent.
 * @author Obl_Team13.
 *
 */
public class SM_StockManagment implements Runnable{
 
	private static Book testBook = new Book();
	
	private Stage stage;
	
	/**
	 * constructor of SM_StockManagment.
	 * @param stage stage type.
	 */
	public SM_StockManagment(Stage stage) {
		this.stage = stage;		
	}
	
	/**
	 * initialize the screen.
	 */
	@Override
	public void run() {
		
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/SM_fxml_StockManagment.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(SM_StockManagment.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new SM_FXML_StockManagment_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! SM_StockManagment");
			e.printStackTrace();
		}
		
	}

	/**
	 * load books information to static book in MantBook class.
	 * @param pipe object of Pipe class, contains ManyBooks.
	 */
	public static void DeliverMessage(Object pipe) {
		
		System.out.println("In DeliverMessage"); 
		
		String command	= ((Pipe)pipe).getCommand();
		Object data		= ((Pipe)pipe).getData();
		
		System.out.println("Command: " + command + " data: " + data);
		
		
		
		
		
		
		if(command.equals("RemoveABookResult") || (command.equals("EditABookResult"))) {
			
			boolean isRemove = command.equals("RemoveABookResult");
			
			if((boolean)data == true) {
				Platform.runLater(new AlertBox(1,"Stock Managment", "Book "+ (isRemove?"removed":"edited") +" succesfully", "Awesome!"));
				ManyBooks.clearStaticbooks();
				ManyBooks.isLoaded = false;
				Router.StockManagment();	//This will reload this page which will reload the book list and update the gui
			}
			else
				Platform.runLater(new AlertBox(1,"Stock Managment", "Book couldn't be " + (isRemove?"removed":"edited") + " for some reason!", "OOF"));
		}		
		
		else if(command.equals("loadBooksResult")) {

			ManyBooks.clearStaticbooks();
			
			ArrayList<Book> library = new ArrayList<Book>();
			library = ((ManyBooks)data).getAllBooks();
			
			for(Book book : library) {
				try {
					System.out.println("Adding Book " + book + " to the library");
					ManyBooks.addBookToStatic(book);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

			ManyBooks.isLoaded = true;
			Router.StockManagment();
			
		}
		System.out.println("End message");
	}

}
