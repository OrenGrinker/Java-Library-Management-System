package application;

import java.util.ArrayList;

/**
 * Get date for returning a book.
 * @author OBL_Team13
 *
 */
public class fetchedDateForReturnABook {
	
	public static boolean isLoaded = false;
	private static String rowNumber;
	private static String bookSerial;
	private static String userID;
	private static String bookName;
	private static java.sql.Date dateOfRent;
	
	/**
	 * Set data for book return.
	 * @param data	object type that hold return book information.
	 */
	public static void setData(ArrayList<Object> data) {
		rowNumber	= (String)data.get(0);
		bookSerial	= (String)data.get(1);
		userID		= (String)data.get(2);
		bookName	= (String)data.get(3);
		dateOfRent	= (java.sql.Date)data.get(4);
		isLoaded = true;
	}
	
	/**
	 * Get data of book return.
	 * @return	returns an object type that hold return book information.
	 */
	public static ArrayList<Object> getData(){
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(rowNumber);
		data.add(bookSerial);
		data.add(userID);
		data.add(bookName);
		data.add(dateOfRent);
		return data;
	}
	
}
