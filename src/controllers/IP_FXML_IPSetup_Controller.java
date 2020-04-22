
package controllers;

import application.*;
import logics.*;
import fxml.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * IPSetup Controller.
 * @author OBL_Team13
 */
public class IP_FXML_IPSetup_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Button testConnectionButton;

    @FXML
    private Button applyAndCloseButton;

    /**
     * Press to secure connection between the client and the server.
     */
    @FXML
    void applyAndCloseClicked(ActionEvent event) {
    	Router.Login();
    }
    /**
     * Press to test the connection.
     */
    @FXML
    void testConnectionClicked(ActionEvent event) {    	
    	testConnection();
    }
    
    @FXML
    void hostEnterPressed(ActionEvent event) {
    	testConnection();
    }

    @FXML
    void portEnterPressed(ActionEvent event) {
    	testConnection();
    }
    /**
     * Check the values of the connection.
     */
    private void testConnection() {
    	if(StringUtils.isStrictlyNumeric(portTextField.getText()) == false){
    		AlertBox.display("Connection Test", "Please make sure that your port is numeric only!", "okay, sorry");
    		return;
    	}
    	
    	if(IP_IPSetup.TestConnection(ipTextField.getText(), portTextField.getText()))	//0=success
    		applyAndCloseButton.setDisable(false);
    }
    
    @FXML
    void initialize() {
        assert ipTextField != null : "fx:id=\"ipTextField\" was not injected: check your FXML file 'IPSetUp.fxml'.";
        assert portTextField != null : "fx:id=\"portTextField\" was not injected: check your FXML file 'IPSetUp.fxml'.";
        assert testConnectionButton != null : "fx:id=\"testConnectionButton\" was not injected: check your FXML file 'IPSetUp.fxml'.";
        assert applyAndCloseButton != null : "fx:id=\"applyAndCloseButton\" was not injected: check your FXML file 'IPSetUp.fxml'.";

    }
}
