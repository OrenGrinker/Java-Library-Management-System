//
package application;

import javafx.application.Platform;
import javafx.stage.Stage;
import logics.*;

/**
 * Class for router that open screens based on switch case.
 * @author OBL_Team13
 */
public class Router {

	private static Stage stage;
	private static int prevWindow;

	/**
	 * Constructor for router.
	 * @param stage	JavaFX stage type.
	 */
	public Router(Stage stage) {
		this.stage = stage;
		prevWindow = -1;	//Must be -1 at the beginning
		PreviousWindow();
	}

	/**
	 * Based on previous window open next window with switch cases.
	 */
	public static void PreviousWindow() {
		switch(prevWindow) {
		case -1:
			IPSetup();
			break;
		case 0:
			Login();
			break;
		case 1:
			CreateNewAccount();
			break;
		case 2:
			SearchABookLibrarian();
			break;
		case 3:
			SearchABookStudent();
			break;
		case 4:
			ViewProfile();
			break;
		case 5:
			ReturnABook();
			break;
		case 6:
			SearchResultLibrarian();
			break;
		case 7:
			SearchResultStudent();
			break;
		case 8:
			BookListLibrarian();
			break;
		case 9:
			BookListStudent();
			break;
		case 10:
			ExtendTime();
			break;
		case 11:
			RentByLibrarian();
			break;
		case 12:
			AddABook();
			break;
		case 13:
			StockManagment();
			break;
		case 14:
			EditABook();
			break;
		case 15:
			ViewStudentProfile();
			break;
	    case 16:
	  		MyBook();
	  		break;
	    case 17:
	  		MyCurrentBook();
	  		break;
	    case 18:
	    	ReportManager();
	  		break;
	    case 19:
	    	ViewStudents();
	  		break;
	    case 20:
	    	ViewEmployees();
	  		break;
	    case 21:
	    	ViewRents();
	  		break;

	    case 22:
	  		CurrentlyRentedByLibrarian();
	    	break;
		}
	}

	/**
	 * Set the first window to open when activating the OBL application.
	 */
	public static void IPSetup() {
		prevWindow = -1;
		new IP_IPSetup(stage);
	}

	public static void Login() {
		prevWindow = 0;

		//Resets:
		User.reSet();
		Employee.reSet();
		ManyBooks.clearStaticbooks();
		ManyBooks.lookForThisBookBySerial = "";

		Platform.runLater(new LI_Login(stage));
	}

	public static void CreateNewAccount() {
		prevWindow = 1;
		Platform.runLater(new CNA_CreateNewAccount(stage));
	}

	public static void SearchABookLibrarian() {
		//new SAB_SearchABook_Librarian(stage);
		prevWindow = 2;
		Platform.runLater(new SAB_SearchABook_Librarian(stage));
	}

	public static void SearchABookStudent() {
		//new SAB_SearchABook_Student(stage);
		prevWindow = 3;
		Platform.runLater(new SAB_SearchABook_Student(stage));
	}

	public static void ViewProfile() {
		prevWindow = 4;
		try {

			if(Employee.getID() == null)
				Platform.runLater(new P_Profile(stage));
			else
				Platform.runLater(new P_Profile_Librarian(stage));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void ReturnABook() {
		prevWindow = 5;
		Platform.runLater(new RAB_ReturnABook(stage));
	}

	public static void SearchResultLibrarian() {
		prevWindow = 6;
		Platform.runLater(new SR_SearchResultLibrarian(stage));
	}

	public static void SearchResultStudent() {
		prevWindow = 7;
		Platform.runLater(new SR_SearchResultStudent(stage));
	}

	public static void BookListLibrarian() {
		prevWindow = 8;
		new BL_BookListLibrarian(stage);
	}

	public static void BookListStudent() {
		prevWindow = 9;
		new BL_BookListStudent(stage);
	}

	public static void ExtendTime() {
		prevWindow = 10;
		new ET_ExtendTime(stage);
	}

	public static void RentByLibrarian() {
		prevWindow = 11;
		Platform.runLater(new RBL_RentByLibrarian(stage));
	}

	public static void AddABook() {
		prevWindow = 12;
		Platform.runLater(new AAB_AddABook(stage));
	}

	public static void StockManagment() {
		prevWindow = 13;
		if(ManyBooks.isLoaded)
			Platform.runLater(new SM_StockManagment(stage));
		else
			Client.loadBooks();
	}

	public static void EditABook() {
		prevWindow = 14;
		Platform.runLater(new EAB_EditABook(stage));
	}

	public static void ViewStudentProfile() {
		prevWindow = 15;
		Platform.runLater(new VSP_ViewStudentProfile(stage));
	}

	public static void MyBook() {
		prevWindow = 16;
		System.out.println("funcy");
		if(ManyRents.isLoaded) {
			System.out.println("loaded");
			Platform.runLater(new BL_BookListStudent(stage));
		}
		else{
			System.out.println(ManyRents.isLoaded);
			 Client.loadMyBooks();
			 System.out.println(ManyRents.isLoaded);
		}
	}

	public static void MyCurrentBook() {
		prevWindow = 17;
		System.out.println("funcy2");
		if(ManyRents.isLoaded)	{

			System.out.println("loaded");
			Platform.runLater(new CurrentlyRentedBooks(stage));
		}
		else	{
			System.out.println(ManyRents.isLoaded);
			 Client.loadMyCurrentBooks();
			 System.out.println(ManyRents.isLoaded);
		}
	}

	public static void ReportManager() {
		prevWindow = 18;
		Platform.runLater(new RM_ReportManager(stage));
	}

	public static void ViewStudents() {
		prevWindow = 19;
		if(ManyNonStaticStudents.isLoaded)
			Platform.runLater(new VASR_ViewAllStudentsReport(stage));
		else
			Client.loadStudents();
	}

	public static void ViewEmployees() {
		prevWindow = 20;
		if(ManyNonStaticEmployees.isLoaded)
			Platform.runLater(new VAER_ViewAllEmployeesReport(stage));
		else
			Client.loadEmployees();
	}

	public static void ViewRents() {

		prevWindow = 21;
		if(ManyRents.isLoaded)
			Platform.runLater(new VR_ViewRents(stage));
		else
			Client.viewRents();

	}

	public static void CurrentlyRentedByLibrarian() {
		prevWindow = 22;
		System.out.println("funcy3");
		if(ManyRents.isLoaded)	{
		//if(true) {
			System.out.println("loaded");
			Platform.runLater(new CRBL_CurrentlyRentedByLibrarian_logcis(stage));
		}
		else	{
			System.out.println(ManyRents.isLoaded);
			 Client.CurrentBooksByLibrarian();
			 System.out.println(ManyRents.isLoaded);
		}
	}
	
	/**
	 * About button for general information on OBL application.
	 */
	public static void About()	{
		String about=new String();
		about="OBL v1.0\r\n" +
				"\r\n" +
				" All copyrights belong to Team13: Shay Axelrod, Oren Grinker, Matan Feldman, Dolev Ashtamker,Shoham Gamliel.\n All rights reserved.\n";

		AlertBox.display("About", about, "Close");
	}

}
