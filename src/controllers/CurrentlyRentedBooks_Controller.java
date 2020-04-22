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


 public class CurrentlyRentedBooks_Controller implements Initializable {


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

   // @FXML
   // private TableColumn<Rent,  String> RealReturnDateTableColumn;

   // @FXML
   // private TableView<?> orderedTableView;

    //@FXML
    //private TableColumn<?, ?> orderedListTableColumn;

  //  @FXML
  //  private Label totalBooksLabel;

    @FXML
    private Button ExtensionRequestButtton;

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
    	//dueDateTableColumn.setCellValueFactory(new PropertyValueFactory<Rent,String>("DateOfRealReturn"));

    	final ObservableList<Rent> data=FXCollections.observableArrayList();

    	for(Rent order : MyBooks) {
    		data.add(order);
    	}
    	ownedTableView.setItems((ObservableList<Rent>) data);
    	ManyRents.isLoaded = false;
   	}
    /**
     * Press on the button to ask for extend time of renting.
     */
    /*
    @FXML
    void timeExtensionButtonClicked(ActionEvent event) {
    }
    */
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }


    @FXML
    void ExtensionRequestButttonClicked(ActionEvent event) {

    	if(!(User.getStatus()).equals("active"))
    	{
    		AlertBox.display("Renting Extension", "User is inactive","OK");
    		return;
    	}

///
    	Rent r;
    	try {

        	r=ownedTableView.getSelectionModel().getSelectedItem();
        	System.out.println(r);
        	if (r==null)
        		{AlertBox.display("Renting Extension", "Please select a row first!","OK");
        	return;
        		}
        	}

        	catch(Exception e) {
        		e.printStackTrace();
    			AlertBox.display("Renting Extension", "Please select a row first!","OK");
    			return;
    		}

///
    	Date originalReturnDate=r.getDateOfReturn();

    	Date date=r.getDateOfReturn();
    	LocalDate ldate=date.toLocalDate();
   	    ldate=ldate.plusDays(-7);
        date= java.sql.Date.valueOf(ldate);

    	Date today=java.sql.Date.valueOf(LocalDate.now());


        if(today.before(date)) {
        	AlertBox.display("Renting Extension", "Extension is enabled starting from a week before the original return date","OK");
    	     return;
        }

        if(today.before(date)) {
        	AlertBox.display("Renting Extension", "Extension is enabled starting from a week beforer the original return date","OK");
    	     return;
        }

       // check is popular
        if((r.getIsPopular()).equals("1")) {
        	AlertBox.display("Renting Extension", "Extension is disabled for popular book","OK");
    	     return;
        }

        date=r.getDateOfReturn();
        ldate=date.toLocalDate();
   	    ldate=ldate.plusDays(3);//extend in 3 days
        date= java.sql.Date.valueOf(ldate);


        Date ReturnDate=date;
        r.setDateOfReturn(ReturnDate);
		Client.UpdateReturnDate(r);
		//String MailAdress=

		String msg="Extension was approved:\n Student id: "+r.getID()+ "\n Book name: "+r.getName()+
				"\n Perivious return date: "+ originalReturnDate+ "\n New return date: " +r.getDateOfReturn();
		SendEmail.sendEmail("Oblsafranit@gmail.com", "Renting Extension",msg) ;
		AlertBox.display("Renting Extension", "Renting was extended successfully!","OK");
		Router.MyCurrentBook();

        //is popular*********
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
    	//Router.BookListStudent();
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
      //  assert orderedTableView != null : "fx:id=\"orderedTableView\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert orderedListTableColumn != null : "fx:id=\"orderedListTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert totalBooksLabel != null : "fx:id=\"totalBooksLabel\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert ExtensionRequestButtton != null : "fx:id=\"ExtensionRequestButtton\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert cancelBookOrderButton != null : "fx:id=\"cancelBookOrderButton\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";

    }
}
