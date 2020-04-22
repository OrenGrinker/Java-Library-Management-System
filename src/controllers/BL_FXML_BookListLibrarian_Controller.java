
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Book List Librarian Controller.
 * @author OBL_Team13
 */
public class BL_FXML_BookListLibrarian_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem myProfileMenuItem;

    @FXML
    private MenuItem createNewProfileMenuItem;

    @FXML
    private MenuItem viewProfileMenuItem;

    @FXML
    private MenuItem searchMenuItem;

    @FXML
    private MenuItem returnABookMenuItem;

    @FXML
    private MenuItem stockManagmentMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TableView<?> ownedTableView;

    @FXML
    private TableColumn<?, ?> bookNameTableColumn;

    @FXML
    private TableColumn<?, ?> bookNameTableColumn1;

    @FXML
    private TableColumn<?, ?> rentedAtTableColumn;

    @FXML
    private TableColumn<?, ?> dueDateTableColumn;

    @FXML
    private TableView<?> orderedTableView;

    @FXML
    private TableColumn<?, ?> orderedListTableColumn;

    @FXML
    private Label totalBooksLabel;

    @FXML
    private Button timeExtensionButton;

    @FXML
    private Button cancelBookOrderButton;
    
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }

    @FXML
    void cancelBookOrderButtonClicked(ActionEvent event) {
    	//CancelBookOrderFunction call will go here
    }
    /**
     * Press on the menu button to create new account.
     */
    @FXML
    void createNewProfileMenuItemClicked(ActionEvent event) {
    	Router.CreateNewAccount();
    }
    /**
     * Press on the menu button to see my profile (librarian).
     */
    @FXML
    void myProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the menu button to return a book.
     */
    @FXML
    void returnABookMenuItemCLicked(ActionEvent event) {
    	Router.ReturnABook();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void searchMenuItemClicked(ActionEvent event) {
    	Router.SearchABookLibrarian();
    }

    @FXML
    void stockManagmentMenuItemClicked(ActionEvent event) {
    	//Router.StockManagment();
    }
    /**
     * Press on the button to extend rent time for selected book.
     */
    @FXML
    void timeExtensionButtonClicked(ActionEvent event) {
    	Router.ExtendTime();
    }

    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {
    	//Router.ViewStudentProfile();
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert createNewProfileMenuItem != null : "fx:id=\"createNewProfileMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert searchMenuItem != null : "fx:id=\"searchMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert ownedTableView != null : "fx:id=\"ownedTableView\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert bookNameTableColumn != null : "fx:id=\"bookNameTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert bookNameTableColumn1 != null : "fx:id=\"bookNameTableColumn1\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert rentedAtTableColumn != null : "fx:id=\"rentedAtTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert dueDateTableColumn != null : "fx:id=\"dueDateTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert orderedTableView != null : "fx:id=\"orderedTableView\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert orderedListTableColumn != null : "fx:id=\"orderedListTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert totalBooksLabel != null : "fx:id=\"totalBooksLabel\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert timeExtensionButton != null : "fx:id=\"timeExtensionButton\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";
        assert cancelBookOrderButton != null : "fx:id=\"cancelBookOrderButton\" was not injected: check your FXML file 'BL_fxml_BookListLibrarian.fxml'.";

    }
}
