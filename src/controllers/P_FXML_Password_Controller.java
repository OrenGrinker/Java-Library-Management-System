package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import runUs.EchoServer;

public class P_FXML_Password_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField passTextField;

    @FXML
    private Button connectButton;

    @FXML
    void connectButtonClicked(ActionEvent event) {
    	//new EchoServer(5555, passTextField.getText());
    	EchoServer.dbPassword = passTextField.getText();
    	EchoServer sv = new EchoServer(5555);
    	try
        {
          sv.listen(); //Start listening for connections
        }
        catch (Exception ex)
        {
          System.out.println("ERROR - Could not listen for clients!");
        }
    }

    @FXML
    void passTextFieldEnterPressed(ActionEvent event) {
    	//new EchoServer(5555, passTextField.getText());
    	EchoServer.dbPassword = passTextField.getText();
    	EchoServer sv = new EchoServer(5555);
    	try
        {
          sv.listen(); //Start listening for connections
        }
        catch (Exception ex)
        {
          System.out.println("ERROR - Could not listen for clients!");
        }
    }

    @FXML
    void initialize() {
        assert passTextField != null : "fx:id=\"passTextField\" was not injected: check your FXML file 'P_fxml_Password.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'P_fxml_Password.fxml'.";

    }
}
