
package controllers;

import application.*;
import logics.*;
import fxml.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Login Controller.
 * @author OBL_Team13
 */
public class LI_FXML_Login_Controller {

	public static int updated = 0;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;
 
    @FXML
    private Button FindABook;
    
    /**
     * Press to login to the system, checks if username and password match.
     */
    @FXML
    void handleLoginButtonAction(ActionEvent event){
    	Client.AttemptLogin(username.getText(), password.getText());
    }
    /**
     * Press to search for a book in guest mode.
     */
    @FXML 
    void handleSearchABook(ActionEvent event) {
    	//Searching for a book from the login window will be made in a student's format
    	Router.SearchABookStudent();
    }
    /**
     * Press to enter the password in the login.
     */
    @FXML
    void passEnterPressed(ActionEvent event) {
    	Client.AttemptLogin(username.getText(), password.getText());
    }
    /**
     * Press to enter the id in the login.
     */
    @FXML
    void userIdEnterPressed(ActionEvent event) {
    	Client.AttemptLogin(username.getText(), password.getText());
    }
    
    @FXML
    void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'LI_fxml_Login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'LI_fxml_Login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LI_fxml_Login.fxml'.";
        assert FindABook != null : "fx:id=\"FindABook\" was not injected: check your FXML file 'LI_fxml_Login.fxml'.";

    }
}
