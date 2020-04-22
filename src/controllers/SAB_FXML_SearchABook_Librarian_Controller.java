package controllers;

import java.util.ResourceBundle;

import application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logics.*;
import fxml.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Search A Book Librarian Controller.
 * @author OBL_Team13
 */
public class SAB_FXML_SearchABook_Librarian_Controller implements Initializable {

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
    private Label helloLabel;

    @FXML
    private Label searchForABookLabel;

    @FXML
    private RadioButton bookNameRadioButton;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private RadioButton authorNameRadioButton;

    @FXML
    private TextField authorNameTextField;

    @FXML
    private RadioButton bookTopicRadioButton;

    @FXML
    private TextField bookTopicTextField;

    @FXML
    private RadioButton freeTextRadioButton;

    @FXML
    private TextArea freeTextArea;

    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageVIew;

    private static boolean updated = false; //WILL BE USED TO SIGNAL UPDATE

    @Override
	public void initialize(URL location, ResourceBundle resources) {


	}
    /**
     * Selected book name option.
     */
    @FXML
    void bookNameMouseClicked(MouseEvent event) {
    	bookNameRadioButton.setSelected(true);
    	authorNameRadioButton.setSelected(false);
		bookTopicRadioButton.setSelected(false);
		freeTextRadioButton.setSelected(false);
    }
    /**
     * Selected author name option.
     */
    @FXML
    void authorNameMouseClicked(MouseEvent event) {
    	authorNameRadioButton.setSelected(true);
    	bookNameRadioButton.setSelected(false);
		bookTopicRadioButton.setSelected(false);
		freeTextRadioButton.setSelected(false);
    }
    /**
     * Selected book topic option.
     */
    @FXML
    void bookTopicMouseClicked(MouseEvent event) {
    	bookTopicRadioButton.setSelected(true);
    	authorNameRadioButton.setSelected(false);
		bookNameRadioButton.setSelected(false);
		freeTextRadioButton.setSelected(false);
    }
    /**
     * Selected free text option.
     */
    @FXML
    void freeTextMouseClicked(MouseEvent event) {
    	freeTextRadioButton.setSelected(true);
    	authorNameRadioButton.setSelected(false);
		bookNameRadioButton.setSelected(false);
		bookTopicRadioButton.setSelected(false);
    }
    /**
     * Set status.
     */
    public static void setUpdated(boolean status) {
		updated = status;
		if(status == true) {
			//updateResults();
			System.out.println("Updated!");	//DELETE ME LATER
		}
		else
			System.out.println("Not Updated :\\");
	}

	public static boolean getUpdated() {
		return updated;
	}
	/**
     * Selected author name option and radio button is enable.
     */
    @FXML
    void authorNameRadioButtonClicked(ActionEvent event) {
    	if(authorNameRadioButton.isSelected()) {
    		bookNameRadioButton.setSelected(false);
    		bookTopicRadioButton.setSelected(false);
    		freeTextRadioButton.setSelected(false);
    	}
    	else
    		authorNameRadioButton.setSelected(true);
    }
    /**
     * Selected book name option and radio button is enable.
     */
    @FXML
    void bookNameRadioButtonClicked(ActionEvent event) {
    	if(bookNameRadioButton.isSelected()) {
    		authorNameRadioButton.setSelected(false);
    		bookTopicRadioButton.setSelected(false);
    		freeTextRadioButton.setSelected(false);
    	}
    	else
    		bookNameRadioButton.setSelected(true);
    }
    /**
     * Selected book topic option and radio button is enable.
     */
    @FXML
    void bookTopicRadioButtonClicked(ActionEvent event) {
    	if(bookTopicRadioButton.isSelected()) {
    		authorNameRadioButton.setSelected(false);
    		bookNameRadioButton.setSelected(false);
    		freeTextRadioButton.setSelected(false);
    	}
    	else
    		bookTopicRadioButton.setSelected(true);
    }
    /**
     * Selected free text option and radio button is enable.
     */
    @FXML
    void freeTextRadioButtonClicked(ActionEvent event) {
    	if(freeTextRadioButton.isSelected()) {
    		authorNameRadioButton.setSelected(false);
    		bookNameRadioButton.setSelected(false);
    		bookTopicRadioButton.setSelected(false);
    	}
    	else
    		freeTextRadioButton.setSelected(true);
    }
    /**
     * Press button to clear the text on the all text fields.
     */
    @FXML
    void clearButtonClicked(ActionEvent event) {
    	bookNameTextField.setText("");
    	authorNameTextField.setText("");
    	bookTopicTextField.setText("");
    	freeTextArea.setText("");
    }
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void AboutMenuItemClicked(ActionEvent event) {
    	Router.About();
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
     * Press button to search for a book by name, author name, topic or free text.
     */
    void enterPressedHandler() {
    	if(bookNameRadioButton.isSelected()) {
    		Client.SearchByBookName_L(bookNameTextField.getText().toString());
    		return;
    	}
    	if(authorNameRadioButton.isSelected()) {
    		Client.SearchByAuthorName_L(authorNameTextField.getText());
    		return;
    	}
    	if(bookTopicRadioButton.isSelected()) {
    		Client.SearchByBookTopic_L(bookTopicTextField.getText());
    		return;
    	}
    	if(freeTextRadioButton.isSelected()) {
    		Client.SearchByFreeText_L(freeTextArea.getText());
    		return;
    	}
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
    	enterPressedHandler();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void bookNameTextFieldEnterPressed(ActionEvent event) {
    	enterPressedHandler();
    }

    @FXML
    void authorNameTextFieldEnterPressed(ActionEvent event) {
    	enterPressedHandler();
    }

    @FXML
    void bookTopicTextFieldEnterPressed(ActionEvent event) {
    	enterPressedHandler();
    }

    @FXML
    void SearchABookMenuItemClicked(ActionEvent event) {
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
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert helloLabel != null : "fx:id=\"helloLabel\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert searchForABookLabel != null : "fx:id=\"searchForABookLabel\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert bookNameRadioButton != null : "fx:id=\"bookNameRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert bookNameTextField != null : "fx:id=\"bookNameTextField\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert authorNameRadioButton != null : "fx:id=\"authorNameRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert authorNameTextField != null : "fx:id=\"authorNameTextField\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert bookTopicRadioButton != null : "fx:id=\"bookTopicRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert bookTopicTextField != null : "fx:id=\"bookTopicTextField\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert freeTextRadioButton != null : "fx:id=\"freeTextRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert freeTextArea != null : "fx:id=\"freeTextArea\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";
        assert imageVIew != null : "fx:id=\"imageVIew\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Librarian.fxml'.";

    }


}
