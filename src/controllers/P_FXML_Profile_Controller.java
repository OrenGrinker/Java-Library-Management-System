package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.security.auth.UnixNumericUserPrincipal;

import application.AlertBox;
import application.Client;
import application.Router;
import application.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Profile Controller.
 * @author OBL_Team13
 */
public class P_FXML_Profile_Controller implements Initializable{

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
    private Text FullnameText;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Text nameLabel1;

    @FXML
    private Label IDLabel;

    @FXML
    private Text birthDateText;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Text facultyText;

    @FXML
    private Label facultyLabel;

    @FXML
    private Text statusText;

    @FXML
    private Label statusLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Button viewCurrentlyRentedBooksButton;

    @FXML
    private Text emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Text phoneNumberText;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Text newPasswordText;

    @FXML
    private PasswordField NewPasswordTextField;

    @FXML
    private Text veryfyPasswordText;

    @FXML
    private PasswordField VerifyPassordTextField;

    @FXML
    private Button SaveChangesButton;
    
    /**
     * Initialize the details of the student.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	fullNameLabel.setText(User.getFirstName() + " " + User.getLastName());
    	IDLabel.setText(User.getID());
    	birthDateLabel.setText(User.getBirthdate().toString());
    	facultyLabel.setText(User.getFaculty());
    	statusLabel.setText(User.getStatus());
    	emailTextField.setText(User.getEmail());
    	phoneNumberTextField.setText(User.getPhoneNumber());    			
	}
    /**
     * When save button clicked it checks if all the fields are not empty and alert with popup if there are problems, if all ok the new details of the student saves in the database.
     */
    @FXML
    void SaveChangesButtonClicked(ActionEvent event) {

    	if(NewPasswordTextField.getText().toString().equals(VerifyPassordTextField.getText().toString()) == false)
    		AlertBox.display("Error", "Passwords dont match!", "Okay");
    	
    	else if(emailTextField.getText().equals("") || (emailTextField.getText().contains("@") == false))
    		Platform.runLater(new AlertBox(1,"Error", "Must have a valid email", "Okay"));
    	
    	else
    		Client.SaveChanges_S(emailTextField.getText(), phoneNumberTextField.getText(), NewPasswordTextField.getText());
    	
    } 
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
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
    /**
     * Press on the my currently books button to see the books that the user renting now.
     */
    @FXML
    void viewCurrentlyRentedBooksButtonClicked(ActionEvent event) {
    	Router.MyCurrentBook();
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert myBooksMenuItem != null : "fx:id=\"myBooksMenuItem\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert searchABookMenuItem != null : "fx:id=\"searchABookMenuItem\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert FullnameText != null : "fx:id=\"FullnameText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert fullNameLabel != null : "fx:id=\"fullNameLabel\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert nameLabel1 != null : "fx:id=\"nameLabel1\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert IDLabel != null : "fx:id=\"IDLabel\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert birthDateText != null : "fx:id=\"birthDateText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert birthDateLabel != null : "fx:id=\"birthDateLabel\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert facultyText != null : "fx:id=\"facultyText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert facultyLabel != null : "fx:id=\"facultyLabel\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert statusText != null : "fx:id=\"statusText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert viewCurrentlyRentedBooksButton != null : "fx:id=\"viewCurrentlyRentedBooksButton\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert phoneNumberText != null : "fx:id=\"phoneNumberText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert newPasswordText != null : "fx:id=\"newPasswordText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert NewPasswordTextField != null : "fx:id=\"NewPasswordTextField\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert veryfyPasswordText != null : "fx:id=\"veryfyPasswordText\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert VerifyPassordTextField != null : "fx:id=\"VerifyPassordTextField\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";
        assert SaveChangesButton != null : "fx:id=\"SaveChangesButton\" was not injected: check your FXML file 'P_fxml_Profile.fxml'.";

    }

}
