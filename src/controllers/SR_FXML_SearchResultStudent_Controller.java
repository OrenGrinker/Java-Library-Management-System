package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.AlertBox;
import application.Book;
import application.Client;
import application.ManyBooks;
import application.PdfSlave;
import application.Router;
import application.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Search Result Student Controller.
 * @author OBL_Team13
 */
public class SR_FXML_SearchResultStudent_Controller implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem myProfileMenuItem;

    @FXML
    private MenuItem signOutMenuItem;

    @FXML
    private MenuItem myBooksMenuItem;

    @FXML
    private MenuItem searchABookMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Label nOCLabel;

    @FXML
    private Label shelfLabel;
    
    @FXML
    private TableView<Book> tableViewID;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private Button orderBookButton;

    @FXML
    private Button closeButton;
    
    @FXML
    private Button viewTableOfContentsButton;

    private ArrayList<Book> loadedBooks;
    
    /**
     * Initialize the books in the table of the search result by student.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	System.out.println("isLoaded: " + ManyBooks.isLoaded);
    	
		if(ManyBooks.isLoaded)
		{
			try {
		    	
				tableViewID.getItems().clear();
				
				System.out.println("In initialize -> " + ManyBooks.getAllStaticBooks());
				
				bookNameColumn.setCellValueFactory(		new PropertyValueFactory<>("Name"));
				authorColumn.setCellValueFactory(		new PropertyValueFactory<>("Author"));
				categoryColumn.setCellValueFactory(		new PropertyValueFactory<>("Category"));
				
		    	loadedBooks = ManyBooks.getAllStaticBooks();
		    	ObservableList<Book> observableList = FXCollections.observableArrayList();
		    	
		    	for(Book book : loadedBooks) {
		    		observableList.add(book);
		    	}
		    	
		    	tableViewID.setItems(observableList);
		    	tableViewID.getColumns().addAll(bookNameColumn, authorColumn, categoryColumn);
		    	ManyBooks.isLoaded = false;
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//orderBookButton.setDisable(true);
		}
		
	}
    /**
     * Press on specific row and get the information about this book.
     */
    @FXML
    void mouseClickedOnRow(MouseEvent event) {
    	Book book = tableViewID.getSelectionModel().getSelectedItem();
    	//User user = new User();
    	String status = User.getStatus();
    	nOCLabel.setText(book.getNumberOfAvailabeCopies());
    	shelfLabel.setText(book.getPlaceOnShelf());
    	 //&& User.getStatus() =="active"
    	if(Integer.parseInt(book.getNumberOfAvailabeCopies())==0 ) {
    		if(User.getStatus().equals("active"))
    		orderBookButton.setDisable(false);
    		//Client.attemptOrderBook(tableViewID.getSelectionModel().getSelectedItem().getSerial(), User.getID());
    	}

    	else
    		{
    		orderBookButton.setDisable(true);
    		//Client.attemptOrderBook(tableViewID.getSelectionModel().getSelectedItem().getSerial(), User.getID());
    	}
    	

    }
    /**
     * Press on the order book button to order a book with 0 available copies, if there are available copies the button is disable.
     */  
    @FXML
    void orderBookButtonClicked(ActionEvent event) {
    	System.out.println("In orderBookButtonClicked");
    	try {
    		Book book = tableViewID.getSelectionModel().getSelectedItem();
        	System.out.println("book.getSerial(): " + book.getSerial() + " User.getID(): " + User.getID());
        	Client.attemptOrderBook(book.getSerial(), User.getID());
        	Platform.runLater(new AlertBox(1,"Book order","We get your order","OK"));
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    @FXML
    void viewTableOfContentsButtonClicked(ActionEvent event) {
    	Book book = tableViewID.getSelectionModel().getSelectedItem();
    	String filePath = PdfSlave.writeFileOnPC(book.getSerial(), book.getTableOfContentAsPdf());
    	PdfSlave.openFile(filePath);
    }
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }
    /**
     * Press on the menu button to see my profile.
     */
    @FXML
    void closeButtonClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the my books button to see the books that the user renting.
     */
    @FXML
    void myBooksMenuItemClicked(ActionEvent event) {
    	Router.MyBook();
    }
    /**
     * Press on the menu button to see my profile.
     */
    @FXML
    void myProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void searchABookMenuItemClicked(ActionEvent event) {
    	Router.SearchABookStudent();
    }
    /**
     * Press on the menu button to sign out from the application.
     */
    @FXML
    void signOutMenuItemClicked(ActionEvent event) {
    	Router.Login();
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert myBooksMenuItem != null : "fx:id=\"myBooksMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert searchABookMenuItem != null : "fx:id=\"searchABookMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert tableViewID != null : "fx:id=\"tableViewID\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert bookNameColumn != null : "fx:id=\"bookNameColumn\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert authorColumn != null : "fx:id=\"authorColumn\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert categoryColumn != null : "fx:id=\"categoryColumn\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert nOCLabel != null : "fx:id=\"nOCLabel\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert shelfLabel != null : "fx:id=\"shelfLabel\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert orderBookButton != null : "fx:id=\"orderBookButton\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
        assert viewTableOfContentsButton != null : "fx:id=\"viewTableOfContentsButton\" was not injected: check your FXML file 'SR_fxml_SearchResultStudent.fxml'.";
    }
	
}
