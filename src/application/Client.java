package application;

import runUs.ClientConsole;
import application.*;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Client class to represent a client.
 * @author OBL_Team13
 *
 */
public class Client {

	private Stage window;

	private static Connection conn;

	private static ClientConsole clientConsole;

	private static ClientConsole chat;
	private static String host;
	private static int port;

	/**
	 * Create a client with servers host and port.
	 * @param host	IP of server.
	 * @param port	port to connect to server.
	 */
	public Client(String host, int port) {
		this.host = host;
		this.port = port;

		try {
			chat = new ClientConsole(host, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 	}

	/**
	 * Searching book in DB with bookName.
	 * @param bookName	book name for search.
	 */
	public static void SearchByBookName_S(String bookName) {
		System.out.println("Searching by bookName: " + bookName);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByBookName_S", bookName));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by book name", "okay"));
			e.printStackTrace();
		}
	}

	/**
	 * Searching book in DB with authorName.
	 * @param authorName	author name for search.
	 */
	public static void SearchByAuthorName_S(String authorName) {
		System.out.println("Searching by authorName: " + authorName);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByAuthorName_S", authorName));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by author name", "okay"));
			e.printStackTrace();
		}
	}

	/**
	 * Searching book in DB with book topic.
	 * @param bookTopic	book topic for search.
	 */
	public static void SearchByBookTopic_S(String bookTopic) {
		System.out.println("Searching by bookTopic: " + bookTopic);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByBookTopic_S", bookTopic));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by book topic", "okay"));
			e.printStackTrace();
		}
	}

	/**
	 * Searching book in DB with freeText.
	 * @param freeText	free text for search.
	 */
	public static void SearchByFreeText_S(String freeText) {
		System.out.println("Searching by freeText: " + freeText);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByFreeText_S", freeText));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by free text", "okay"));
			e.printStackTrace();
		}
	}

	public static void SearchByBookName_L(String bookName) {
		System.out.println("Searching by bookName: " + bookName);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByBookName_L", bookName));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by book name", "okay"));
			e.printStackTrace();
		}
	}

	public static void SearchByAuthorName_L(String authorName) {
		System.out.println("Searching by authorName: " + authorName);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByAuthorName_L", authorName));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by author name", "okay"));
			e.printStackTrace();
		}
	}

	public static void SearchByBookTopic_L(String bookTopic) {
		System.out.println("Searching by bookTopic: " + bookTopic);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByBookTopic_L", bookTopic));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by book topic", "okay"));
			e.printStackTrace();
		}
	}

	public static void SearchByFreeText_L(String freeText) {
		System.out.println("Searching by freeText: " + freeText);
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SearchByFreeText_L", freeText));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to search by free text", "okay"));
			e.printStackTrace();
		}
	}

	/**
	 * Try log in with credentials.
	 * @param username	the ID for log in.
	 * @param password	the password for log in.
	 */
	public static void AttemptLogin(String username, String password) {

		System.out.println("Attempting to login with Username: " + username + " Password: " + password);

		ArrayList<String> data = new ArrayList<String>();
		data.add(username);
		data.add(password);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("AttemptLogin", data));
		}
		catch(Exception e){
			Platform.runLater(new AlertBox(1, "Error", "Unable to Login", "okay"));
			e.printStackTrace();
		}
	}

	/**
	 * Save changes student make in profile.
	 * @param email			email of student.
	 * @param phone			phone of student.
	 * @param newPassword	new password for student.
	 */
	public static void SaveChanges_S(String email, String phone, String newPassword) {
		ArrayList<String> data = new ArrayList<String>();

		data.add(User.getID());
		data.add(email);
		data.add(phone);
		if(newPassword.isEmpty())
			data.add(User.getPass());
		else
			data.add(newPassword);

		System.out.println("SaveChanges_S: " + data);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SaveChanges_S", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save changes librarian make in profile.
	 * @param newPassword	new password for librarian.
	 */
	public static void SaveChanges_L(String newPassword) {

		ArrayList<String> data = new ArrayList<String>();

		data.add(Employee.getID());
		if(newPassword.isEmpty())
			data.add(User.getPass());
		else
			data.add(newPassword);

		System.out.println("SaveChanges_L: " + data);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SaveChanges_L", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save changes librarian make in student profile.
	 * @param firstNameTextField	first name of student.
	 * @param lastNameTextField		last name of student.
	 * @param emailTextField		email of student.
	 * @param IDNumberTextField		ID of student.
	 * @param passwordTextField		password of student.
	 * @param phoneNumber			phone number of student.
	 * @param dateAsString			birthday of student.
	 * @param faculty				faculty of student.
	 * @param gender				gender of student.
	 * @param emailCheck			mark email updates for student.
	 * @param phoneCheck			mark SMS updates for student.
	 * @param status				status of student.
	 */
	public static void SaveChanges_LS(
										String firstNameTextField,
										String lastNameTextField,
										String emailTextField,
										String IDNumberTextField,
										String passwordTextField,
										String phoneNumber,
										java.sql.Date dateAsString,
										String faculty,
										String gender,
										String emailCheck,
										String phoneCheck,
										String status) {

		ArrayList<Object> data = new ArrayList<Object>();

		data.add(firstNameTextField);
		data.add(lastNameTextField);
		data.add(emailTextField);
		data.add(IDNumberTextField);
		data.add(passwordTextField);
		data.add(phoneNumber);
		data.add(dateAsString);
		data.add(faculty);
		data.add(gender);
		data.add(emailCheck);
		data.add(phoneCheck);
		data.add(status);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("SaveChanges_LS", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create a new student account in OBL.
	 * @param firstNameTextField	first name of new student.
	 * @param lastNameTextField		last name of new student.
	 * @param emailTextField		email of new student.
	 * @param IDNumberTextField		ID of new student.
	 * @param passwordTextField		password of new student.
	 * @param phoneNumber			phone number of new student.
	 * @param dateAsString			birthday of new student.
	 * @param faculty				faculty of new student.
	 * @param gender				gender of new student.
	 * @param emailCheck			mark email updates for new student.
	 * @param phoneCheck			mark SMS updates for new student.
	 */
	public static void CreateNewAccount(
										String firstNameTextField,
										String lastNameTextField,
										String emailTextField,
										String IDNumberTextField,
										String passwordTextField,
										String phoneNumber,
										java.sql.Date dateAsString,
										String faculty,
										String gender,
										boolean emailCheck,
										boolean phoneCheck) {

		ArrayList<Object> data = new ArrayList<Object>();

		data.add(firstNameTextField);
		data.add(lastNameTextField);
		data.add(emailTextField);
		data.add(IDNumberTextField);
		data.add(passwordTextField);
		data.add(phoneNumber);
		data.add(dateAsString);
		data.add(faculty);
		data.add(gender);
		data.add(emailCheck?"1":"0");
		data.add(phoneCheck?"1":"0");

		System.out.println("Creating New Account: " + data);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("CreateNewAccount", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create a new book in OBL.
	 * @param Serial			serial number of new book.
	 * @param Name				name of new book.
	 * @param Author			author of new book.
	 * @param Edition			edition of new book.
	 * @param PrintingDate		print date of new book.
	 * @param Subject			subject of new book.
	 * @param Description		description of new book.
	 * @param PurchasedDate		purchase date of new book.
	 * @param Shelf				shelf location of new book.
	 * @param TableOfContents	table of contents of new book.
	 * @param NumberOfCopies	amount of copies of new book.
	 */
	public static void CreateNewBook(	String Serial,
										String Name,
										String Author,
										String Edition,
										java.sql.Date PrintingDate,
										String Subject,
										String Description,
										java.sql.Date PurchasedDate,
										String Shelf,
										byte[] fileData,
										String NumberOfCopies) {




		ArrayList<Object> data = new ArrayList<Object>();

		data.add(Serial);
		data.add(Name);
		data.add(Author);
		data.add(Edition);
		data.add(PrintingDate);
		data.add(Subject);
		data.add(Description);
		data.add(PurchasedDate);
		data.add(Shelf);
		data.add(fileData);
		data.add(NumberOfCopies);

		System.out.println("Create New Book: " + data);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("CreateNewBook", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Edits an existing book in OBL.
	 * @param Serial			serial number of book.
	 * @param Name				name of book.
	 * @param Author			author of book.
	 * @param Edition			edition of book.
	 * @param PrintingDate		print date of book.
	 * @param Subject			subject of book.
	 * @param Description		description of book.
	 * @param PurchasedDate		purchase date of book.
	 * @param Shelf				shelf location of book.
	 * @param TableOfContents	table of contents of book.
	 * @param NumberOfCopies	amount of copies of book.
	 */
	public static void EditABook(	String Serial,
									String Name,
									String Author,
									String Edition,
									java.sql.Date PrintingDate,
									String Subject,
									String Description,
									java.sql.Date PurchasedDate,
									String Shelf,
									byte[] pdfData,
									String NumberOfCopies) {

		ArrayList<Object> data = new ArrayList<Object>();

		data.add(Serial);
		data.add(Name);
		data.add(Author);
		data.add(Edition);
		data.add(PrintingDate);
		data.add(Subject);
		data.add(Description);
		data.add(PurchasedDate);
		data.add(Shelf);
		data.add(pdfData);
		data.add(NumberOfCopies);


		System.out.println("Edit A Book: " + data);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("EditABook", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * Delete a book from OBL.
	 * @param selectedBook	book to be deleted.
	 */
	public static void removeABook(Book selectedBook) {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("RemoveABook", selectedBook.getSerial()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load a list all books ever rented by student.
	 */
	public static void loadMyBooks() {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadMyBooks", User.getID()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load a list of currently rented books by student.
	 */
	public static void loadMyCurrentBooks() {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadMyCurrentBooks", User.getID()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

public static void CurrentBooksByLibrarian() {

	try {
		chat.client.openConnection();
		chat.client.sendToServer(new Pipe("CurrentBooksByLibrarians", User.getID()));
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}


/**
 * Load a list of books.
 */
	public static void loadBooks() {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadBooks", "deleteMe"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load a book by serial number.
	 * @param serial	serial number of book.
	 */
	public static void loadBookBySerial(String serial) {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadBookBySerial", serial));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load a student by ID.
	 * @param UserID	ID of student.
	 */
	public static void loadUser(String UserID) {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadUser", UserID));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rent a book by librarian for student.
	 * @param UserID		ID of student.
	 * @param BookSerial	serial number of book.
	 * @param rentDate		start date of rent.
	 * @param returnDate	return date of rent.
	 */
	public static void attemptRentBook(String UserID, String BookSerial, java.sql.Date rentDate, java.sql.Date returnDate) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(UserID);
		data.add(BookSerial);
		data.add(rentDate);
		data.add(returnDate);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("attemptRentBook", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get data of specific rent.
	 * @param UserID		ID of student.
	 * @param BookSerial	serial number of book.
	 */
	public static void fetchReturnData(String UserID, String BookSerial) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(UserID);
		data.add(BookSerial);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("fetchReturnData", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Return a book by librarian that was rented by student.
	 * @param data	object type that hold information about rented book by student.
	 */
	public static void returnABook(ArrayList<Object> data) {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("returnABook", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load a list of students in OBL.
	 */
	public static void loadStudents() {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadStudents", "deleteMe"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load a list of employees in OBL.
	 */
	public static void loadEmployees() {
		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("loadEmployees", "deleteMe"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Order a book by student.
	 * @param BookSerial	serial number of book.
	 * @param UserID		ID of student.
	 */
	public static void attemptOrderBook(String BookSerial, String UserID) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(BookSerial);
		data.add(UserID);

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("attemptOrderBook", data));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}



	/**
	 * View a report about rents in OBL.
	 */
	public static void viewRents() {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("viewRents", "deleteMe"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void UpdateReturnDate(Rent r) {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("UpdateReturnDate", r));
		}
		catch(Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * Create a new periodic report in OBL.
	 */
	public static void createNewReport() {

		try {
			chat.client.openConnection();
			chat.client.sendToServer(new Pipe("createNewReport", "deleteMe"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}













}
