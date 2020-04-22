package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import application.AlertBox;
import application.DateConvertor;
import application.ManyRents;
import application.Rent;
import application.Router;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.chart.XYChart;

/**
 * View Rents Controller.
 * @author OBL_Team13
 */
public class VR_FXML_ViewRents_Controller implements Initializable{

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
    private Label titleLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label avgLabel;

    @FXML
    private Label medianLabel;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis rentAmountAxis;

    @FXML
    private NumberAxis daysDistributionAxis;
    /**
     * Initialize the reports by cases.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	int mode = Rent.mode;
    	
    	
    	switch(mode) {
    	case 1:
    		titleLabel.setText("View Late Returns");
    		break;
    	case 2:
    		titleLabel.setText("View Late Returns for " + Rent.lookForThisBook);
    		break;
    	}    	
    	
    	ArrayList<Rent> rents = ManyRents.getAllStaticRents();
    	
		ArrayList<Double> rentDaysArray = new  ArrayList<Double>();
		int maxRent = 0;
		
		double sum = 0;
		
		for(Rent rent : rents) {
			
			Double daysBetween = (double) 0;
			
			switch(mode){
				case 0:
					daysBetween = (double) ((rent.getDateOfRealReturn().getTime() - rent.getDateOfRent().getTime()) / (1000*60*60*24));			
					rentDaysArray.add(daysBetween);
					sum += daysBetween;
					maxRent = (int) ((daysBetween > maxRent) ? daysBetween : maxRent);
					break;
				case 1:
					if(rent.getDateOfRealReturn().getTime() > rent.getDateOfReturn().getTime()) {
						daysBetween = (double) ((rent.getDateOfRealReturn().getTime() - rent.getDateOfReturn().getTime()) / (1000*60*60*24));			
						rentDaysArray.add(daysBetween);	
						sum += daysBetween;
						maxRent = (int) ((daysBetween > maxRent) ? daysBetween : maxRent);
					}
					break;
				case 2:
					if((rent.getDateOfRealReturn().getTime() > rent.getDateOfReturn().getTime()) && Rent.lookForThisBook.toLowerCase().equals(rent.getName().toLowerCase())) {
						daysBetween = (double) ((rent.getDateOfRealReturn().getTime() - rent.getDateOfReturn().getTime()) / (1000*60*60*24));			
						rentDaysArray.add(daysBetween);	
						sum += daysBetween;
						maxRent = (int) ((daysBetween > maxRent) ? daysBetween : maxRent);
					}
					break;
			}
			
		}
		
		if(rentDaysArray.size() == 0) {
			Platform.runLater(new AlertBox(1, "Error", "No result found!", "Close"));
			return;
		}
		
		int NumOfRents=rentDaysArray.size();
		
		double average = sum/NumOfRents;		
		
		Collections.sort(rentDaysArray);
		double median;

		if(NumOfRents%2 == 1)
			median = (rentDaysArray.get(rentDaysArray.size()/2));
		else
			median = (rentDaysArray.get(rentDaysArray.size()/2) + rentDaysArray.get(rentDaysArray.size()/2 -1))/2;
		
		avgLabel.setText(	String.format("%.2f", average));
		medianLabel.setText(String.format("%.2f", median));

		maxRent = maxRent + ( 10 - (maxRent%10));	
		
		ArrayList<Integer> rentTimeForChart = new ArrayList<Integer>();
		
		for(int i = 0; i < 10; i++)	// Zeroing the values
			rentTimeForChart.add(0);
		
		System.out.println("rentTimeForChart: " + rentTimeForChart);
		
		System.out.println("maxRent: " + maxRent);
		
		//Add rentDaysArray to the correct slot in rentTimeForChart (counter array)
		for(Double rentTime : rentDaysArray)
			rentTimeForChart.set((rentTime.intValue() / (maxRent / 10)), rentTimeForChart.get((rentTime.intValue() / (maxRent / 10))) + 1);
		
		System.out.println("rentTimeForChart: " + rentTimeForChart);
		
		XYChart.Series chartData = new XYChart.Series<>();
		
		//ADD DATA TO THE TABLE FROM HERE
		
		int stepSize = maxRent/10;
		for(int i = 0; i < rentTimeForChart.size(); i++) {// Integer value : rentTimeForChart) {
			String before, after;
			before = String.valueOf(stepSize*i);
			after = String.valueOf((stepSize*(i+1)) - 1);
			Integer value = rentTimeForChart.get(i);
			chartData.getData().add(new XYChart.Data(before + "-" + after, value));
		}
		
		barChart.getData().add(chartData);
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
     * Press back to the report manager.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
    	Router.ReportManager();
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
        assert menuBar1 != null : "fx:id=\"menuBar1\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert myProfileMenuItem != null : "fx:id=\"myProfileMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert createNewMenuItem != null : "fx:id=\"createNewMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert viewProfileMenuItem != null : "fx:id=\"viewProfileMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert signOutMenuItem != null : "fx:id=\"signOutMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert SearchABookMenuItem != null : "fx:id=\"SearchABookMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert returnABookMenuItem != null : "fx:id=\"returnABookMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert stockManagmentMenuItem != null : "fx:id=\"stockManagmentMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert AboutMenuItem != null : "fx:id=\"AboutMenuItem\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert avgLabel != null : "fx:id=\"avgLabel\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert medianLabel != null : "fx:id=\"medianLabel\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert rentAmountAxis != null : "fx:id=\"rentAmountAxis\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";
        assert daysDistributionAxis != null : "fx:id=\"daysDistributionAxis\" was not injected: check your FXML file 'VR_fxml_ViewRents.fxml'.";

    }
	
}
