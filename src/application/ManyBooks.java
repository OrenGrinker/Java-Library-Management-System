package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.sql.*;

/**
 * Class that hold a list of books.
 * @author OBL_Team13
 *
 */
public class ManyBooks implements Serializable{

	private ArrayList<Book> manyBooks;
	private static ArrayList<Book> myStaticBooks = new ArrayList<Book>();
	public static boolean isLoaded = false;
	public static String lookForThisBookBySerial = new String();

	/**
	 * Constructor of ManyBooks.
	 */
	public ManyBooks() {
		manyBooks = new ArrayList<Book>();
		isLoaded = false;
	}

	/**
	 * Constructor of ManyBooks initialized with existing list of books.
	 * @param manyBooks	List of books
	 */
	public ManyBooks(ArrayList<Book> manyBooks) {
		myStaticBooks = manyBooks;
		isLoaded = false;
	}


	/**
	 * Add book to ManyBooks.
	 * @param book	book type.
	 */
	public void addBook(Book book) {
		manyBooks.add(book);
	}

	/**
	 * Add book to a static ManyBooks.
	 * @param book	book type.
	 */
	public static void addBookToStatic(Book book) {
		for(Book bok : myStaticBooks)
			if(bok.getSerial().equals(book.getSerial()))
				return;
		myStaticBooks.add(book);
	}

	/**
	 * Add an existing list of books to a static ManyBooks.
	 * @param arrBook	a list of book type.
	 */
	public static void addLibraryToStatic(ArrayList<Book> arrBook) {
		for(Book book : arrBook)
			myStaticBooks.add(book);
	}
	
	/**
	 * Add an existing ManyBooks to a static ManyBooks.
	 * @param arrBook	a ManyBooks list type.
	 */
	public static void addLibraryToStatic(ManyBooks arrBook) {

		for (Book book : arrBook.getAllBooks())
			myStaticBooks.add(book);
	}


	/**
	 * Remove a book from ManyBooks.
	 * @param book	book type.
	 */
	public void removeBook(Book book) {
		manyBooks.remove(book);
	}

	/**
	 * Remove a book from a static ManyBooks.
	 * @param book	book type.
	 */
	public static void removeBookFromStatic(Book book) {
		myStaticBooks.remove(book);
	}

	public static void clearStaticbooks() {
		myStaticBooks.clear();
	}


	/**
	 * Get ManyBooks.
	 * @return	ManyBooks.
	 */
	public ArrayList<Book> getAllBooks(){
		return manyBooks;
	}

	/**
	 * Get a static ManyBooks.
	 * @return	static ManyBooks.
	 */
	public static ArrayList<Book> getAllStaticBooks(){
		return myStaticBooks;
	}

	/**
	 * Get a book from static ManyBooks by serial number.
	 * @param serial	serial number of book.
	 * @return			book type.
	 */
	public static Book getStaticBook(String serial){
		for(Book book : myStaticBooks)
			if(book.getSerial().equals(serial))
				return book;
		return null;
	}
	
	/**
	 * Create a string for ManyBooks.
	 */
	@Override
	public String toString() {
		return manyBooks.toString();
	}

}
