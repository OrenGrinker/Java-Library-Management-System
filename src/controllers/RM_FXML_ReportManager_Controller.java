package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import application.Client;
import application.ManyNonStaticEmployees;
import application.ManyNonStaticStudents;
import application.Rent;
import application.Router;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Report Manager Controller.
 * @author OBL_Team13
 */
public class RM_FXML_ReportManager_Controller implements Initializable{

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
    private Button viewStudentsButton;

    @FXML
    private Button viewEmployeesButton;

    @FXML
    private Button createNewReportButton;

    @FXML
    private MenuButton savedReportsMenuButton;

    @FXML
    private MenuItem report2018MenuItem;

    @FXML
    private MenuItem report2017MenuItem;

    @FXML
    private Button viewRentsButton;

    @FXML
    private Button viewLateReturnsButton;

    @FXML
    private Button viewLateReturnsByBook;
    
    @FXML
    private TextField bookNameTextField;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	
		
	}
    
    /**
     * Button to create a new report.
     */
    @FXML
    void createNewReportButtonClicked(ActionEvent event) {
    	Client.createNewReport();
    }

    @FXML
    void report2017MenuItemClicked(ActionEvent event) {
    	Platform.runLater(new AlertBox(1, "Periodic report", "Periodic report for 2017" + 
				"\nTotal Users: " + 302 + 
				"\nActive Users: " + 201 + 
				"\nFrozen Users: " + 82 + 
				"\nLocked Users: " + 19 + 
				"\nTotal number of copies in OBL: " + 5012 + 
				"\nNumber of late students: " + 75, "Close"));
    }

    @FXML
    void report2018MenuItemClicked(ActionEvent event) {
    	Platform.runLater(new AlertBox(1, "Periodic report", "Periodic report for 2018" + 
				"\nTotal Users: " + 459 + 
				"\nActive Users: " + 322 + 
				"\nFrozen Users: " + 105 + 
				"\nLocked Users: " + 32 + 
				"\nTotal number of copies in OBL: " + 7348 + 
				"\nNumber of late students: " + 87, "Close"));
    }
    /**
     * Press on the menu button to return a book.
     */
    @FXML
    void returnABookMenuItemClicked(ActionEvent event) {
    	Router.ReportManager();
    }
    /**
     * Button to view the employees list.
     */
    @FXML
    void viewEmployeesButtonClicked(ActionEvent event) {
    	ManyNonStaticEmployees.isLoaded = false;
    	Router.ViewEmployees();
    }
    /**
     * Button to view the students list.
     */
    @FXML
    void viewStudentsButtonClicked(ActionEvent event) {
    	ManyNonStaticStudents.isLoaded = false;
    	Router.ViewStudents();
    }
    /**
     * Button to view the rents list.
     */
    @FXML
    void viewRentsButtonClicked(ActionEvent event) {
    	Rent.mode = 0;
    	Rent.lookForThisBook = "";
    	Router.ViewRents();
    }
    /**
     * Button to view the late list.
     */
    @FXML
    void viewLateReturnsButtonClicked(ActionEvent event) {
    	Rent.mode = 1;
    	Rent.lookForThisBook = "";
    	Router.ViewRents();
    }
    /**
     * Button to view the report by book name.
     */
    private void reportByBookName() {
    	Rent.mode = 2;
    	Rent.lookForThisBook = bookNameTextField.getText();
    	Router.ViewRents();
    }
    /**
     * Press enter after fill the book name on the text field and show the report by book name.
     */
    @FXML
    void bookNameTextFieldEnterPressed(ActionEvent event) {
    	reportByBookName();
    }
    /**
     * Press enter after fill the book name on the text field and show the report by book name.
     */
    @FXML
    void viewLateReturnsByBookClicked(ActionEvent event) {
    	reportByBookName();
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
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert viewStudentsButton != null : "fx:id=\"viewStudentsButton\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert viewEmployeesButton != null : "fx:id=\"viewEmployeesButton\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert createNewReportButton != null : "fx:id=\"createNewReportButton\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert savedReportsMenuButton != null : "fx:id=\"savedReportsMenuButton\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert report2018MenuItem != null : "fx:id=\"report2018MenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert report2017MenuItem != null : "fx:id=\"report2017MenuItem\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert viewRentsButton != null : "fx:id=\"viewRentsButton\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert viewLateReturnsButton != null : "fx:id=\"viewLateReturnsButton\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert bookNameTextField != null : "fx:id=\"bookNameTextField\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
        assert viewLateReturnsByBook != null : "fx:id=\"viewLateReturnsByBook\" was not injected: check your FXML file 'RM_fxml_ReportManager.fxml'.";
    }
	
}
