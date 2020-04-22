package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * View All Employees Report Controller.
 * @author OBL_Team13
 */
public class VAER_FXML_ViewAllEmployeesReport_Controller implements Initializable{

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
    private Button backButton;

    @FXML
    private TableView<NonStaticEmployee> employeesViewTable;

    @FXML
    private TableColumn<NonStaticEmployee, String> idColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> rollColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> passColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> firstNameColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> lastNameColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> emailColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> departmentColumn;

    @FXML
    private TableColumn<NonStaticEmployee, String> birthdayteColumn;
    
    private ArrayList<NonStaticEmployee> loadedEmployees;

    /**
     * Initialize the employees list in the table of the report result.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	try {

			System.out.println("In initialize -> " + ManyNonStaticEmployees.getAllStaticEmployees());

			idColumn.setCellValueFactory(			new PropertyValueFactory<>("ID"));
			rollColumn.setCellValueFactory(			new PropertyValueFactory<>("Roll"));
			passColumn.setCellValueFactory(			new PropertyValueFactory<>("Pass"));
			firstNameColumn.setCellValueFactory(	new PropertyValueFactory<>("FirstName"));
			lastNameColumn.setCellValueFactory(		new PropertyValueFactory<>("LastName"));
			emailColumn.setCellValueFactory(		new PropertyValueFactory<>("Email"));
			departmentColumn.setCellValueFactory(	new PropertyValueFactory<>("Department"));
			birthdayteColumn.setCellValueFactory(	new PropertyValueFactory<>("Birthdate"));
	    	
			loadedEmployees = new ArrayList<NonStaticEmployee>();
			loadedEmployees = ManyNonStaticEmployees.getAllStaticEmployees();
	    	ObservableList<NonStaticEmployee> observableList = FXCollections.observableArrayList();

	    	System.out.println("observableList: " + observableList);

	    	for(NonStaticEmployee NonStaticEmployee : loadedEmployees) {
	    		System.out.println("Adding book: " + NonStaticEmployee);
	    		observableList.add(NonStaticEmployee);
	    	}

	    	System.out.println("Full list is: " + loadedEmployees);

	    	employeesViewTable.setItems(observableList);
	    	employeesViewTable.getColumns().addAll(idColumn, rollColumn, passColumn, firstNameColumn, lastNameColumn, emailColumn, departmentColumn, birthdayteColumn);
	    	ManyNonStaticEmployees.isLoaded = false;
	    	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    
    @FXML
    void studentsViewTableMouseClicked(MouseEvent event) {

    }

    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {

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
     * Press back to the report manager.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
    	Router.ReportManager();
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

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert employeesViewTable != null : "fx:id=\"employeesViewTable\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert rollColumn != null : "fx:id=\"rollColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert passColumn != null : "fx:id=\"passColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert firstNameColumn != null : "fx:id=\"firstNameColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert lastNameColumn != null : "fx:id=\"lastNameColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert emailColumn != null : "fx:id=\"emailColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert departmentColumn != null : "fx:id=\"departmentColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";
        assert birthdayteColumn != null : "fx:id=\"birthdayteColumn\" was not injected: check your FXML file 'VAER_fxml_ViewAllEmployeesReport.fxml'.";

    }
    
}
