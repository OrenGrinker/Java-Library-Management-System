package runUs;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com
//import java.sql.Date;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import application.*;
import javafx.stage.Stage;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer
{
  //Class variables *************************************************

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;

  public static String dbPassword = "";

  private Connection conn;

  //Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  
  public EchoServer(int port)
  {	  
    super(port);
    
    try
	{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception ex) {/* handle the error*/}

    try
    {
    	
    	/*
    	System.out.println("Enter password for DB: ");
        Scanner scanIn = new Scanner(System.in);
        dbPassword = scanIn.nextLine();
        scanIn.close();*/

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ I NOW CHANGE WHEN YOU RUN ME!!!! ~~~~~~~~~~~~~~~~
        conn = DriverManager.getConnection("jdbc:mysql://localhost/obl","root",dbPassword);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ~~~~~~~~~~~~~~~~

        System.out.println("Password Recieved is: " + dbPassword);
        System.out.println("SQL connection succeed");


 	} catch (SQLException ex)
 	    {/* handle any errors*/
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        }

    
    
    Statement stmt;
    // This will send email for every user that need to return book tomorrow or he will be late!
	try {
		stmt = conn.createStatement();
		ResultSet rs = null;
		LocalDate date=LocalDate.now().plusDays(1);
		java.sql.Date tomorrow=java.sql.Date.valueOf(date);
		System.out.println(tomorrow);

		rs = stmt.executeQuery("SELECT Email, Name, FirstName FROM users,renthistory Where renthistory.DateOfRealReturn IS NULL AND renthistory.UserID=users.ID AND renthistory.DateOfReturn= '" +  tomorrow + "';");
		while(rs.next())
		{
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			
			// un // only if you want to send emails for test. it works.
			SendEmail.sendEmail(rs.getString(1), "OBL Mail System: Return date tomorrow!", "Dear " + rs.getString(3) + ", \n The return date for " + rs.getString(2) + " is tomorrow. \n Please return it on time. \n Thanks in advance - OBL TEAM");

		}
		rs.close();
	}

	catch(SQLException ex){/* handle any errors*/
		
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
    }

	
	
	// This will freeze any student who is late to return a book.
	try {
		stmt = conn.createStatement();
		ResultSet rs = null;
		LocalDate date=LocalDate.now();
		java.sql.Date today=java.sql.Date.valueOf(date);
		System.out.println(today);

		rs = stmt.executeQuery("SELECT DISTINCT(UserID) FROM renthistory WHERE renthistory.DateOfRealReturn IS NULL AND renthistory.DateOfReturn < '" +  today + "';");
		while(rs.next()){
			
			Statement stmt2 = conn.createStatement();
			
			String frozen = "frozen";
			String studentID = rs.getString(1);
			System.out.println(studentID);			
	
			int affectedRows = stmt2.executeUpdate("UPDATE users SET Status = '" + frozen + "' WHERE users.ID = '" + studentID + "';");
		}
		rs.close();
	}

	catch(SQLException ex){/* handle any errors*/
		ex.printStackTrace();
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
    }
	
	
	
	// This will delete expired orders and will check if other order takes it's place and send mail or add 1 copy to available copies of book
		try {
			stmt = conn.createStatement();
			ResultSet rs = null;
			LocalDate date=LocalDate.now();
			java.sql.Date today=java.sql.Date.valueOf(date);
			System.out.println(today + " 2");

			rs = stmt.executeQuery("SELECT RowNum, BookSerial FROM order2 WHERE BookIsReadyUntilDate IS NOT NULL AND BookIsReadyUntilDate < '" +  today + "';");
			while(rs.next()){
				System.out.println("rs: " + rs);
				
				Statement stmt2 = conn.createStatement();
				Statement stmt3 = conn.createStatement();
				Statement stmt4 = conn.createStatement();

				
				int rowNum = rs.getInt(1);
				String serial = rs.getString(2);
				System.out.println(rowNum + " " + serial);			
								
				stmt2.executeUpdate("DELETE FROM order2 WHERE RowNum = '" + rowNum + "';");				
				
				ResultSet rs2 = null;
				ResultSet rs3 = null;
				
				// check if there is another order with this book
				rs2 = stmt2.executeQuery("SELECT * FROM order2 WHERE BookIsReadyUntilDate IS NULL AND BookSerial = '" + serial + "';");
				rs2.last();
				if(rs2.getRow() == 0) {
					//No one ordered the book
					rs2.first();
					rs3 = stmt3.executeQuery("SELECT NumberOfAvailabeCopies FROM books WHERE Serial = '" + serial + "';");
					rs3.first();
					int nOAC = (Integer.parseInt(rs3.getString(1)) + 1);
					
					// add one to available copies
					stmt2.executeUpdate("UPDATE books SET NumberOfAvailabeCopies = '" + String.valueOf(nOAC) + "' WHERE Serial = '" + serial + "';");
				}
				else{
					// there is another order
					rs2.first();
					int rowNumGetOrder = rs2.getInt(1);
					rs2.first();
					String id = rs2.getString(3);	//getUserID;
					rs3 = stmt2.executeQuery("SELECT Email, FirstName FROM users WHERE ID = '" + id + "';");
					rs3.first();
					String emailAddress = rs3.getString(1);
					String FirstName = rs3.getString(2);
					rs3 = stmt2.executeQuery("SELECT BookName FROM books WHERE Serial = '" + serial + "';");
					rs3.first();
					String bookName = rs3.getString(1);
					SendEmail.sendEmail(emailAddress, "OBL Mail System: Your book order is ready!", "Dear " + FirstName + ", \n Your book order - " + bookName + " is waiting for you in the library for the next 2 days. \n Please come rent it on time. \n Thanks in advance - OBL TEAM");
					java.sql.Date DayAfterTomorrow=java.sql.Date.valueOf(date.plusDays(2));
					stmt2.executeUpdate("UPDATE order2 SET BookIsReadyUntilDate = '" + DayAfterTomorrow + "' WHERE RowNum = '" + rowNumGetOrder + "';");
				}
				
				
				rs3.close();
				rs2.close();
				
				
				
			}			
			rs.close();
		}

		catch(SQLException ex){/* handle any errors*/
			ex.printStackTrace();
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
	    }
	
	

		// This will active/lock all students on first/last day of the year
		try {
			stmt = conn.createStatement();
			ResultSet rs = null;
			LocalDate date=LocalDate.now();
			java.sql.Date today=java.sql.Date.valueOf(date);
			
			String newStatus = "";
			
			if(today.toString().equals("2019-06-20")) {
				newStatus = "locked";
				stmt.executeUpdate("UPDATE users SET Status = '" + newStatus + "';");
			}
			else if(today.toString().equals("2019-09-01")) {
				newStatus = "active";
				stmt.executeUpdate("UPDATE users SET Status = '" + newStatus + "';");
			}
			
			
		}
		catch(Exception e) {			
			e.printStackTrace();
		}
	
	
  }

  //Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {

	System.out.println("Message received: " + ((Pipe)msg).toString() + "from " + client);
	String command = ((Pipe)msg).getCommand();
	Pipe pipe = new Pipe(command + "Result");	//Make a new pipe with the same command + "Result"


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SEARCH DB FOR A BOOK ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ALL USERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.contains("SearchByBookName") || command.contains("SearchByAuthorName") || command.contains("SearchByBookTopic") || command.contains("SearchByFreeText")) {
    	Statement stmt;
		try {
			stmt = conn.createStatement();

			String wanted = ((String)((Pipe)msg).getData()).toString();

			ResultSet rs = null;
			if(command.contains("SearchByBookName"))
				rs = stmt.executeQuery("SELECT * FROM books Where BookName LIKE '%" + wanted + "%';");
			else if(command.contains("SearchByAuthorName"))
				rs = stmt.executeQuery("SELECT * FROM books Where Author LIKE '%" + wanted + "%';");
			else if(command.contains("SearchByBookTopic"))
				rs = stmt.executeQuery("SELECT * FROM books Where Category LIKE '%" + wanted + "%';");
			else if(command.contains("SearchByFreeText"))
				rs = stmt.executeQuery("SELECT * FROM books Where Description LIKE '%" + wanted + "%';");

			ManyBooks manyBooks = new ManyBooks();
			int amountFound = 0;

			while(rs.next())
	 		{
				amountFound++;
				System.out.println("Book name: " + rs.getString(2));

				Book book = new Book();
				book.setSerial(					rs.getString(1));
				book.setName(					rs.getString(2));
				book.setAuthor(					rs.getString(3));
				book.setGeneration(				rs.getString(4));
				book.setPrintedDate(			rs.getDate(5));
				book.setCategory(				rs.getString(6));
				book.setDescription(			rs.getString(7));
				book.setDateOfPurchase(			rs.getDate(8));
				book.setPlaceOnShelf(			rs.getString(9));
				String bookPath					= rs.getString(10);
				book.setTableOfContentAsPdf(	PdfSlave.getDataAsByteArray(bookPath));
				book.setNumberOfCopies(			rs.getString(11));
				book.setNumberOfAvailabeCopies(	rs.getString(12));

				manyBooks.addBook(book);

			}

			//System.out.println(manyBooks.getAllBooks());
			pipe.setData(manyBooks);

			System.out.println("Command in echo: " + pipe.getCommand() + " Data: " + pipe.getData());
			System.out.println("Amount found: " + amountFound);

			this.sendToAllClients(pipe);

			rs.close();

			return;

		}
		catch (SQLException e) {
			System.out.println("Search Results returned 0!");
			//e.printStackTrace();
			this.sendToAllClients(pipe);
		}
    }

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ATTEMPT LOGIN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ALL USERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("AttemptLogin")) {
		Statement stmt;
		try {
			stmt = conn.createStatement();

			String username	= ((ArrayList<String>)((Pipe)msg).getData()).get(0).toString();
			String pass		= ((ArrayList<String>)((Pipe)msg).getData()).get(1).toString();

			ResultSet rs = null;

			boolean isStudent = true;
			rs = stmt.executeQuery("SELECT * FROM users Where ID = '" + username + "' AND Pass = '" + pass + "';");
			rs.last();
			if(rs.getRow() == 0) {//If no students were found:
				rs = stmt.executeQuery("SELECT * FROM employee Where ID = '" + username + "' AND Pass = '" + pass + "';");
				isStudent = false;
			}

			rs.beforeFirst();
			ArrayList<Object> user = new ArrayList<Object>();	//Data
			while(rs.next()){
				System.out.println("Found Something");
				//Student
				if(isStudent) {
					System.out.println("Found Student");
					user.add(rs.getString(1));	//setID
					user.add(rs.getString(2));	//setPass
					user.add(rs.getString(3));	//setFirstName
					user.add(rs.getString(4));	//setLastName
					user.add(rs.getString(5));	//setEmail
					user.add(rs.getString(6));	//setPhoneNumber
					user.add(rs.getDate(7));	//setBirthdate
					user.add(rs.getString(8));	//setFaculty
					user.add(rs.getString(9));	//setGender
					user.add(rs.getString(10));	//setGetUpdatesToEmail
					user.add(rs.getString(11));	//setGetUpdatesToPhone
					user.add(rs.getString(12));	//setStatus
				}

				//Employee
				else {
					System.out.println("Found Employee");
					user.add(rs.getString(1));	//setId
					user.add(rs.getString(2));	//setRoll
					user.add(rs.getString(3));	//setPass
					user.add(rs.getString(4));	//setFirstName
					user.add(rs.getString(5));	//setLastName
					user.add(rs.getString(6));	//setEmail
					user.add(rs.getString(7));	//setDepartment
					user.add(rs.getDate(8));	//setBirthdate
				}

			}

			pipe.setData(user);
			this.sendToAllClients(pipe);

			rs.close();

			return;

		}
		catch(SQLException e) {
			System.out.println("Search Results returned 0!");
			//e.printStackTrace();
			pipe.setData(new ArrayList<Object>());
			this.sendToAllClients(pipe);
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOAD USER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ALL USERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("loadUser")) {

		Statement stmt;

		try {
			stmt = conn.createStatement();

			String id	= ((Pipe)msg).getData().toString();

			ResultSet rs = null;

			rs = stmt.executeQuery("SELECT * FROM users Where ID = '" + id + "';");
			rs.last();
			if(rs.getRow() == 0) {//If no students were found:
				System.out.println("Found nothing");
				pipe.setData(null);
			}
			else {
				rs.beforeFirst();
				ArrayList<Object> user = new ArrayList<Object>();	//Data
				while(rs.next()){
					System.out.println("Found Student");
					user.add(rs.getString(1));	//setID
					user.add(rs.getString(2));	//setPass
					user.add(rs.getString(3));	//setFirstName
					user.add(rs.getString(4));	//setLastName
					user.add(rs.getString(5));	//setEmail
					user.add(rs.getString(6));	//setPhoneNumber
					user.add(rs.getDate(7));	//setBirthdate
					user.add(rs.getString(8));	//setFaculty
					user.add(rs.getString(9));	//setGender
					user.add(rs.getString(10));	//setGetUpdatesToEmail
					user.add(rs.getString(11));	//setGetUpdatesToPhone
					user.add(rs.getString(12));	//setStatus
				}
				pipe.setData(user);
			}

			this.sendToAllClients(pipe);

			rs.close();

			return;

		}
		catch(SQLException e) {
			System.out.println("Search Results returned 0!");
			//e.printStackTrace();
			pipe.setData(new ArrayList<Object>());
			this.sendToAllClients(pipe);
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SAVE CHANGES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.contains("SaveChanges_LS")) {
		Statement stmt;
		try {

			//Retrieve data
			String firstNameTextField	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);
			String lastNameTextField	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);
			String emailTextField		= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(2);
			String IDNumberTextField	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(3);
			String passwordTextField	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(4);
			String phoneNumber			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(5);
			java.sql.Date dateAsString	= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(6);
			String faculty				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(7);
			String gender		 		= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(8);
			String emailCheck			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(9);
			String phoneCheck 			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(10);
			String status 				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(11);

			stmt = conn.createStatement();

			stmt.executeUpdate("UPDATE users SET ID = '" + IDNumberTextField + "' , "
													+ "Pass = '" + passwordTextField + "' , "
													+ "FirstName = '" + firstNameTextField + "', "
													+ "LastName = '" + lastNameTextField + "', "
													+ "Email = '" + emailTextField + "', "
													+ "PhoneNumber = '" + phoneNumber + "', "
													+ "Birthdate = '" + dateAsString + "', "
													+ "Faculty = '" + faculty + "', "
													+ "Gender = '" + gender + "', "
								 					+ "GetUpdatesToEmail = '" + emailCheck + "', "
													+ "GetUpdatesToPhone = '" + phoneCheck + "', "
													+ "Status = '" + status + "'"
													+ " WHERE ID = '" + IDNumberTextField + "';");

			ArrayList<Object> data = new ArrayList<Object>();

			data.add(IDNumberTextField);
			data.add(passwordTextField);
			data.add(firstNameTextField);
			data.add(lastNameTextField);
			data.add(emailTextField);
			data.add(IDNumberTextField);
			data.add(dateAsString);
			data.add(faculty);
			data.add(gender);
			data.add(emailCheck);
			data.add(phoneCheck);
			data.add(status);

			pipe.setData(data);
			this.sendToAllClients(pipe);
			return;
		}
		catch(SQLException e) {
			System.out.println("in SaveChanges_LS SQLException");
			e.printStackTrace();
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SAVE CHANGES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ALL USERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.contains("SaveChanges")) {
		System.out.println("in SaveChanges");
		Statement stmt;
		try {

			boolean isStudent = command.equals("SaveChanges_S");

			//Retrieve data
			String id		= ((ArrayList<String>)((Pipe)msg).getData()).get(0).toString();
			String email	= null;
			String phone	= null;
			String newPassword;

			if (isStudent) {
				//Student
				email		= ((ArrayList<String>)((Pipe)msg).getData()).get(1).toString();
				phone		= ((ArrayList<String>)((Pipe)msg).getData()).get(2).toString();
				newPassword	= ((ArrayList<String>)((Pipe)msg).getData()).get(3).toString();
			}
			else
				//Librarian
				newPassword	= ((ArrayList<String>)((Pipe)msg).getData()).get(1).toString();

			stmt = conn.createStatement();

			if (isStudent) {
				if (phone.isEmpty())
					stmt.executeUpdate("UPDATE users SET Email = '" + email + "' , PhoneNumber = NULL , Pass = '" + newPassword + "' where ID = '" + id + "';");
				else
					stmt.executeUpdate("UPDATE users SET Email = '" + email + "' , PhoneNumber = '" + phone + "' , Pass = '" + newPassword + "' where ID = '" + id + "';");
			}
			else
				stmt.executeUpdate("UPDATE employee SET Pass = '" + newPassword + "' where ID = '" + id + "';");


			ArrayList<String> data = new ArrayList<String>();

			if(isStudent) {
				data.add(id);
				data.add(email);
				data.add(phone);
				data.add(newPassword);
			}
			else {
				data.add(id);
				data.add(newPassword);
			}

			pipe.setData(data);
			this.sendToAllClients(pipe);
			return;
		}
		catch(SQLException e) {
			e.printStackTrace();
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}

	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CREATE NEW ACCOUNT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("CreateNewAccount")) {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			String IDNumber		  		= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);
			String password				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);
			String firstName			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(2);
			String lastName				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(3);
			String email				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(4);
			String phone				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(5);
			java.sql.Date birthday		= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(6);
			String faculty				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(7);
			String gender				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(8);
			String getupdatesToEmail	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(9);
			String getupdatesToPhone	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(10);
			String status				= "active";

			System.out.println("IN SERVER: " + ((ArrayList<Object>)((Pipe)msg).getData()).toString());

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM users Where ID = '" + IDNumber + "';");
			rs.last();

			if(rs.getRow() != 0) {
				//User already exists!
				rs.close();
				pipe.setData(false);
			}
			else {
				rs.close();
				int affectedRows = 0;

				//executeUpdate returns: An int that indicates the number of rows affected, or 0 if using a DDL statement.

				//INSERT INTO 'obl'.'users' ('ID', 'Pass', 'FirstName', 'LastName', 'Email', 'PhoneNumber', 'Faculty', 'Gender', 'GetUpdatesToEmail', 'GetUpdatesToPhone', 'Status') VALUES ('012', '012', 'matan', 'lazimi', 'ml@gmail.com', '123987', 'soft', 'male', '1', '1', 'active');
				stmt = conn.createStatement();
				affectedRows = stmt.executeUpdate("INSERT INTO users VALUES ('"	 +
													IDNumber			+ "', '" +
													password			+ "', '" +
													firstName			+ "', '" +
													lastName			+ "', '" +
													email				+ "', '" +
													phone				+ "', '" +
													birthday			+ "', '" +
													faculty				+ "', '" +
													gender				+ "', '" +
													getupdatesToEmail	+ "', '" +
													getupdatesToPhone	+ "', '" +
													status				+ "');");

				boolean isSuccessful = ((affectedRows != 0) ? true : false);
				pipe.setData(isSuccessful);
			}
			this.sendToAllClients(pipe);
			return;
		}
		catch(SQLException e) {
			System.out.println("Search Results returned 0!");
			//e.printStackTrace();
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			System.out.println("Echo server CreateNewAccount exception");
		}


	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOAD MY BOOKS && LOAD MY CURRENT BOOKS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ STUDENT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("loadMyBooks")||command.equals("loadMyCurrentBooks") || command.equals("CurrentBooksByLibrarians")) {
		String id=(String)((Pipe)msg).getData();

		Statement stmt;
		try {

			stmt = conn.createStatement();
			ResultSet rs = null;
			 rs = stmt.executeQuery("SELECT * FROM renthistory WHERE UserID='" + id + "';");
			ManyRents MyRents = new ManyRents();	//DATA

			while(rs.next()){
				Rent newRent = new Rent( rs.getString(2),rs.getString(4),rs.getString(3),rs.getDate(5),rs.getDate(6),rs.getDate(7), rs.getString(8));
				System.out.println("inside load my books1");
				/*

			*/	if(command.equals("loadMyCurrentBooks")|| command.equals("CurrentBooksByLibrarians"))
			      { if (rs.getDate(7)==null)////
				       MyRents.addRent(newRent);}

				   else MyRents.addRent(newRent);
			}
			rs.close();
			pipe.setData(MyRents);
			System.out.println("inside load my books2");
			System.out.println(MyRents);

			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("loadMyBooks SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("loadMyBooks exception");
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~      EXTEND RENTING ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
/////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////


	if(command.equals("UpdateReturnDate")) {
		 Rent rent=(Rent)((Pipe)msg).getData();

	    String id=rent.getID();
	    Date RentDate=rent.getDateOfRent();
	    String serialNumber=rent.getSerial();
	    Date returnDate=rent.getDateOfReturn();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = null;

			stmt.executeUpdate("UPDATE renthistory SET DateOfReturn = '" + returnDate + "' Where DateOfRent='" + RentDate + "'  AND UserID='" + id + "' AND BookSerial= '" + serialNumber + "';");//(\"SELECT * FROM renthistory WHERE UserID='\" + id + \"';");
			// rs = stmt.executeQuery("SELECT BookSerial, UserId, DateOfRent FROM renthistory, Where DateOfRent='" + RentDate + "'  AND UserID='" + id + "'  AND renthistory.UserID=users.ID AND BookSerial= '" + serialNumber + "';");//("SELECT * FROM renthistory WHERE UserID='" + id + "';");
			//ManyRents MyRents = new ManyRents();	//DATA
            /*
			while(rs.next()){
				//Rent newRent = new Rent( rs.getString(2),rs.getString(4),rs.getString(3),rs.getDate(5),rs.getDate(6),rs.getDate(7), rs.getString(8));
				System.out.println("1 more rent changed");
			*/

			rs.close();
			//pipe.setData(MyRents);
			System.out.println("inside update rent");
			//System.out.println(MyRents);

			//this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("updateRent SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("updateRent SQLException");//2 exceptions why?
		}
	}

	//////////////


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CREATE NEW || Edit BOOK ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("CreateNewBook") || command.equals("EditABook")) {

		Statement stmt;
		try {
			stmt	= conn.createStatement();

			String Serial		  			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);
			String Name						= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);
			String Author					= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(2);
			String Edition					= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(3);
			java.sql.Date PrintingDate		= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(4);
			String Subject					= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(5);
			String Description				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(6);
			java.sql.Date PurchasedDate		= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(7);
			String Shelf					= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(8);
			byte[] pdfData					= (byte[])((ArrayList<Object>)((Pipe)msg).getData()).get(9);
			String NumberOfCopies			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(10);
			//Number of available copies will always start as NumberOfCopies

			//Save the file on the PC
			String filePath = Serial + ".pdf";
			Files.write(Paths.get(filePath), pdfData);

			System.out.println("IN SERVER CreateNew|EditBook: " + ((ArrayList<Object>)((Pipe)msg).getData()).toString());

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM books Where Serial = '" + Serial + "';");
			rs.last();


			if(command.equals("CreateNewBook") && (rs.getRow() != 0)) {
				//Book already exists!
				rs.close();
				pipe.setData(false);
			}
			else {
				rs.close();
				int affectedRows = 0;

				if(command.equals("CreateNewBook"))
					affectedRows = stmt.executeUpdate("INSERT INTO books VALUES ('"	 +
													Serial				+ "', '" +
													Name				+ "', '" +
													Author				+ "', '" +
													Edition				+ "', '" +
													PrintingDate		+ "', '" +
													Subject				+ "', '" +
													Description			+ "', '" +
													PurchasedDate		+ "', '" +
													Shelf				+ "', '" +
													filePath			+ "', '" +
													NumberOfCopies		+ "', '" +
													NumberOfCopies		+ "');");

				else if(command.equals("EditABook")) {
					affectedRows = stmt.executeUpdate("UPDATE books SET " +
														"Serial = '"					+ Serial + "' ," +
														"BookName = '"					+ Name + "' ," +
														"Author = '"					+ Author + "' ," +
														"Generation = '"				+ Edition + "' ," +
														"PrintedDate = '" 				+ PrintingDate + "' ," +
														"Category = '"					+ Subject + "' ," +
														"Description = '"				+ Description + "' ," +
														"DateOfPurchase = '" 			+ PurchasedDate +"' ," +
														"PlaceOnShelf = '"				+ Shelf + "' ," +
														"TableOfContentAsPDF = '"		+ filePath + "' ," +
														"NumberOfCopies = '"			+ NumberOfCopies + "' ," +
														"NumberOfAvailabeCopies = '"	+ NumberOfCopies + "' " +
														"WHERE Serial = '" + Serial + "';");
				}

				boolean isSuccessful = ((affectedRows != 0) ? true : false);
				pipe.setData(isSuccessful);
			}
			this.sendToAllClients(pipe);
			return;
		}
		catch(SQLException e) {
			System.out.println("CreateNew|EditBook SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			System.out.println("CreateNew|EditBook exception");
		}


	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ DELETE A BOOK ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("RemoveABook")) {

		Statement stmt;
		try {
			String Serial = ((Pipe)msg).getData().toString();

			System.out.println("Removing book with serial: " + Serial);

			int affectedRows = 0;
			stmt = conn.createStatement();

			affectedRows = stmt.executeUpdate("DELETE FROM books WHERE Serial = '" + Serial.toString() + "';");

			boolean isSuccessful = ((affectedRows != 0) ? true : false);

			pipe.setData(isSuccessful);
			this.sendToAllClients(pipe);
		}
		catch(SQLException e) {
			System.out.println("RemoveABook SQLException");
			pipe.setData(false);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			System.out.println("RemoveABook exception");
		}
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOAD BOOKS FROM DB ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("loadBooks")) {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM books;");

			ManyBooks library = new ManyBooks();	//DATA

			while(rs.next()){
				Book newBook = new Book();

				newBook.setSerial(					rs.getString(1));
				newBook.setName(					rs.getString(2));
				newBook.setAuthor(					rs.getString(3));
				newBook.setGeneration(				rs.getString(4));
				newBook.setPrintedDate(				rs.getDate(5));
				newBook.setCategory(				rs.getString(6));
				newBook.setDescription(				rs.getString(7));
				newBook.setDateOfPurchase(			rs.getDate(8));
				newBook.setPlaceOnShelf(			rs.getString(9));
				String filePath						= rs.getString(10);
				newBook.setTableOfContentAsPdf(		PdfSlave.getDataAsByteArray(filePath));
				newBook.setNumberOfCopies(			rs.getString(11));
				newBook.setNumberOfAvailabeCopies(	rs.getString(12));

				System.out.println("Found book!: " + newBook.getSerial() + " " + newBook.getName());

				library.addBook(newBook);
			}
			rs.close();
			pipe.setData(library);
			System.out.println(library);
			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("loadBooks SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("loadBooks exception");
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOAD BOOK BY SERIAL ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("loadBookBySerial")) {

		Statement stmt;

		try {
			stmt = conn.createStatement();

			String serial	= ((Pipe)msg).getData().toString();

			System.out.println("Serial to look for is: " + serial);

			ResultSet rs = null;


			rs = stmt.executeQuery("SELECT * FROM books WHERE Serial = '" + serial + "';");
			rs.last();
			if(rs.getRow() == 0) {//If no books were found:
				System.out.println("Found nothing");
				pipe.setData(null);
			}
			else {
				rs.first();
				Book book = new Book();
				book.setSerial(					rs.getString(1));
				book.setName(					rs.getString(2));
				book.setAuthor(					rs.getString(3));
				book.setGeneration(				rs.getString(4));
				book.setPrintedDate(			rs.getDate(5));
				book.setCategory(				rs.getString(6));
				book.setDescription(			rs.getString(7));
				book.setDateOfPurchase(			rs.getDate(8));
				book.setPlaceOnShelf(			rs.getString(9));
				String filePath					= rs.getString(10);
				book.setTableOfContentAsPdf(	PdfSlave.getDataAsByteArray(filePath));
				book.setNumberOfCopies(			rs.getString(11));
				book.setNumberOfAvailabeCopies(	rs.getString(12));
				pipe.setData(book);
			}

			this.sendToAllClients(pipe);

			rs.close();

			return;

		}
		catch(SQLException e) {
			System.out.println("Search Results returned 0!");
			//e.printStackTrace();
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ATTEMPT RENT A BOOK ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("attemptRentBook")) {

		System.out.println("in attemptRentBook");

		Statement stmt;

		try {
			stmt = conn.createStatement();

			String userID				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);
			String bookSerial			= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);
			java.sql.Date rentDate		= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(2);
			java.sql.Date returnDate	= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(3);

			ResultSet rs = null;

			rs = stmt.executeQuery("SELECT * FROM users WHERE ID = '" + userID + "';");

			String status;
			rs.last();
			status = rs.getString(12).toLowerCase();	//Get the status of the user
			if(status.equals("active") == false) {
				//User is not Active and can't rent the book
				System.out.println("User is not active");
				pipe.setData(false);
				this.sendToAllClients(pipe);
				return;
			}


			rs = stmt.executeQuery("SELECT * FROM books WHERE Serial = '" + bookSerial + "';");
			rs.last();
			if(rs.getRow() == 0) {//If no books were found:
				System.out.println("Found nothing");
				pipe.setData(false);
				this.sendToAllClients(pipe);
				return;
			}

			rs.first();
			Book book = new Book();
			book.setSerial(					rs.getString(1));
			book.setName(					rs.getString(2));
			book.setAuthor(					rs.getString(3));
			book.setGeneration(				rs.getString(4));
			book.setPrintedDate(			rs.getDate(5));
			book.setCategory(				rs.getString(6));
			book.setDescription(			rs.getString(7));
			book.setDateOfPurchase(			rs.getDate(8));
			book.setPlaceOnShelf(			rs.getString(9));
			String filePath					=rs.getString(10);
			book.setTableOfContentAsPdf(	PdfSlave.getDataAsByteArray(filePath));
			book.setNumberOfCopies(			rs.getString(11));
			book.setNumberOfAvailabeCopies(	rs.getString(12));

			String serial	= book.getSerial();
			String nOAC		= book.getNumberOfAvailabeCopies();
			int InOAC		= Integer.parseInt(nOAC);

			if(InOAC <= 0) {

				rs=stmt.executeQuery("SELECT * FROM order2 WHERE UserID = '" + userID + "' AND BookSerial = '" + bookSerial + "' AND BookIsReadyUntilDate IS NOT NULL;");
				rs.last();
				if(rs.getRow() != 0) {
					rs.first();
					stmt.executeUpdate("DELETE FROM order2 WHERE RowNum = '" + rs.getInt(1) + "';");
				}
				else {//Can't rent the book, not enough copies available
					pipe.setData(false);
					this.sendToAllClients(pipe);
					return;
				}
			}
			else {
				nOAC = String.valueOf(InOAC-1);
				stmt.executeUpdate("UPDATE books SET NumberOfAvailabeCopies = '" + nOAC + "' WHERE Serial = '" + serial + "';");
			}

			rs = stmt.executeQuery("SELECT * FROM renthistory;");
			rs.last();
			String nextRowNum = String.valueOf(rs.getRow());

			int affectedRows;
			affectedRows = stmt.executeUpdate("INSERT INTO renthistory VALUES ('" + nextRowNum			+ "', '" +
																					serial				+ "', '" +
																					userID				+ "', '" +
																					book.getName()		+ "', '" +
																					rentDate			+ "', '" +
																					returnDate			+ "', NULL, '" + ((InOAC)==0?"1":"0") + "');");

			pipe.setData(true);
			this.sendToAllClients(pipe);


		}
		catch(SQLException e) {
			System.out.println("attemptRentBook: SQLException");
			e.printStackTrace();
			pipe.setData(false);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			System.out.println("attemptRentBook: Reg exception");
			e.printStackTrace();
			pipe.setData(false);
			this.sendToAllClients(pipe);
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ FETCHING DATA ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ALL USERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("fetchReturnData")) {

		System.out.println("in fetchReturnData");

		Statement stmt;

		try {
			stmt = conn.createStatement();

			String userID		= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);
			String bookSerial	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);

			ResultSet rs = null;

			rs = stmt.executeQuery("SELECT * FROM renthistory WHERE UserID = '" + userID + "' AND BookSerial = '" + bookSerial + "' AND DateOfRealReturn IS NULL;");
			rs.last();
			if(rs.getRow() == 0) {//If no books were found:
				System.out.println("Found nothing");
				pipe.setData(null);
				this.sendToAllClients(pipe);
				return;
			}
			System.out.println("Found something");

			rs.first();
			ArrayList<Object> fetchedData = new ArrayList<Object>();

			fetchedData.add(rs.getString(1));	//RowNumber
			fetchedData.add(rs.getString(2));	//Book Serial
			fetchedData.add(rs.getString(3));	//User ID
			fetchedData.add(rs.getString(4));	//Book Name
			fetchedData.add(rs.getDate(5));		//Date Of Rent

			System.out.println(fetchedData);

			pipe.setData(fetchedData);
			this.sendToAllClients(pipe);
			return;
		}
		catch(SQLException e) {
			System.out.println("fetchReturnData: SQLException");
			e.printStackTrace();
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}




	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RETURN A BOOK ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("returnABook")) {

		System.out.println("in returnABook");

		Statement stmt;

		try {
			stmt = conn.createStatement();

	    	//get status->check if "frozen", if yes: check closed rents by this user have 2 or more time late->if yes: change status to "locked" | if not: change status to "active"
	    	// now finish the rent with adding todays date to RealReturnDate, and go to order table. check with BookSerial if an order is waiting. the first one will receive it.
	    	// receive it means: mail to user, add date of (today+2 days) to BookIsReadyUntilDate and finish I think :]

			String userID				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);								//User ID
	    	String serial				= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);
	    	java.sql.Date todaysDate	= (java.sql.Date)((ArrayList<Object>)((Pipe)msg).getData()).get(2);

	    	ResultSet rs = null;
	    	rs = stmt.executeQuery("SELECT Status FROM users WHERE ID = '" + userID + "';");

	    	rs.first();
			String status = rs.getString(1);

			if(status.toLowerCase().equals("frozen")) {
				rs=stmt.executeQuery("SELECT COUNT(*) FROM renthistory WHERE UserID = '" + userID + "' AND DateOfRealReturn IS NOT NULL AND ((DateOfRealReturn-DateOfReturn)>0);");
				rs.first();
				status = (rs.getInt(1) >= 2) ? "locked" : "active";
				stmt.executeUpdate("UPDATE users SET Status = '" + status + "' WHERE ID = '" + userID + "';");
			}

			stmt.executeUpdate("UPDATE renthistory SET DateOfRealReturn = '" + todaysDate + "' WHERE UserID = '" + userID + "' AND BookSerial = '" + serial + "' AND DateOfRealReturn IS NULL;");



			rs = null;
			// Now we done with the rent and check if there is an order to this book
			//rs = stmt.executeQuery("SELECT * FROM order WHERE BookIsReadyUntilDate IS NULL AND BookSerial = '" + serial + "';");
			System.out.println("Serial: " + serial);

			rs = stmt.executeQuery("SELECT * FROM order2 WHERE BookIsReadyUntilDate IS NULL AND BookSerial = '" + serial + "';");
			//System.out.println("rs.getInt(1): 1064" + rs.getInt(1));
			rs.last();
			//int rowNum = rs.getInt(1);
			ResultSet rs2 = null;
			if(rs.getRow() == 0) {
				//No one ordered the book
				rs.first();
				rs2 = stmt.executeQuery("SELECT NumberOfAvailabeCopies FROM books WHERE Serial = '" + serial + "';");
				rs2.first();

				int nOAC = (Integer.parseInt(rs2.getString(1)) + 1);

				stmt.executeUpdate("UPDATE books SET NumberOfAvailabeCopies = '" + String.valueOf(nOAC) + "' WHERE Serial = '" + serial + "';");
			}
			else{
				int rowNum = rs.getInt(1);
				rs.first();
				String id = rs.getString(3);	//getUserID;
				rs2 = stmt.executeQuery("SELECT Email, FirstName FROM users WHERE ID = '" + id + "';");
				rs2.first();
				String emailAddress = rs2.getString(1);
				String FirstName = rs2.getString(2);
				rs2 = stmt.executeQuery("SELECT BookName FROM books WHERE Serial = '" + serial + "';");
				rs2.first();
				String bookName = rs2.getString(1);
				SendEmail.sendEmail(emailAddress, "OBL Mail System: Your book order is ready!", "Dear " + FirstName + ", \n Your book order - " + bookName + " is waiting for you in the library for the next 2 days. \n Please come rent it on time. \n Thanks in advance - OBL TEAM");
				LocalDate date=LocalDate.now().plusDays(2);
				java.sql.Date DayAfterTomorrow=java.sql.Date.valueOf(date);
				stmt.executeUpdate("UPDATE order2 SET BookIsReadyUntilDate = '" + DayAfterTomorrow + "' WHERE RowNum = '" + rowNum + "';");
			}
			rs2.close();
			rs.close();

			pipe.setData(status);
			this.sendToAllClients(pipe);
			return;

		}
		catch(SQLException e) {
			System.out.println("returnABook: SQLException");
			e.printStackTrace();
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOAD Employees ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("loadEmployees")) {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM employee;");

			ManyNonStaticEmployees employees = new ManyNonStaticEmployees();	//DATA

			while(rs.next()){
				NonStaticEmployee newemployee = new NonStaticEmployee();

				newemployee.setID(			rs.getString(1));
				newemployee.setRoll(		rs.getString(2));
				newemployee.setPass(		rs.getString(3));
				newemployee.setFirstName(	rs.getString(4));
				newemployee.setLastName(	rs.getString(5));
				newemployee.setEmail(		rs.getString(6));
				newemployee.setDepartment(	rs.getString(7));
				newemployee.setBirthdate(	rs.getDate(8));


				System.out.println("Found book!: " + newemployee.getID() + " " + newemployee.getFirstName());

				employees.addEmployee(newemployee);
			}
			rs.close();
			pipe.setData(employees);
			System.out.println(employees);
			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("loadStudents SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("loadStudents exception");
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Order A Book ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ STUDENT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("attemptOrderBook")) {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			String bookSerial	= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(0);
			String userID		= (String)((ArrayList<Object>)((Pipe)msg).getData()).get(1);

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM order2;");
			rs.last();
			String nextRowNum = String.valueOf(rs.getRow() +1);

			int affectedRows;
			affectedRows = stmt.executeUpdate("INSERT INTO order2 VALUES ('" + 	nextRowNum			 + "', '" +
																				bookSerial			 + "', '" +
																				userID				 + "', NULL);");

			pipe.setData(true);
			this.sendToAllClients(pipe);


			rs.close();
			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("loadStudents SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("loadStudents exception");
		}
	}



	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOAD STUDENTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("loadStudents")) {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM users;");

			ManyNonStaticStudents students = new ManyNonStaticStudents();	//DATA

			while(rs.next()){
				NonStaticUser newUser = new NonStaticUser();

				newUser.setID(					rs.getString(1));
				newUser.setPass(				rs.getString(2));
				newUser.setFirstName(			rs.getString(3));
				newUser.setLastName(			rs.getString(4));
				newUser.setEmail(				rs.getString(5));
				newUser.setPhoneNumber(			rs.getString(6));
				newUser.setBirthdate(			rs.getDate(7));
				newUser.setFaculty(				rs.getString(8));
				newUser.setGender(				rs.getString(9));
				newUser.setGetUpdatesToEmail(	rs.getString(10));
				newUser.setGetUpdatesToPhone(	rs.getString(11));
				newUser.setStatus(				rs.getString(12));


				System.out.println("Found Student!: " + newUser.getID() + " " + newUser.getFirstName());

				students.addUser(newUser);
			}
			rs.close();
			pipe.setData(students);
			System.out.println(students);
			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("loadStudents SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("loadStudents exception");
		}
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ VIEW RENTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("viewRents")) {

		Statement stmt;
		try {
			stmt = conn.createStatement();

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM renthistory WHERE DateOfRealReturn IS NOT NULL;");

			ManyRents manyRents = new ManyRents();	//DATA

			while(rs.next()){
				Rent rent = new Rent();

				//							rs.getString(1) is Row Number
				rent.setSerial(				rs.getString(2));
				rent.setID(					rs.getString(3));
				rent.setName(				rs.getString(4));
				rent.setDateOfRent(			rs.getDate(5));
				rent.setDateOfReturn(		rs.getDate(6));
				rent.setDateOfRealReturn(	rs.getDate(7));
				rent.setIsPopular(			rs.getString(8));

				System.out.println("Found rent!: " + rent.getSerial() + " " + rent.getID());

				manyRents.addRent(rent);
			}
			rs.close();
			pipe.setData(manyRents);
			System.out.println(manyRents);
			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("loadStudents SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("loadStudents exception");
		}
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CREATE NEW PERIODIC REPORT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LIBRARIAN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if(command.equals("createNewReport")) {


		// all users, all renthistory, NumberOfCopies from books

		Statement stmt;
		try {
			stmt = conn.createStatement();

			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT Status FROM users;");

			ArrayList<String> statusList = new ArrayList<String>();	//all users
			int NumberOfLateReturns;	//all renthistory
			int numOfCopies = 0;

			while(rs.next()){
				String stats = rs.getString(1);
				statusList.add(stats);
			}

			System.out.println("statusList: " + statusList);
			
			rs = stmt.executeQuery("SELECT COUNT(DISTINCT UserID) FROM renthistory WHERE DateOfRealReturn IS NOT NULL AND ((DateOfRealReturn-DateOfReturn)>0);");
			rs.next();
			NumberOfLateReturns = rs.getInt(1);	//get number of late returns


			rs = stmt.executeQuery("SELECT NumberOfCopies from books;");
			while(rs.next())
				numOfCopies += Integer.parseInt(rs.getString(1));


			//SEND ALL THE DATA BACK TO THE CLIENT
			ArrayList<Object> data = new ArrayList<Object>();
			data.add(statusList);
			data.add(String.valueOf(NumberOfLateReturns));
			data.add(String.valueOf(numOfCopies));

			rs.close();
			pipe.setData(data);
			System.out.println(data);
			this.sendToAllClients(pipe);

		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("createNewReport SQLException");
			pipe.setData(null);
			this.sendToAllClients(pipe);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("createNewReport exception");
		}
	}









    else {
    	System.out.println("Server:");
    	System.out.println("Elsed me Server");
    }

	  this.sendToAllClients(msg);
	  System.out.println("inside load my books3");
	}


  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }

  //Class methods ***************************************************

  /**
   * This method is responsible for the creation of
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555
   *          if no argument is entered.
   */
  public static void main(String[] args)
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }

    EchoServer sv = new EchoServer(port);

    try
    {
    	System.out.println("Trying SV.LISTEN");
      sv.listen(); //Start listening for connections
    }
    catch (Exception ex)
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
