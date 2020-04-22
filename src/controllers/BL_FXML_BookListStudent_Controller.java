
package controllers;
import application.*;
import runUs.EchoServer;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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

/**
 * Book List Student Controller.
 * @author OBL_Team13
 */
public class BL_FXML_BookListStudent_Controller implements Initializable {

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
    private TableView<Rent> ownedTableView;

    @FXML
    private TableColumn<Rent, String> bookNameTableColumn;

    @FXML
    private TableColumn<Rent,String> bookNameTableColumn1;

    @FXML
    private TableColumn<Rent,  Date> rentedAtTableColumn;

    @FXML
    private TableColumn<Rent,  Date> dueDateTableColumn;
    
    @FXML
    private TableColumn<Rent,  Date> RealDateReturnTableColumn;

    @FXML
    private TableView<?> orderedTableView;

    //@FXML
    //private TableColumn<?, ?> orderedListTableColumn;

  //  @FXML
  //  private Label totalBooksLabel;

    @FXML
    private Button timeExtensionButton;

   // @FXML
   // private Button cancelBookOrderButton; 
    
    
    private ArrayList<Rent> MyBooks;
    /**
     * Initialize the table of the book list (set columns and books).
     */
    @Override
   	public void initialize(URL arg0, ResourceBundle arg1) {
    	//ownedTableView.getColumns().add(bookNameTableColumn);
    	//Book new=new Book();
    	//book.setName("name here");
    	
    	MyBooks=ManyRents.getAllStaticRents();
    	
    	bookNameTableColumn.setCellValueFactory(new PropertyValueFactory<Rent,String>("Name"));
    	bookNameTableColumn1.setCellValueFactory(new PropertyValueFactory<Rent,String>("Serial"));
    	rentedAtTableColumn.setCellValueFactory(new PropertyValueFactory<Rent,Date>("DateOfRent"));
    	dueDateTableColumn.setCellValueFactory(new PropertyValueFactory<Rent,Date>("DateOfReturn"));
    	RealDateReturnTableColumn.setCellValueFactory(new PropertyValueFactory<Rent,Date>("DateOfRealReturn"));
    	
    	final ObservableList<Rent> data=FXCollections.observableArrayList();
    	
    	for(Rent order : MyBooks) {
    		data.add(order);
    	}
    	ownedTableView.setItems((ObservableList<Rent>) data);
    	ManyRents.isLoaded = false;
   	}
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }

    @FXML
    void cancelBookOrderButtonClicked(ActionEvent event) {
    	//Cancel book order goes here
    }
    /**
     * Press on the menu button to view list of books that the student rented.
     */
    @FXML
    void myBooksMenuItemClicked(ActionEvent event) {
    	Router.BookListStudent();
    }
    /**
     * Press on the menu button to view my profile (student).
     */
    @FXML
    void myProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewProfile();
    }
    /**
     * Press on the menu button to search for a book.
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

    @FXML
    void timeExtensionButtonClicked(ActionEvent event) {
    	//Router.ExtendTimeRequest();
    }

    @FXML
    void initialize() { 
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert myBooksMenuItem != null : "fx:id=\"myBooksMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert searchABookMenuItem != null : "fx:id=\"searchABookMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert ownedTableView != null : "fx:id=\"ownedTableView\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert bookNameTableColumn != null : "fx:id=\"bookNameTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert bookNameTableColumn1 != null : "fx:id=\"bookNameTableColumn1\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert rentedAtTableColumn != null : "fx:id=\"rentedAtTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert dueDateTableColumn != null : "fx:id=\"dueDateTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert orderedTableView != null : "fx:id=\"orderedTableView\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert orderedListTableColumn != null : "fx:id=\"orderedListTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert totalBooksLabel != null : "fx:id=\"totalBooksLabel\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert timeExtensionButton != null : "fx:id=\"timeExtensionButton\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert cancelBookOrderButton != null : "fx:id=\"cancelBookOrderButton\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";

    }
}
