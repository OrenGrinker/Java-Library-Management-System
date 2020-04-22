package logics;

import controllers.*;
import application.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open page of search book.
 * @author Obl_Team13.
 *
 */
public class SAB_SearchABook_Student implements Runnable {

	private Stage stage;
	private static ManyBooks manyBooks;
	
	/**
	 * constructor of SAB_SearchABook_Student.
	 * @param stage stage type.
	 */
	public SAB_SearchABook_Student(Stage stage) {	
		
		this.stage = stage;
		manyBooks = new ManyBooks();
		
	}
	
	/**
	 * initialize the screen.
	 */
	@Override
	public void run() {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/SAB_fxml_SearchABook_Student.fxml"));						
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(SAB_SearchABook_Student.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
			new SAB_FXML_SearchABook_Student_Controller();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!!");
			e.printStackTrace();
		}
	}

	/**
	 * add Books to staticsBooks.
	 * @param data object of ManyBooks class.
	 */
	public static void DeliverMessage(Object data) {
		if(data == null) {
			//AlertBox.display("Search Results", "Sorry, we couldn't find anything that matches your search!", "Okay");
		}
		manyBooks = (ManyBooks)data;
		//SAB_FXML_SearchABook_Student_Controller.setUpdated(true);
		ManyBooks.clearStaticbooks();
		ManyBooks.addLibraryToStatic(manyBooks);
		ManyBooks.isLoaded = true;
		Router.SearchResultStudent();
	}
	
	
	
}
