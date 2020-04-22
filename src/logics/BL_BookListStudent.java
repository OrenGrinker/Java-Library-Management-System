package logics;

import java.util.ArrayList;

//import application.Book;
//import application.ManyBooks;
import application.ManyRents;
import application.Rent;
import application.Pipe;
import application.Router;
import controllers.SM_FXML_StockManagment_Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *BL_BookListLibrarian class that open book list for student.
 * @author OBL Team13.
 *
 */

public class BL_BookListStudent implements Runnable {

private static Rent testRent;// = new Rent();
	
	private Stage stage;
	
	/**
	 * constructor  that open book list for student.
	 * @param	stage stage javafx.
	 */
	
	public BL_BookListStudent (Stage stage) {
		this.stage = stage;		
	}
	
	@Override
	public void run() {
		
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/BL_fxml_BookListStudent.fxml"));						
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
	
	/**
	 *  function that delivery message for  displayed on the screen. 
	 * @param pipe	Contains information that will be displayed on the screen.
	 */
	public static void DeliverMessage(Object pipe) {
		
		System.out.println("In DeliverMessage of book list");
		System.out.println("A");
		String command	= ((Pipe)pipe).getCommand();
		Object data		= ((Pipe)pipe).getData();
		
		System.out.println("Command: " + command + " data: " + data);
		if(command.equals("loadMyBooksResult")){
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
			Router.MyBook();
			
		}
		System.out.println("End message");
	}

}
