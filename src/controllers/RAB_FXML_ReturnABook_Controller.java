package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.AlertBox;
import application.Client;
import application.DateConvertor;
import application.Router;
import application.fetchedDateForReturnABook;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Return A Book Controller.
 * @author OBL_Team13
 */
public class RAB_FXML_ReturnABook_Controller implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem myProfileMenuItem;

    @FXML
    private MenuItem createNewMenuItem;

    @FXML
    private MenuItem viewProfileMenuItem;

    @FXML
    private MenuItem signOutMenuItem;

    @FXML
    private MenuItem SearchABookMenuItem;

    @FXML
    private MenuItem returnABookMenuItem;

    @FXML
    private MenuItem stockManagmentMenuItem;

    @FXML
    private MenuItem AboutMenuItem;

    @FXML
    private TextField userIDTextField;

    @FXML
    private TextField serialTextField;

    @FXML
    private Button loadButton;

    @FXML
    private DatePicker dateOfRentDatePicker;

    @FXML
    private DatePicker returnDateDatePicker;

    @FXML
    private ImageView bookImageView;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private Button returnBookButton;

    @FXML
    private Button backButton;

    /**
     * Initialize the details about the book.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	System.out.println("in ReturnABook initialize");
    	returnDateDatePicker.setValue(DateConvertor.DateToLocalDate(new java.util.Date()));
    	returnDateDatePicker.setDisable(true);
    	dateOfRentDatePicker.setDisable(true);
    	bookNameTextField.setDisable(true);
    	
    	if(fetchedDateForReturnABook.isLoaded == true) {
    		
    		System.out.println("initialize 0");
    		ArrayList<Object> data = new ArrayList<Object>();
    		data = fetchedDateForReturnABook.getData();
    		
    		System.out.println("initialize 1");
    		 String rowNumber			= (String)data.get(0);
    		 String bookSerial			= (String)data.get(1);
    		 String userID				= (String)data.get(2);
    		 String bookName			= (String)data.get(3);
    		 java.sql.Date dateOfRent	= (java.sql.Date)data.get(4);
    		 System.out.println("initialize 2");
    		 
    		 userIDTextField.setText(userID);
    		 serialTextField.setText(bookSerial);
    		 bookNameTextField.setText(bookName);
    		 dateOfRentDatePicker.setValue(DateConvertor.sqlDateToLocalDate(dateOfRent));
    		 System.out.println("initialize 3");
    	}
		
	}
    /**
     * Press return button to return book and update the database.
     */
    @FXML
    void returnBookButtonClicked(ActionEvent event) {
    	
    	if(dateOfRentDatePicker.getValue() == null) {
    		Platform.runLater(new AlertBox(1, "Return", "You must first match a user ID with a book serial", "close"));
    		return;
    	}
    	
    	ArrayList<Object> data = new ArrayList<Object>();
    	
    	data.add(userIDTextField.getText());											//User ID
    	data.add(serialTextField.getText());											//Book Serial
    	data.add(DateConvertor.localDateToSqlDate(returnDateDatePicker.getValue()));	//today's date
    	
    	Client.returnABook(data);
    	
    }    
    /**
     * This function looks for a match between the value that the user typing to the database to show the book.
     */
    private void lookForAMatch() {    	
    	dateOfRentDatePicker.setValue(null);
    	bookNameTextField.clear();
    	
    	if(userIDTextField.getText().equals("") || serialTextField.getText().equals("")) {
    		Platform.runLater(new AlertBox(1,"Return A Book", "You must enter a userID and a book serial", "okay"));
    		return;
    	}
    	Client.fetchReturnData(userIDTextField.getText(), serialTextField.getText());
    }
    /**
     * Press enter to search for the book by serial number.
     */
    @FXML
    void userIDTextFieldEnterClicked(ActionEvent event) {
    	lookForAMatch();
    }
    /**
     * Press enter to search for the book by serial number.
     */
    @FXML
    void serialTextFieldEnterClicked(ActionEvent event) {
    	lookForAMatch();
    }
    /**
     * Press load button to search for the book by serial number.
     */
    @FXML
    void loadButtonClicked(ActionEvent event) {
    	lookForAMatch();
    }
    /**
     * Press back to the list of exists books in stock managment.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void AboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void SearchABookMenuItemClicked(ActionEvent event) {
    	Router.SearchABookLibrarian();
    }
    /**
     * Press on the menu button to create new account.
     */
    @FXML
    void createNewMenuItemClicked(ActionEvent event) {
    	Router.CreateNewAccount();
    }
    /**
     * Press on the menu button to see my profile.
     */
    @FXML
    void myProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the menu button to return a book.
     */
    @FXML
    void returnABookMenuItemClicked(ActionEvent event) {
    	Router.ReturnABook();
    }
    /**
     * Press on the menu button to sign out from the application.
     */
    @FXML
    void signOutMenuItemClicked(ActionEvent event) {
    	Router.Login();
    }
    /**
     * Press on the menu button to move to stock managment.
     */
    @FXML
    void stockManagmentMenuItemClicked(ActionEvent event) {
    	Router.StockManagment();
    }
    /**
     * Press on the menu button to view student profile.
     */
    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewStudentProfile();
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert userIDTextField != null : "fx:id=\"userIDTextField\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert serialTextField != null : "fx:id=\"serialTextField\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert loadButton != null : "fx:id=\"loadButton\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert dateOfRentDatePicker != null : "fx:id=\"dateOfRentDatePicker\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert returnDateDatePicker != null : "fx:id=\"returnDateDatePicker\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert bookImageView != null : "fx:id=\"bookImageView\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert bookNameTextField != null : "fx:id=\"bookNameTextField\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert returnBookButton != null : "fx:id=\"returnBookButton\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'RAB_fxml_ReturnABook.fxml'.";

    }
	
}
