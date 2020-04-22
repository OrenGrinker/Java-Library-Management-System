package controllers;

import application.*;
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
import javafx.scene.input.MouseEvent;

/**
 * Search A Book Student Controller.
 * @author OBL_Team13
 */
public class SAB_FXML_SearchABook_Student_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem MyProfileMenuItem;

    @FXML
    private MenuItem SignOutMenuItem;

    @FXML
    private MenuItem MyBooksMenuItem;

    @FXML
    private MenuItem SearchABookMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

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
	public void initialize(URL arg0, ResourceBundle arg1) {


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

	public static boolean getUpdated() {
		return updated;
	}
	/**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
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
     * Press on the my books button to see the books that the user renting.
     */
    @FXML
    void myBooksMenuItemClicked(ActionEvent event) {
    	Router.MyCurrentBook();
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
		 * Press button to search for a book by name, author name, topic or free text.
		 */
    void enterPressedHandler() {
    	if(bookNameRadioButton.isSelected()) {
    		Client.SearchByBookName_S(bookNameTextField.getText().toString());
    		return;
    	}
    	if(authorNameRadioButton.isSelected()) {
    		Client.SearchByAuthorName_S(authorNameTextField.getText());
    		return;
    	}
    	if(bookTopicRadioButton.isSelected()) {
    		Client.SearchByBookTopic_S(bookTopicTextField.getText());
    		return;
    	}
    	if(freeTextRadioButton.isSelected()) {
    		Client.SearchByFreeText_S(freeTextArea.getText());
    		return;
    	}
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
    	enterPressedHandler();
    }
    /**
     * Press on the menu button to sign out from the application.
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
    void signOutMenuItemClicked(ActionEvent event) {
    	Router.Login();
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert MyProfileMenuItem != null : "fx:id=\"MyProfileMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert SignOutMenuItem != null : "fx:id=\"SignOutMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert MyBooksMenuItem != null : "fx:id=\"MyBooksMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert helloLabel != null : "fx:id=\"helloLabel\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert searchForABookLabel != null : "fx:id=\"searchForABookLabel\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert bookNameRadioButton != null : "fx:id=\"bookNameRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert bookNameTextField != null : "fx:id=\"bookNameTextField\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert authorNameRadioButton != null : "fx:id=\"authorNameRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert authorNameTextField != null : "fx:id=\"authorNameTextField\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert bookTopicRadioButton != null : "fx:id=\"bookTopicRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert bookTopicTextField != null : "fx:id=\"bookTopicTextField\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert freeTextRadioButton != null : "fx:id=\"freeTextRadioButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert freeTextArea != null : "fx:id=\"freeTextArea\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
        assert imageVIew != null : "fx:id=\"imageVIew\" was not injected: check your FXML file 'SAB_fxml_SearchABook_Student.fxml'.";
    }

}
