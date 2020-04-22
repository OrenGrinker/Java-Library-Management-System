package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import application.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import logics.AAB_AddABook;

/**
 * Add A Book Controller.
 * @author OBL_Team13
 */
public class AAB_FXML_AddABook_Controller implements Initializable{

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
    private TextField SerialNumberTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField AuthoTextField;

    @FXML
    private TextField ShelfTextField;

    @FXML
    private TextField CopiesNumberTextField;

    @FXML
    private TextField EditionTextField;

    @FXML
    private TextField pdfPathTextField;

    @FXML
    private DatePicker PrinitngDateDatePicker;

    @FXML
    private DatePicker AquisitionDateTextField;

    @FXML
    private Button addPDFButton;

    @FXML
    private TextField DescriptionTextFiel;

    @FXML
    private MenuButton chooseMenuButton;

    @FXML
    private MenuItem softwareMenuItem;

    @FXML
    private MenuItem InforamationSystemsMenuItem;

    @FXML
    private MenuItem industryNManagmenMenuItem;

    @FXML
    private MenuItem eclectricityNEkctronicsMenuItem;

    @FXML
    private MenuItem AppliedMathematicsMenuItem;

    @FXML
    private MenuItem BiotechnologyMenuItem;

    @FXML
    private MenuItem OpticsMenuItem;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    String TableOfContentsURL;

    byte[] dataArray = null;
    /**
     * Initialize default value of faculty as "software".
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	chooseMenuButton.setText("Software");
    	return;
	}
    /**
     * Press back to the list of exists books in stock managment.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
    	Router.StockManagment();
    }
    /**
     * Press on the menu button to view student profile.
     */
    @FXML
    void viewProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewStudentProfile();
    }
    /**
     * Press on the menu button to move to stock managment.
     */
    @FXML
    void stockManagmentMenuItemClicked(ActionEvent event) {
    	Router.StockManagment();
    }
    /**
     * Press on the menu button to sign out from the application.
     */
    @FXML
    void signOutMenuItemClicked(ActionEvent event) {
    	Router.Login();
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
     * Press on the menu button to create new account.
     */
    @FXML
    void createNewMenuItemClicked(ActionEvent event) {
    	Router.CreateNewAccount();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void SearchABookMenuItemClicked(ActionEvent event) {
    	Router.SearchABookLibrarian();
    }
    /**
     * Press on the menu button to read about the application.
     */
    @FXML
    void AboutMenuItemClicked(ActionEvent event) {
    	Router.About();
    }
    /**
     * When save button clicked it checks if all the fields are not empty and alert with popup if there are problems, if all ok the new book saves in the database.
     */
    @FXML
    void saveButtonClicked(ActionEvent event) {
    	if (SerialNumberTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter a serial number", "Okay"));
    		return;
    	}

    	if (NameTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter a name", "Okay"));
    		return;
    	}

    	if (AuthoTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter an arthur", "Okay"));
    		return;
    	}

    	if (ShelfTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter a shelf placement", "Okay"));
    		return;
    	}

    	if (CopiesNumberTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter number of copies", "Okay"));
    		return;
    	}

    	if (EditionTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter an edition", "Okay"));
    		return;
    	}

    	if (PrinitngDateDatePicker.getValue() == null) {
    		Platform.runLater(new AlertBox(1, "Error", "Must select the printing date", "Okay"));
    		return;
    	}

    	if (AquisitionDateTextField.getValue() == null) {
    		Platform.runLater(new AlertBox(1, "Error", "Must select the date of purchase", "Okay"));
    		return;
    	}

    	if (DescriptionTextFiel.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must enter a description", "Okay"));
    		return;
    	}


    	if (pdfPathTextField.getText().isEmpty()) {
    		Platform.runLater(new AlertBox(1, "Error", "Must select a pdf file", "Okay"));
    		return;
    	}

    	System.out.println("AAB_FXML -> Shelf is: " + ShelfTextField.getText());

    	java.sql.Date printingDate		= DateConvertor.localDateToSqlDate(PrinitngDateDatePicker.getValue());
    	java.sql.Date aquisitionDate	= DateConvertor.localDateToSqlDate(AquisitionDateTextField.getValue());

    	Client.CreateNewBook(	SerialNumberTextField.getText(),
    							NameTextField.getText(),
    							AuthoTextField.getText(),
    							EditionTextField.getText(),
    							printingDate,//PrinitngDateDatePicker.getValue(),	//PrinitngDateDatePicker.getValue()
    							chooseMenuButton.getText(),
    							DescriptionTextFiel.getText(),
    							aquisitionDate,//AquisitionDateTextField.getValue(),	//AquisitionDateTextField.getValue()
    							ShelfTextField.getText(),
    							dataArray,//TableOfContents.getText(),
    							CopiesNumberTextField.getText());
    }

    @FXML
    void addPDFButtonClicked(ActionEvent event) {
    	TableOfContentsURL = PdfSlave.chooseFile();
    	dataArray = PdfSlave.getDataAsByteArray(TableOfContentsURL);
    	pdfPathTextField.setText(TableOfContentsURL);
    }
    
    /**
     * Choose "applied mathematics" faculty from the menu for the book subject.
     */
    @FXML
    void AppliedMathematicsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Applied Mathematics");
    }
    /**
     * Choose "biotechnology" faculty from the menu for the book subject.
     */
    @FXML
    void BiotechnologyMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Biotechnology");
    }
    /**
     * Choose "inforamation systems" faculty from the menu for the book subject.
     */
    @FXML
    void InforamationSystemsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Inforamation Systems");
    }
    /**
     * Choose "optics" faculty from the menu for the book subject.
     */
    @FXML
    void OpticsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Optics");
    }
    /**
     * Choose "electronics" faculty from the menu for the book subject.
     */
    @FXML
    void eclectricityNEkctronicsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Electronics");
    }
    /**
     * Choose "industy & managment" faculty from the menu for the book subject.
     */
    @FXML
    void industryNmanagmentMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Industry & managment");
    }
    /**
     * Choose "software" faculty from the menu for the book subject.
     */
    @FXML
    void softwareMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Software");
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert SerialNumberTextField != null : "fx:id=\"SerialNumberTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert NameTextField != null : "fx:id=\"NameTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert AuthoTextField != null : "fx:id=\"AuthoTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert ShelfTextField != null : "fx:id=\"ShelfTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert CopiesNumberTextField != null : "fx:id=\"CopiesNumberTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert EditionTextField != null : "fx:id=\"EditionTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert PrinitngDateDatePicker != null : "fx:id=\"PrinitngDateDatePicker\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert AquisitionDateTextField != null : "fx:id=\"AquisitionDateTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert DescriptionTextFiel != null : "fx:id=\"DescriptionTextFiel\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert pdfPathTextField != null : "fx:id=\"pdfPathTextField\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert chooseMenuButton != null : "fx:id=\"chooseMenuButton\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert softwareMenuItem != null : "fx:id=\"softwareMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert InforamationSystemsMenuItem != null : "fx:id=\"InforamationSystemsMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert industryNManagmenMenuItem != null : "fx:id=\"industryNManagmenMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert eclectricityNEkctronicsMenuItem != null : "fx:id=\"eclectricityNEkctronicsMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert AppliedMathematicsMenuItem != null : "fx:id=\"AppliedMathematicsMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert BiotechnologyMenuItem != null : "fx:id=\"BiotechnologyMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert OpticsMenuItem != null : "fx:id=\"OpticsMenuItem\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert addPDFButton != null : "fx:id=\"addPDFButton\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'AAB_fxml_AddABook.fxml'.";
    }
}
