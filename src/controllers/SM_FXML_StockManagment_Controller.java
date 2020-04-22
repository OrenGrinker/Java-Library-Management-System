package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.control.TableView;

/**
 * Stock Managment Controller.
 * @author OBL_Team13
 */
public class SM_FXML_StockManagment_Controller implements Initializable{

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar1;

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
    private TextField bookNameTextField;

    @FXML
    private Button refreshButton;

    @FXML
    private Button viewReportButton;

    @FXML
    private TableView<Book> tableTableView;

    @FXML
    private TableColumn<Book, String> serialNumberTableColumn;

    @FXML
    private TableColumn<Book, String> bookNameTableColumn;

    @FXML
    private TableColumn<Book, String> authorTableColumn;

    @FXML
    private TableColumn<Book, String> placeOnShelfTableColumn;

    @FXML
    private TableColumn<Book, String> editionTableColumn;

    @FXML
    private Label totalBooksLabel;

    @FXML
    private Button removeSelectedButton;

    @FXML
    private Button editSelectedButton;

    @FXML
    private Button addANewBookButton;

    private ArrayList<Book> loadedBooks;

    private ObservableList<Book> observableList;

    /**
     * Initialize the books in the table of the stock managment.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			System.out.println("In initialize -> " + ManyBooks.getAllStaticBooks());

			serialNumberTableColumn.setCellValueFactory(	new PropertyValueFactory<>("Serial"));
	    	bookNameTableColumn.setCellValueFactory(		new PropertyValueFactory<>("Name"));
	    	authorTableColumn.setCellValueFactory(			new PropertyValueFactory<>("Author"));
	    	placeOnShelfTableColumn.setCellValueFactory(	new PropertyValueFactory<>("PlaceOnShelf"));
	    	editionTableColumn.setCellValueFactory(			new PropertyValueFactory<>("Generation"));

	    	loadedBooks = new ArrayList<Book>();
	    	loadedBooks = ManyBooks.getAllStaticBooks();
	    	observableList = FXCollections.observableArrayList();

	    	System.out.println("observableList: " + observableList);

	    	for(Book book : loadedBooks) {
	    		System.out.println("Adding book: " + book);
	    		observableList.add(book);
	    	}

	    	System.out.println("Full list is: " + loadedBooks);

	    	tableTableView.setItems(observableList);
	    	tableTableView.getColumns().addAll(serialNumberTableColumn, bookNameTableColumn, authorTableColumn, placeOnShelfTableColumn, editionTableColumn);
	    	ManyBooks.isLoaded = false;

	    	totalBooksLabel.setText("Total Books: " + loadedBooks.size());

	    	viewReportButton.setDisable(Employee.getRoll().toLowerCase().equals("manager") == false);

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
    /**
     * Press button to move to view report.
     */
    @FXML
    void viewReportButtonClicked(ActionEvent event) {
    	Router.ReportManager();
    }

    @FXML
    void bookNameTextFieldEnterClicked(ActionEvent event) {
    	//NOTHING GOES HERE (LEAVE BLANK) (DONT DELETE)
    }
    /**
     * Press button to refresh the list.
     */
    @FXML
    void refreshButtonClicked(ActionEvent event) {
    	System.out.println("in bookNameTextFieldTextChanged");

    	ObservableList<Book> currList = observableList;
    	ObservableList<Book> newList = FXCollections.observableArrayList();

    	for(Book book : currList)
    		if(book.getName().toLowerCase().contains(bookNameTextField.getText().toLowerCase()))
    			newList.add(book);
    	tableTableView.setItems(newList);
    	totalBooksLabel.setText("Total Books: " + newList.size());
    }

    /**
     * Press button to remove the selected book.
     */
    @FXML
    void removeSelectedButtonClicked(ActionEvent event) {
    	Client.removeABook(tableTableView.getSelectionModel().getSelectedItem()); //argument is of type Book
    }
    /**
     * Press button to edit the selected book.
     */
    @FXML
    void editSelectedButtonClicked(ActionEvent event) {
    	ManyBooks.lookForThisBookBySerial = tableTableView.getSelectionModel().getSelectedItem().getSerial();
    	Router.EditABook();
    }
    /**
     * Press button to add a new book to the stock managment.
     */
    @FXML
    void addANewBookButtonClicked(ActionEvent event) {
    	Router.AddABook();
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
    /**
     * Press on the menu button to view student profile.
     */
    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewStudentProfile();
    }

    @FXML
    void initialize() {
        assert menuBar1 != null : "fx:id=\"menuBar1\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert bookNameTextField != null : "fx:id=\"bookNameTextField\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert viewReportButton != null : "fx:id=\"viewReportButton\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert tableTableView != null : "fx:id=\"tableTableView\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert serialNumberTableColumn != null : "fx:id=\"serialNumberTableColumn\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert bookNameTableColumn != null : "fx:id=\"bookNameTableColumn\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert authorTableColumn != null : "fx:id=\"authorTableColumn\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert placeOnShelfTableColumn != null : "fx:id=\"placeOnShelfTableColumn\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert editionTableColumn != null : "fx:id=\"editionTableColumn\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert totalBooksLabel != null : "fx:id=\"totalBooksLabel\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert removeSelectedButton != null : "fx:id=\"removeSelectedButton\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert editSelectedButton != null : "fx:id=\"editSelectedButton\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert addANewBookButton != null : "fx:id=\"addANewBookButton\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
    }
}
