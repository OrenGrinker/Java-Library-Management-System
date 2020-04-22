
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Extend Time Controller.
 * @author OBL_Team13
 */
public class ET_FXML_ExtendTime_Controller {

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
    private MenuItem searchMenuItem;

    @FXML
    private MenuItem returnABookMenuItem;

    @FXML
    private MenuItem stockManagmentMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Label extendRentTimeLabel;

    @FXML
    private Label bookNameLabel;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private Label serialNumberLabel;

    @FXML
    private TextField serialNumberTextField;

    @FXML
    private Label previousDateLabel;

    @FXML
    private DatePicker previousDateDatePicker;

    @FXML
    private Label newDateLabel;

    @FXML
    private DatePicker newDateDatePicker;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

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

    @FXML
    void stockManagmentMenuItemClicked(ActionEvent event) {
    	//Router.StockManagment();
    }

    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {
    	//Router.ViewStudentProfile();
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert createNewProfileMenuItem != null : "fx:id=\"createNewProfileMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert searchMenuItem != null : "fx:id=\"searchMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert extendRentTimeLabel != null : "fx:id=\"extendRentTimeLabel\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert bookNameLabel != null : "fx:id=\"bookNameLabel\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert bookNameTextField != null : "fx:id=\"bookNameTextField\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert serialNumberLabel != null : "fx:id=\"serialNumberLabel\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert serialNumberTextField != null : "fx:id=\"serialNumberTextField\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert previousDateLabel != null : "fx:id=\"previousDateLabel\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert previousDateDatePicker != null : "fx:id=\"previousDateDatePicker\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert newDateLabel != null : "fx:id=\"newDateLabel\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert newDateDatePicker != null : "fx:id=\"newDateDatePicker\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'ET_fxml_ExtendTime.fxml'.";

    }
}
