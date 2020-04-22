package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Profile Librarian Controller.
 * @author OBL_Team13
 */
public class P_FXML_Profile_Librarian_Controller implements Initializable{

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
    private Text FullnameText;

    @FXML
    private Label FullNameLabel;

    @FXML
    private Text IdText;

    @FXML
    private Label IdLabel;

    @FXML
    private Text birthDateText;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Text emailText;

    @FXML
    private Label emailLabel;

    @FXML
    private Text departmentText;

    @FXML
    private Label departmentLabel;

    @FXML
    private Text rollText;

    @FXML
    private Label rollLabel;

    @FXML
    private Text newPasswordText;

    @FXML
    private PasswordField newPasswordTextField;

    @FXML
    private Text verifyPasswordText;

    @FXML
    private PasswordField verifyPassordTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private Button SaveChangesButton;
    
    /**
     * Initialize the details of the librarian.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
    	FullNameLabel.setText(Employee.getFirstName() + " " + Employee.getLastName());    	
    	IdLabel.setText(Employee.getID());    	
    	birthDateLabel.setText(Employee.getBirthdate().toString());
    	emailLabel.setText(Employee.getEmail());
    	departmentLabel.setText(Employee.getDepartment());    	
    	rollLabel.setText(Employee.getRoll());
	}
    /**
     * When save button clicked it checks if all the fields are not empty and alert with popup if there are problems, if all ok the new details of the librarian saves in the database.
     */
    @FXML
    void SaveChangesButtonClicked(ActionEvent event) {
    	
    	if(newPasswordTextField.getText().toString().equals(verifyPassordTextField.getText().toString()) == false)
    		AlertBox.display("Error", "Passwords dont match!", "Okay");    	
    	else
    		Client.SaveChanges_L(newPasswordTextField.getText());    	
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
    void viewProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewStudentProfile();
    }
    /**
     * Press on the menu button to view student profile.
     */
    @FXML
    void initialize() {
    	
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert FullnameText != null : "fx:id=\"FullnameText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert FullNameLabel != null : "fx:id=\"FullNameLabel\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert IdText != null : "fx:id=\"IdText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert IdLabel != null : "fx:id=\"IdLabel\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert birthDateText != null : "fx:id=\"birthDateText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert birthDateLabel != null : "fx:id=\"birthDateLabel\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert departmentText != null : "fx:id=\"departmentText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert departmentLabel != null : "fx:id=\"departmentLabel\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert rollText != null : "fx:id=\"rollText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert rollLabel != null : "fx:id=\"rollLabel\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert newPasswordText != null : "fx:id=\"newPasswordText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert newPasswordTextField != null : "fx:id=\"newPasswordTextField\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert verifyPasswordText != null : "fx:id=\"verifyPasswordText\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert verifyPassordTextField != null : "fx:id=\"verifyPassordTextField\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";
        assert SaveChangesButton != null : "fx:id=\"SaveChangesButton\" was not injected: check your FXML file 'P_fxml_Profile_Librarian.fxml'.";

    }
}
