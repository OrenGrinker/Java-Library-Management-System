// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package client;

import ocsf.client.*;
import common.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logics.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import application.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************

  /**
   * The interface type variable.  It allows the implementation of
   * the display method in the client.
   */
  ChatIF clientUI;


  //Constructors ****************************************************

  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */


  	public static String studentID;
	public static String studentName;
	public static String statusMembership;
	public static String operation;
	public static boolean freeze;



  public ChatClient(String host, int port, ChatIF clientUI)
    throws IOException
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;

    openConnection();
  }

  public void handleMessageFromServer(Object msg)
  {
	  // we gonna handle stuff here with if-else cases to know what to do with the data.
	  // like, getting "getStudent" + RestOfMSG will result in updating GUI.
	  // or "updated" result in success message.

	  String command = ((Pipe)msg).getCommand().toString();
	  System.out.println("Command received in chattClient: " + command);

	  if(command.equals("SearchByBookName_SResult") || command.equals("SearchByAuthorName_SResult") || command.equals("SearchByBookTopic_SResult") || command.equals("SearchByFreeText_SResult"))
		  SAB_SearchABook_Student.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("SearchByBookName_LResult") || command.equals("SearchByAuthorName_LResult") || command.equals("SearchByBookTopic_LResult") || command.equals("SearchByFreeText_LResult"))
		  SAB_SearchABook_Librarian.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("AttemptLoginResult"))
		  LI_Login.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("SaveChanges_SResult"))
		  P_Profile.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("SaveChanges_LResult"))
		  P_Profile_Librarian.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("CreateNewAccountResult"))
		  CNA_CreateNewAccount.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("CreateNewBookResult"))
		  AAB_AddABook.DeliverMessage(((Pipe)msg).getData());

	  else if(command.equals("RemoveABookResult") || command.equals("loadBooksResult") || command.equals("EditABookResult"))
		  SM_StockManagment.DeliverMessage((Pipe)msg);

	  else if(command.equals("loadUserResult") || command.equals("SaveChanges_LSResult"))
		  VSP_ViewStudentProfile.DeliverMessage((Pipe)msg);

	  else if(command.equals("loadBookBySerialResult") || command.equals("attemptRentBookResult"))
		  RBL_RentByLibrarian.DeliverMessage((Pipe)msg);

      else if(command.equals("loadMyBooksResult")) {
  		  System.out.println("in chat cient");
  		  BL_BookListStudent.DeliverMessage((Pipe)msg);
  		   }
	  
  	  else if(command.equals("loadMyCurrentBooksResult")) { 
  		  System.out.println("in chat cient");
  		  CurrentlyRentedBooks.DeliverMessage((Pipe)msg);
  	  }
	  
  	 else if(command.equals("CurrentBooksByLibrariansResult")) {  
 		  System.out.println("in chat cient");
 		 CRBL_CurrentlyRentedByLibrarian_logcis.DeliverMessage((Pipe)msg);
 	  }
	  
	  
  	  else if(command.equals("fetchReturnDataResult") || command.equals("returnABookResult"))
  		  RAB_ReturnABook.DeliverMessage((Pipe)msg);
	  
  	  else if(command.equals("loadEmployeesResult") || command.equals("loadStudentsResult") || command.equals("viewRentsResult") || command.equals("createNewReportResult"))
  		  RM_ReportManager.DeliverMessage((Pipe)msg);
	  
  	  else if(command.equals("attemptOrderBook"))
  		SR_SearchResultStudent.DeliverMessage((Pipe)msg);
	  /*
  	 else if(command.equals("UpdateReturnDateResult"))
   		SR_SearchResultStudent.DeliverMessage((Pipe)msg);
	   */
	  /*
	  else if(command.equals("RemoveABookResult"))
		  SM_StockManagment.DeliverMessage(((Pipe)msg).getData());
	  */


	  clientUI.display(msg.toString());
  }
  
  /**
   * This method handles all data coming from the UI
   *
   * @param message The message from the UI.
   */
  public void handleMessageFromClientUI(String message) 
  {
    try
    {
    	//------

    	ArrayList<String> arrayList = new ArrayList<String>();

    	arrayList.addAll(Arrays.asList("Bob", "123456", "VIF project", "7654321"));

    	if(message.toLowerCase().equals("send")) {
    		sendToServer(arrayList);
    	}
    	else {
    		String splitter[] = message.split(" ", 2);
        	splitter[0] = splitter[0].toLowerCase();

        	if(splitter[0].equals("send"))
        		sendToServer(splitter[1]);

        	else
        		sendToServer(message);
    	}


    	//------
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }

  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
