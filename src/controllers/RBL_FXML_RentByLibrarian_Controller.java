package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Rent By Librarian Controller.
 * @author OBL_Team13
 */
public class RBL_FXML_RentByLibrarian_Controller implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem myProfileMenuItem;

    @FXML
    private MenuItem createNewProfileMenuItem;

    @FXML
    private MenuItem viewProfileMenuItem;

    @FXML
    private MenuItem signOutMenuItem;

    @FXML
    private MenuItem searchMenuItem;

    @FXML
    private MenuItem returnABookMenuItem;

    @FXML
    private MenuItem stockManagmentMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TextField studentIDTextField;

    @FXML
    private TextField SerialNumberTextField;

    @FXML
    private Button loadButton;

    @FXML
    private TextField BookNameTextField;

    @FXML
    private DatePicker dateOfRentDatePicker;

    @FXML
    private DatePicker returnDateDatePicker;

    @FXML
    private Label nOACLabel;

    @FXML
    private Label placeOnShelf;

    @FXML
    private Button backButton;

    @FXML
    private Button rentButton;

    String nOAC = "Number Of Available Copies: ";
    String pOS	= "Place On Shelf: ";
    
    java.util.Date todaysDate = new java.util.Date();
    
    LocalDate todaysLocalDate;
    
    /**
     * Initialize the details about the book.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	todaysLocalDate = DateConvertor.DateToLocalDate(todaysDate);
    	
    	if(ManyBooks.lookForThisBookBySerial.equals("") == false) {
    		//Book Serial is known to us	<-> Came here from SAB
    		
    		Book book = ManyBooks.getStaticBook(ManyBooks.lookForThisBookBySerial);    	
    		
    		boolean isPopular = (Integer.parseInt(book.getNumberOfAvailabeCopies()) == 0);    		
    		SerialNumberTextField.setText(book.getSerial());
    		BookNameTextField.setText(book.getName());
    		dateOfRentDatePicker.setValue(todaysLocalDate);
    		returnDateDatePicker.setValue(isPopular?todaysLocalDate.plusDays(3): todaysLocalDate.plusDays(14));	//isPopular? (Today + 3) : (Today + 14);
    		
    		nOACLabel.setText(nOAC + book.getNumberOfAvailabeCopies());
    		placeOnShelf.setText(pOS + book.getPlaceOnShelf());    	
    		
    		dateOfRentDatePicker.setDisable(true);
    		returnDateDatePicker.setDisable(true);
    	}
    	
    	if(User.lookForThisUserByID.equals("") == false) {
    		//User ID is known to us		<-> Came here from VSP    		
    		studentIDTextField.setText(User.getID());
    	}
		
	}
    /**
     * Press on the rent button, if one of the text fields is empty, an alert with popup will jump to the screen.
     */
    @FXML
    void rentButtonClicked(ActionEvent event) {
    	if(studentIDTextField.getText().equals("")) {
    		Platform.runLater(new AlertBox(1,"Rent","You must enter a student ID","Okay"));
    		return;
    	}
    	
    	if(BookNameTextField.getText().equals("")) {
    		Platform.runLater(new AlertBox(1,"Rent","You must enter a book name","Okay"));
    		return;
    	}
    	
    	if(SerialNumberTextField.getText().equals("")) {
    		Platform.runLater(new AlertBox(1,"Rent","You must enter a book serial","Okay"));
    		return;
    	}    	
    	
    	java.sql.Date dateOfRentSqlDate = DateConvertor.localDateToSqlDate(dateOfRentDatePicker.getValue());
    	java.sql.Date returnDateSqlDate = DateConvertor.localDateToSqlDate(returnDateDatePicker.getValue());
    	
    	Client.attemptRentBook(studentIDTextField.getText(),SerialNumberTextField.getText(), dateOfRentSqlDate, returnDateSqlDate);
    	
    }
 
    @FXML
    void SerialNumberTextFieldEnterPressed(ActionEvent event) {
    	Client.loadBookBySerial(SerialNumberTextField.getText());
    }
    /**
     * Press on the load book by serial button to load book to the window by serial number.
     */
    @FXML
    void loadButtonClicked(ActionEvent event) {
    	Client.loadBookBySerial(SerialNumberTextField.getText());
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
    	Router.SearchABookLibrarian();
    }
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }
    /**
     * Press on the menu button to create new account.
     */
    @FXML
    void createNewProfileMenuItemClicked(ActionEvent event) {
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
    void returnABookMenuItemCLicked(ActionEvent event) {
    	Router.ReturnABook();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void searchMenuItemClicked(ActionEvent event) {
    	Router.SearchABookLibrarian();
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
    	assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert createNewProfileMenuItem != null : "fx:id=\"createNewProfileMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert searchMenuItem != null : "fx:id=\"searchMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert studentIDTextField != null : "fx:id=\"studentIDTextField\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert SerialNumberTextField != null : "fx:id=\"SerialNumberTextField\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert loadButton != null : "fx:id=\"loadButton\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert BookNameTextField != null : "fx:id=\"BookNameTextField\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert dateOfRentDatePicker != null : "fx:id=\"dateOfRentDatePicker\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert returnDateDatePicker != null : "fx:id=\"returnDateDatePicker\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert nOACLabel != null : "fx:id=\"nOACLabel\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert placeOnShelf != null : "fx:id=\"placeOnShelf\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";
        assert rentButton != null : "fx:id=\"rentButton\" was not injected: check your FXML file 'RBL_fxml_RentByLibrarian.fxml'.";

    }
    
}
