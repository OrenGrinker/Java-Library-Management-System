package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import application.Client;
import application.DateConvertor;
import application.Employee;
import application.ManyBooks;
import application.Router;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * View Student Profile Controller.
 * @author OBL_Team13
 */
public class VSP_FXML_ViewStudentProfile_Controller implements Initializable{

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
    private MenuItem searchMenuButton;

    @FXML
    private MenuItem returnMenuButton;

    @FXML
    private MenuItem stockManagmentMenuButton;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Label createAccountLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label birthdayLabel;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private Label facultyMenuButton;

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
    private Label getUpdatesToLabel;

    @FXML
    private CheckBox emailCheckBox;

    @FXML
    private CheckBox phoneCheckBox;

    @FXML
    private Label genderLabel;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private Label IDNumberLabel;

    @FXML
    private TextField IDNumberTextField;

    @FXML
    private ImageView IDIconImageView;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView passwordIconImageView;

    @FXML
    private Label accountStatusLabel;

    @FXML
    private MenuButton accountStatusManuButton;

    @FXML
    private MenuItem activeMenuItem;

    @FXML
    private MenuItem frozenMenuItem;

    @FXML
    private MenuItem lockedMenuItem;

    @FXML
    private Button viewCurrentlyRentedBooksButton;

    @FXML
    private Button rentANewBookButton;

    @FXML
    private Button createAccountNowButton;

    private String gender = "male";

    
    /**
     * Initialize the user details for user or employee.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	System.out.println("User.isLoaded: " + User.isLoaded);
    	
    	if(User.isLoaded == true) {
    		firstNameTextField.setText(User.getFirstName());
    		lastNameTextField.setText(User.getLastName());
    		emailTextField.setText(User.getEmail());
    		phoneNumberTextField.setText((User.getPhoneNumber() == null) ? "" : User.getPhoneNumber());
    		birthdayDatePicker.setValue(DateConvertor.sqlDateToLocalDate(User.getBirthdate()));
    		chooseMenuButton.setText(User.getFaculty());
    		emailCheckBox.selectedProperty().set(User.getGetUpdatesToEmail().equals("1"));
    		phoneCheckBox.selectedProperty().set(User.getGetUpdatesToPhone().equals("1"));
    		if(User.getGender().equals("male")) {
    			maleRadioButton.setSelected(true);
    			femaleRadioButton.setSelected(false);
    		}
    		else {
    			maleRadioButton.setSelected(false);
    			femaleRadioButton.setSelected(true);
    		}
    		IDNumberTextField.setText(User.getID());
    		passwordTextField.setText(User.getPass());           
    		accountStatusManuButton.setText(User.getStatus());
    		User.isLoaded = false;
    		
    		//If the employee is not a manager
    		if(Employee.getRoll().toLowerCase().equals("manager") == false) {
    			firstNameTextField.setDisable(true);
    			lastNameTextField.setDisable(true);
    			emailTextField.setDisable(true);
    			phoneNumberTextField.setDisable(true);
    			birthdayDatePicker.setDisable(true);
    			chooseMenuButton.setDisable(true);
    			emailCheckBox.setDisable(true);
    			phoneCheckBox.setDisable(true);
    			maleRadioButton.setDisable(true);
    			femaleRadioButton.setDisable(true);
    			IDNumberTextField.setDisable(true);
    			passwordTextField.setDisable(true);    			
    		}
    		
    	}
		
	}
    /**
     * Press to view currently rented books for this student.
     */
    @FXML
    void viewCurrentlyRentedBooksButtonClicked(ActionEvent event) {
           Router.CurrentlyRentedByLibrarian();
    }
    /**
     * Press enter to load the student's details by his id.
     */
    @FXML
    void IDNumberTextFieldEnterClicked(ActionEvent event) {
    	User.isLoaded = false;
    	Client.loadUser(IDNumberTextField.getText());
    }
    /**
     * Press to create a new account, if some text field is empty alert box will popup.
     */
    @FXML
    void createAccountNowButtonClicked(ActionEvent event) {	//THIS IS THE SAVE BUTTON
    	
    	if(firstNameTextField.getText().equals("")) {
    		AlertBox.display("Error", "Must Enter a first name", "Okay");
    		return;
    	}
    	
    	if(lastNameTextField.getText().equals("")) {
    		AlertBox.display("Error", "Must Enter a last name", "Okay");
    		return;
    	}
    	
    	if(emailTextField.getText().equals("")) {
    		AlertBox.display("Error", "Must Enter an email", "Okay");
    		return;
    	}
    	
    	if(IDNumberTextField.getText().equals("")) {
    		AlertBox.display("Error", "Must Enter an ID number", "Okay");
    		return;
    	}
    	
    	if(passwordTextField.getText().equals("")) {
    		AlertBox.display("Error", "Must Enter a password", "Okay");
    		return;
    	}
    	
    	if((emailCheckBox.isSelected() == false) && (phoneCheckBox.isSelected() == false)) {
    		AlertBox.display("Error", "Must selecte at least one contacting method", "Okay");
    		return;
    	}
    	
    	java.sql.Date birthdayDate = DateConvertor.localDateToSqlDate(birthdayDatePicker.getValue());
    	
    	Client.SaveChanges_LS(	firstNameTextField.getText(),
    							lastNameTextField.getText(),
    							emailTextField.getText(),
    							IDNumberTextField.getText(),
    							passwordTextField.getText(),
    							phoneNumberTextField.getText(),
    							birthdayDate,	//should be birthday
    							chooseMenuButton.getText(),
    							gender,
    							(emailCheckBox.isSelected()?"1":"0"),
    							(phoneCheckBox.isSelected()?"1":"0"),
    							accountStatusManuButton.getText());
    }
    /**
     * Press to rent a new book for this student.
     */
    @FXML
    void rentANewBookButtonClicked(ActionEvent event) {
    	User.lookForThisUserByID = IDNumberTextField.getText();
    	ManyBooks.lookForThisBookBySerial = "";
    	Router.RentByLibrarian();
    }
    /**
     * Press to select active status.
     */
    @FXML
    void activeMenuItemClicked(ActionEvent event) {
    	accountStatusManuButton.setText("Active");
    }  
    /**
     * Press to select frozen status.
     */
    @FXML
    void frozenMenuItemClicked(ActionEvent event) {
    	accountStatusManuButton.setText("Frozen");
    }
    /**
     * Press to select locked status.
     */
    @FXML
    void lockedMenuItemClicked(ActionEvent event) {
    	accountStatusManuButton.setText("Locked");
    }
    /**
     * Press on the menu button to create new account.
     */
    @FXML
    void createNewProfileMenuItemClicked(ActionEvent event) {
    	Router.CreateNewAccount();
    }
    /**
     * Press to select female gender.
     */
    @FXML
    void femaleRadioButtonClicked(ActionEvent event) {
    	if(femaleRadioButton.isSelected()) {
    		maleRadioButton.setSelected(false);
    		gender = "female";
    	}
    	else
    		femaleRadioButton.setSelected(true);
    }
    /**
     * Press to select male gender.
     */
    @FXML
    void maleRadioButtonClicked(ActionEvent event) {
    	if(maleRadioButton.isSelected()) {
    		femaleRadioButton.setSelected(false);
    		gender = "male";
    	}
    	else
    		maleRadioButton.setSelected(true);
    }
    /**
     * Press on the menu button to see my profile.
     */
    @FXML
    void myProfileMenuItemClicked(ActionEvent event) {
    	Router.ViewProfile();
    }

    @FXML
    void phoneCheckBoxClicked(ActionEvent event) {
    	//Nothing should be here (DONT DELETE ME!)
    }
    
    @FXML
    void emailCheckBoxClicked(ActionEvent event) {
    	//Nothing should be here (DONT DELETE ME!)
    }
    /**
     * Press on the menu button to return a book.
     */
    @FXML
    void returnMenuButtonClicked(ActionEvent event) {
    	Router.ReturnABook();
    }
    /**
     * Press on the menu button to search a book.
     */
    @FXML
    void searchMenuButtonClicked(ActionEvent event) {
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
    void stockManagmentMenuButtonClicked(ActionEvent event) {
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
     * Choose "software" faculty from the menu for the account faculty.
     */
    @FXML
    void softwareMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Software");
    }
    /**
     * Choose "applied mathematics" faculty from the menu for the account faculty.
     */
    @FXML
    void AppliedMathematicsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Applied Mathematics");
    }
    /**
     * Choose "biotechnology" faculty from the menu for the account faculty.
     */
    @FXML
    void BiotechnologyMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Biotechnology");
    }
    /**
     * Choose "inforamation systems" faculty from the menu for the account faculty.
     */
    @FXML
    void InforamationSystemsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Inforamation Systems");
    }
    /**
     * Choose "optics" faculty from the menu for the account faculty.
     */
    @FXML
    void OpticsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Optics");
    }
    /**
     * Choose "electronics" faculty from the menu for the account faculty.
     */
    @FXML
    void eclectricityNEkctronicsMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Electronics");
    }
    /**
     * Choose "industy & managment" faculty from the menu for the account faculty.
     */
    @FXML
    void industryNmanagmentMenuItemClicked(ActionEvent event) {
    	chooseMenuButton.setText("Industry & managment");
    }

    @FXML
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert createNewProfileMenuItem != null : "fx:id=\"createNewProfileMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert searchMenuButton != null : "fx:id=\"searchMenuButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert returnMenuButton != null : "fx:id=\"returnMenuButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert stockManagmentMenuButton != null : "fx:id=\"stockManagmentMenuButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert createAccountLabel != null : "fx:id=\"createAccountLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert firstNameLabel != null : "fx:id=\"firstNameLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert lastNameLabel != null : "fx:id=\"lastNameLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert birthdayLabel != null : "fx:id=\"birthdayLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert birthdayDatePicker != null : "fx:id=\"birthdayDatePicker\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert facultyMenuButton != null : "fx:id=\"facultyMenuButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert chooseMenuButton != null : "fx:id=\"chooseMenuButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert softwareMenuItem != null : "fx:id=\"softwareMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert InforamationSystemsMenuItem != null : "fx:id=\"InforamationSystemsMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert industryNManagmenMenuItem != null : "fx:id=\"industryNManagmenMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert eclectricityNEkctronicsMenuItem != null : "fx:id=\"eclectricityNEkctronicsMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert AppliedMathematicsMenuItem != null : "fx:id=\"AppliedMathematicsMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert BiotechnologyMenuItem != null : "fx:id=\"BiotechnologyMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert OpticsMenuItem != null : "fx:id=\"OpticsMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert getUpdatesToLabel != null : "fx:id=\"getUpdatesToLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert emailCheckBox != null : "fx:id=\"emailCheckBox\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert phoneCheckBox != null : "fx:id=\"phoneCheckBox\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert genderLabel != null : "fx:id=\"genderLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert maleRadioButton != null : "fx:id=\"maleRadioButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert femaleRadioButton != null : "fx:id=\"femaleRadioButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert IDNumberLabel != null : "fx:id=\"IDNumberLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert IDNumberTextField != null : "fx:id=\"IDNumberTextField\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert IDIconImageView != null : "fx:id=\"IDIconImageView\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert passwordIconImageView != null : "fx:id=\"passwordIconImageView\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert accountStatusLabel != null : "fx:id=\"accountStatusLabel\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert accountStatusManuButton != null : "fx:id=\"accountStatusManuButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert activeMenuItem != null : "fx:id=\"activeMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert frozenMenuItem != null : "fx:id=\"frozenMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert lockedMenuItem != null : "fx:id=\"lockedMenuItem\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert viewCurrentlyRentedBooksButton != null : "fx:id=\"viewCurrentlyRentedBooksButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert rentANewBookButton != null : "fx:id=\"rentANewBookButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";
        assert createAccountNowButton != null : "fx:id=\"createAccountNowButton\" was not injected: check your FXML file 'VSP_fxml_ViewStudentProfile.fxml'.";

    }
    
}
