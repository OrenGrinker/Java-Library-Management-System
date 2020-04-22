package logics;

import java.util.ArrayList;

import application.*;
import controllers.SM_FXML_StockManagment_Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * open page of report for manager.
 * @author Obl_Team13.
 *
 */
public class RM_ReportManager implements Runnable{

	private Stage stage;

	/**
	 * constructor of RM_ReportManager.
	 * @param stage stage type.
	 */
	public RM_ReportManager(Stage stage) {
		this.stage = stage;
	}

	/**
	 * initialize screen.
	 */
	@Override
	public void run() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/RM_fxml_ReportManager.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			stage.getIcons().add(new Image(RM_ReportManager.class.getResourceAsStream("/z_images/logo_black.png")));
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2); 
		    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2); 
			
			stage.show();
		} catch(Exception e) {
			System.out.println("ARGHHHHHHH!!! RM_ReportManager");
			e.printStackTrace();
		}
	}

	/**
	 * load books/rents/student/employee information to the matching class. 
	 * @param pipe object of Pipe class. contains command-string and data.
	 */
	public static void DeliverMessage(Pipe pipe) {

		String command	= ((Pipe)pipe).getCommand();
		Object data		= ((Pipe)pipe).getData();


		if(command.equals("loadStudentsResult")) {
		
			ManyNonStaticStudents.clearStaticbooks();
		
			ArrayList<NonStaticUser> students = new ArrayList<NonStaticUser>();
			students = ((ManyNonStaticStudents)data).getAllUsers();
		
			for(NonStaticUser user : students) {
				try {
					System.out.println("Adding User " + user + " to the Classroom");
					ManyNonStaticStudents.addUserToStatic(user);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		
			ManyNonStaticStudents.isLoaded = true;		
			Router.ViewStudents();
		
		}
		else if(command.equals("loadEmployeesResult")) {
			ManyNonStaticEmployees.clearStaticbooks();

			ArrayList<NonStaticEmployee> employees = new ArrayList<NonStaticEmployee>();
			employees = ((ManyNonStaticEmployees)data).getAllEmployees();

			for(NonStaticEmployee NonStaticEmployee : employees) {
				try {
					System.out.println("Adding Employee " + NonStaticEmployee + " to the employees");
					ManyNonStaticEmployees.addEmployeeToStatic(NonStaticEmployee);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

			ManyNonStaticEmployees.isLoaded = true;
			Router.ViewEmployees();
		}
		
		
		else if(command.equals("viewRentsResult")) {
			ManyRents.clearStaticRents();

			ArrayList<Rent> rents = new ArrayList<Rent>();
			rents = ((ManyRents)data).getAllRents();

			for(Rent rent : rents) {
				try {
					System.out.println("Adding Rent " + rent + " to the Rents");
					ManyRents.addRentToStatic(rent);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

			ManyRents.isLoaded = true;
			Router.ViewRents();
		}
		
		
		else if(command.equals("createNewReportResult")) {
			
			System.out.println("in createNewReportResult");
			
			try {
				ArrayList<String> statusList	= (ArrayList<String>)(((ArrayList<Object>)data).get(0));
				String NumberOfLateReturns		= (String)((ArrayList<Object>)data).get(1);
				String numberOfCopies			= (String)((ArrayList<Object>)data).get(2);
				
				
				int allStudents = 0, activeStudents = 0, frozenStudents = 0, lockedStudents = 0;
				
				System.out.println("manyStudents: " + statusList);
				System.out.println("NumberOfLateReturns: " + NumberOfLateReturns);
				System.out.println("numberOfCopies: " + numberOfCopies);
				
				for(String nsu : statusList) {
					if(nsu.toLowerCase().equals("active"))
						activeStudents++;
					else if(nsu.toLowerCase().equals("frozen"))
						frozenStudents++;
					else if(nsu.toLowerCase().equals("locked"))
						lockedStudents++;
					allStudents++;
				}
				
				java.util.Date date = new java.util.Date();
				
				
				Platform.runLater(new AlertBox(1, "New report", "Periodic report for " + date + 
																"\nTotal Users: " + allStudents + 
																"\nActive Users: " + activeStudents + 
																"\nFrozen Users: " + frozenStudents + 
																"\nLocked Users: " + lockedStudents + 
																"\nTotal number of copies in OBL: " + numberOfCopies + 
																"\nNumber of late students: " + NumberOfLateReturns, "Close"));
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
}
