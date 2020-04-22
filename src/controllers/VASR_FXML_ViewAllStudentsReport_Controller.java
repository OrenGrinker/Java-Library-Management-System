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
 * View All Students Report Controller.
 * @author OBL_Team13
 */
public class VASR_FXML_ViewAllStudentsReport_Controller implements Initializable{

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
    private TableView<NonStaticUser> studentsViewTable;

    @FXML
    private TableColumn<NonStaticUser, String> idColumn;

    @FXML
    private TableColumn<NonStaticUser, String> passColumn;

    @FXML
    private TableColumn<NonStaticUser, String> firstNameColumn;

    @FXML
    private TableColumn<NonStaticUser, String> lastNameColumn;

    @FXML
    private TableColumn<NonStaticUser, String> emailColumn;

    @FXML
    private TableColumn<NonStaticUser, String> phoneNumberColumn;

    @FXML
    private TableColumn<NonStaticUser, String> birthdayColumn;

    @FXML
    private TableColumn<NonStaticUser, String> facultyColumn;

    @FXML
    private TableColumn<NonStaticUser, String> genderColumn;

    @FXML
    private TableColumn<NonStaticUser, String> updates2EmailColumn;

    @FXML
    private TableColumn<NonStaticUser, String> updates2SMSColumn;

    @FXML
    private TableColumn<NonStaticUser, String> statusColumn;

    private ArrayList<NonStaticUser> loadedStudents;
    
    /**
     * Initialize the students list in the table of the report result.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	try {

			System.out.println("In initialize -> " + ManyNonStaticStudents.getAllStaticUsers());

			idColumn.setCellValueFactory(				new PropertyValueFactory<>("ID"));
			passColumn.setCellValueFactory(				new PropertyValueFactory<>("Pass"));
			firstNameColumn.setCellValueFactory(		new PropertyValueFactory<>("FirstName"));
			lastNameColumn.setCellValueFactory(			new PropertyValueFactory<>("LastName"));
			emailColumn.setCellValueFactory(			new PropertyValueFactory<>("Email"));
			phoneNumberColumn.setCellValueFactory(		new PropertyValueFactory<>("PhoneNumber"));
			birthdayColumn.setCellValueFactory(			new PropertyValueFactory<>("Birthdate"));
			facultyColumn.setCellValueFactory(			new PropertyValueFactory<>("Faculty"));
			genderColumn.setCellValueFactory(			new PropertyValueFactory<>("Gender"));
			updates2EmailColumn.setCellValueFactory(	new PropertyValueFactory<>("GetUpdatesToEmail"));
			updates2SMSColumn.setCellValueFactory(		new PropertyValueFactory<>("GetUpdatesToPhone"));
			statusColumn.setCellValueFactory(			new PropertyValueFactory<>("Status"));
			
			
	    	
			loadedStudents = new ArrayList<NonStaticUser>();
			loadedStudents = ManyNonStaticStudents.getAllStaticUsers();
	    	ObservableList<NonStaticUser> observableList = FXCollections.observableArrayList();

	    	System.out.println("observableList: " + observableList);

	    	for(NonStaticUser user : loadedStudents) {
	    		System.out.println("Adding book: " + user);
	    		observableList.add(user);
	    	}

	    	System.out.println("Full list is: " + loadedStudents);

	    	studentsViewTable.setItems(observableList);
	    	studentsViewTable.getColumns().addAll(	idColumn,
									    			passColumn,
									    			firstNameColumn,
									    			lastNameColumn,
									    			emailColumn,
									    			phoneNumberColumn,
									    			birthdayColumn,
									    			facultyColumn,
									    			genderColumn,
									    			updates2EmailColumn,
									    			updates2SMSColumn,
									    			statusColumn);
	    	
	    	ManyBooks.isLoaded = false;
	    	
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
     * Press back to the report manager.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
    	Router.ReportManager();
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

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert studentsViewTable != null : "fx:id=\"studentsViewTable\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert passColumn != null : "fx:id=\"passColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert firstNameColumn != null : "fx:id=\"firstNameColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert lastNameColumn != null : "fx:id=\"lastNameColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert emailColumn != null : "fx:id=\"emailColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert phoneNumberColumn != null : "fx:id=\"phoneNumberColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert birthdayColumn != null : "fx:id=\"birthdayColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert facultyColumn != null : "fx:id=\"facultyColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert genderColumn != null : "fx:id=\"genderColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert updates2EmailColumn != null : "fx:id=\"updates2EmailColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert updates2SMSColumn != null : "fx:id=\"updates2SMSColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'VASR_fxml_ViewAllStudentsReport.fxml'.";

    }
	
}

