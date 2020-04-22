package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Book;
import application.ManyBooks;
import application.PdfSlave;
import application.Router;
import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Search Result Librarian Controller.
 * @author OBL_Team13
 */
public class SR_FXML_SearchResultLibrarian_Controller implements Initializable{

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
    private MenuItem signOutMenuItem;

    @FXML
    private MenuItem searchMenuItem;

    @FXML
    private MenuItem returnABookMenuItem;

    @FXML
    private MenuItem stockManagmentMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TableView<Book> tableViewID;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private Label copiesAvailabeLabel;

    @FXML
    private Label copiesAvailabeID;

    @FXML
    private Label shelfLabel;

    @FXML
    private Label shelfLabelID;

    @FXML
    private Button rentRequestButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button viewTableOfContentsButton;

    private ArrayList<Book> loadedBooks;

    Book book;
    /**
     * Initialize the books in the table of the search result by librarian.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

			if(ManyBooks.isLoaded)
			{
				try {

					tableViewID.getItems().clear();

					System.out.println("In initialize -> " + ManyBooks.getAllStaticBooks());

					bookNameColumn.setCellValueFactory(		new PropertyValueFactory<>("Name"));
					authorColumn.setCellValueFactory(		new PropertyValueFactory<>("Author"));
					categoryColumn.setCellValueFactory(		new PropertyValueFactory<>("Category"));

			    	loadedBooks = ManyBooks.getAllStaticBooks();
			    	ObservableList<Book> observableList = FXCollections.observableArrayList();

			    	for(Book book : loadedBooks) {
			    		observableList.add(book);
			    	}

			    	tableViewID.setItems(observableList);
			    	tableViewID.getColumns().addAll(bookNameColumn, authorColumn, categoryColumn);
			    	ManyBooks.isLoaded = false;

				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}


	}
    /**
     * Press on specific row and get the information about this book.
     */
    @FXML
    void mouseClickedOnRow(MouseEvent event) {

    	book = tableViewID.getSelectionModel().getSelectedItem();

    	copiesAvailabeID.setText(book.getNumberOfAvailabeCopies());
    	shelfLabelID.setText(book.getPlaceOnShelf());

    }
    /**
     * Press on the rent request button to rent a book.
     */
    @FXML
    void rentRequestButtonClicked(ActionEvent event) {
    	ManyBooks.lookForThisBookBySerial = tableViewID.getSelectionModel().getSelectedItem().getSerial();
    	User.lookForThisUserByID = "";
    	Router.RentByLibrarian();
    }
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void viewTableOfContentsButtonClicked(ActionEvent event) {
    	book = tableViewID.getSelectionModel().getSelectedItem();
    	String filePath = PdfSlave.writeFileOnPC(book.getSerial(), book.getTableOfContentAsPdf());
    	PdfSlave.openFile(filePath);
    }

    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }
    /**
     * Press on the close button and move to my profile.
     */
    @FXML
    void closeButtonClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the menu button to create new account.
     */
    @FXML
    void createNewProfileMenuItemClicked(ActionEvent event) {
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
     * Press on the menu button to search a book.
     */
    @FXML
    void searchMenuItemClicked(ActionEvent event) {
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
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert createNewProfileMenuItem != null : "fx:id=\"createNewProfileMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert searchMenuItem != null : "fx:id=\"searchMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert tableViewID != null : "fx:id=\"tableViewID\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert bookNameColumn != null : "fx:id=\"bookNameColumn\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert authorColumn != null : "fx:id=\"authorColumn\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert categoryColumn != null : "fx:id=\"categoryColumn\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert copiesAvailabeLabel != null : "fx:id=\"copiesAvailabeLabel\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert copiesAvailabeID != null : "fx:id=\"copiesAvailabeID\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert shelfLabel != null : "fx:id=\"shelfLabel\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert shelfLabelID != null : "fx:id=\"shelfLabelID\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert rentRequestButton != null : "fx:id=\"rentRequestButton\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
        assert viewTableOfContentsButton != null : "fx:id=\"viewTableOfContentsButton\" was not injected: check your FXML file 'SR_fxml_SearchResultLibrarian.fxml'.";
    }
}
