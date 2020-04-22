package application;

import java.sql.Date;
import java.io.Serializable;
import java.sql.*;

/**
 * Book class to represent books.
 * @author OBL_Team13
 *
 */
public class Book implements Serializable{

	private String Serial;
	private String Name;
	private String Author;
	private String Generation;
	private Date PrintedDate;
	private String Category;
	private String Description;
	private String NumberOfCopies;
	private Date DateOfPurchase;
	private String PlaceOnShelf;
	private byte[] TableOfContentAsPdf;
	private String NumberOfAvailabeCopies;
	
	/**
	 * Constructor to create an empty book.
	 */
	public Book()
	{
		Serial = null;
		Name = null;
		Author = null;
		Generation = null;
		PrintedDate = null;
		Category = null;
		Description = null;
		NumberOfCopies = null;
		DateOfPurchase = null;
		PlaceOnShelf = null;
		TableOfContentAsPdf = null;
		NumberOfAvailabeCopies = null;
	}
	
	public String getSerial() {
		return Serial;
	}

	public void setSerial(String serial) {
		Serial = serial;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getAuthor() {
		return Author;
	}
	
	public void setAuthor(String author) {
		Author = author;
	}
	
	public String getGeneration() {
		return Generation;
	}
	
	public void setGeneration(String generation) {
		Generation = generation;
	}
	
	public java.sql.Date getPrintedDate() {
		return PrintedDate;
	}
	
	public void setPrintedDate(java.sql.Date printedDate) {
		PrintedDate = printedDate;
	}
	
	public String getCategory() {
		return Category;
	}
	
	public void setCategory(String category) {
		Category = category;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getNumberOfCopies() {
		return NumberOfCopies;
	}
	
	public void setNumberOfCopies(String numberOfCopies) {
		NumberOfCopies = numberOfCopies;
	}
	public String getNumberOfAvailabeCopies() {
		return NumberOfAvailabeCopies;
	}
	
	public void setNumberOfAvailabeCopies(String numberOfAvailabeCopies) {
		NumberOfAvailabeCopies = numberOfAvailabeCopies;
	}
	
	public java.sql.Date  getDateOfPurchase() {
		return DateOfPurchase;
	}
	
	public void setDateOfPurchase(java.sql.Date  dateOfPurchase) {
		DateOfPurchase = dateOfPurchase;
	}
	
	public String getPlaceOnShelf() {
		return PlaceOnShelf;
	}
	
	public void setPlaceOnShelf(String placeOnShelf) {
		PlaceOnShelf = placeOnShelf;
	}
	
	public byte[] getTableOfContentAsPdf() {
		return TableOfContentAsPdf;
	}
	
	public void setTableOfContentAsPdf(byte[] tableOfContentAsPdf) {
		TableOfContentAsPdf = tableOfContentAsPdf;
	}
	
	/**
	 * Overriding toString to show book serial and book name.
	 */
	@Override
	public String toString() {
		return Serial.toString() + " " + Name.toString();
	}
	
}
