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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


 public class CRBL_CurrentlyRentedByLibrarian_Controller implements Initializable {

	 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

//    @FXML
  //  private MenuBar menuBar1;

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
    private TableView<Rent> ownedTableView;

    @FXML
    private TableColumn<Rent, String> bookNameTableColumn;

    @FXML
    private TableColumn<Rent,String> SerialNumberColumn;

    @FXML
    private TableColumn<Rent,  Date> rentedAtTableColumn;

    @FXML
    private TableColumn<Rent,  Date> dueDateTableColumn;
    

    @FXML
    private Button ExtensionRequestButtton;
    
    @FXML
    private Button approveButton;
    
    @FXML
    private DatePicker newDateDatePicker;
    
    @FXML
    private Label IDLabel;
    

   // @FXML
   // private Button cancelBookOrderButton; 
    
    
    private ArrayList<Rent> MyBooks;
    
    @Override
   	public void initialize(URL arg0, ResourceBundle arg1) {
    	//ownedTableView.getColumns().add(bookNameTableColumn);
    	//Book new=new Book();
    	//book.setName("name here");
    	IDLabel.setText(User.getID());   
    	MyBooks=ManyRents.getAllStaticRents();
    	
    	bookNameTableColumn.setCellValueFactory(new PropertyValueFactory<Rent,String>("Name"));
    	SerialNumberColumn.setCellValueFactory(new PropertyValueFactory<Rent,String>("Serial"));
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
    
    @FXML
    void approveButtonClicked(ActionEvent event) {
    	//Router.ExtendTimeRequest();
    	//boolean f=true;
    	java.sql.Date ReturnDate=null;
    	try {
    		
    		 ReturnDate = DateConvertor.localDateToSqlDate(newDateDatePicker.getValue());}
    	catch(Exception e) {
    		//e.printStackTrace();
    		System.out.println("check");
			AlertBox.display("Renting Extension", "Please pick a new date!","OK");
			//f=false;
			return;
		}
    	//if(!false)
    	
    	Rent r;
    	try {
    	r=ownedTableView.getSelectionModel().getSelectedItem();
    	
    	Date originalReturnDate=r.getDateOfReturn();
    	
        
    	if(ReturnDate!=null && ReturnDate.after(r.getDateOfReturn())) 
    	{
    		Date today=java.sql.Date.valueOf(LocalDate.now());//get today
    		r.setDateOfReturn(ReturnDate);
    		
    		//Router.About();
    		Client.UpdateReturnDate(r);
    		AlertBox.display("Renting Extension", "Renting was extended successfully!","OK");
    		//alert operated by
    		String msg="Extension was made:\n\n By librarian: "+ Employee.getFirstName()
    		        +"\n Operation Date: "+today
    				+ "\n\n Student id: "+r.getID()+ "\n Book name: "+r.getName()+
    				"\n Perivious return date: "+ originalReturnDate+ "\n New return date: " +r.getDateOfReturn();
    		SendEmail.sendEmail("Oblsafranit@gmail.com", "Renting Extension",msg) ;
    		
    		Router.CurrentlyRentedByLibrarian();
    	}
    	
    	else AlertBox.display("Renting Extension", "Please select a pick a later date than the current Return date!","OK");
    	return;
    	     }
    	catch(Exception e) {
    		//e.printStackTrace();
			AlertBox.display("Renting Extension", "Please select a row first!","OK");
			return;
		}
    	
    	
    		
    }
    
    /////////
    @FXML
    void AboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }

    @FXML
    void SearchABookMenuItemClicked(ActionEvent event) {
    	Router.SearchABookLibrarian();
    }

    @FXML
    void createNewMenuItemClicked(ActionEvent event) {
    	Router.CreateNewAccount();
    }

    @FXML
    void myProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewProfile();
    }

    @FXML
    void returnABookMenuItemClicked(ActionEvent event) {
    	Router.ReturnABook();
    }

    @FXML
    void signOutMenuItemClicked(ActionEvent event) {
    	Router.Login();
    }

    @FXML
    void stockManagmentMenuItemClicked(ActionEvent event) {
    	Router.StockManagment();
    }

    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewStudentProfile();
    }
    ///
    
    @FXML
    void initialize() {
    	//assert menuBar != null : "fx:id=\"menuBar1\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
       // assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'SM_fxml_StockManagment.fxml'.";
    	
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
       // assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        //assert myBooksMenuItem != null : "fx:id=\"myBooksMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        //assert searchABookMenuItem != null : "fx:id=\"searchABookMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert ownedTableView != null : "fx:id=\"ownedTableView\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert bookNameTableColumn != null : "fx:id=\"bookNameTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert SerialNumberColumn != null : "fx:id=\"SerialNumberColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert rentedAtTableColumn != null : "fx:id=\"rentedAtTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert dueDateTableColumn != null : "fx:id=\"dueDateTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert orderedTableView != null : "fx:id=\"orderedTableView\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert orderedListTableColumn != null : "fx:id=\"orderedListTableColumn\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
      //  assert totalBooksLabel != null : "fx:id=\"totalBooksLabel\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert IDLabel != null : "fx:id=\"IDLabel\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        // assert ExtensionRequestButtton != null : "fx:id=\"ExtensionRequestButtton\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert approveButton != null : "fx:id=\"approveButton\" was not injected: check your FXML file 'BL_fxml_BookListStudent.fxml'.";
        assert newDateDatePicker != null : "fx:id=\"newDateDatePicker\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
    }
}


