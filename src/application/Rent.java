package application;

import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.Date;
import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class for rent.
 * @author OBL_Team13.
 */
public class Rent implements Serializable {

	//private String Email=null;
	private String Serial;
	private String Name;
	private String ID;
	private Date DateOfRent;
	private Date DateOfReturn;
	private Date DateOfRealReturn;
	private String isPopular;
	public static int mode = 0;
	public static String lookForThisBook = "";
	
	/**
	 * Constructor for with initialized name of book.
	 * @param name	name of book as string.
	 */
	public Rent(String name) {		
	    Serial=new String("a");
		Name=new String(name);
		ID=new String("c");
		DateOfRent=Date.valueOf("2014-02-14");
		DateOfReturn=Date.valueOf("2014-02-15");
		DateOfRealReturn=Date.valueOf("2014-02-16");
		isPopular = "false";
	};
	
	/**
	 * Constructor for rent initialized with default parameters.
	 */
	public Rent() {
	    Serial=new String("a");
		Name=new String("b");
		ID=new String("c");
		DateOfRent=Date.valueOf("2014-02-14");
		DateOfReturn=Date.valueOf("2014-02-14");
		DateOfRealReturn=Date.valueOf("2014-02-14");
		isPopular = "false";
	};
	
	/**
	 * Constructor of rent initialized with all rent information.
	 * @param serial			serial number of book.
	 * @param name				name of book.
	 * @param id				ID of student.
	 * @param rentDate			rent date.
	 * @param returnDate		date of expected return.
	 * @param RealReturnDate	date of actual return.
	 * @param isPopular			decide if rent of popular book.
	 */
	public Rent(String serial,String  name,String id, Date rentDate,Date returnDate,Date RealReturnDate, String isPopular) {
		this.Serial=new String(serial) ;
		this.Name=new String(name);
		this.ID=new String(id);
		DateOfRent=rentDate;
		DateOfReturn=returnDate;
		DateOfRealReturn= RealReturnDate;
		this.isPopular = isPopular;
	};
	
	public String getIsPopular()
	 {return isPopular;}//.get();}
	 
	 public void setIsPopular(String isPopular)
	 {this.isPopular = isPopular;}
	
	 public String getSerial()
	 {return Serial;}//.get();}
	 
	 public void setSerial(String serial)
	 {this.Serial = serial;}
	 
	 public String getName()
	 {return Name;}//.get();}
	 
	 public void setName(String name)
	 {this.Name = name;}
	 
	 public String getID()
	 {return ID;}//.get();}
	 
	 public void setID(String id)
	 {this.ID = id;}
	 
	 public Date getDateOfRent()
	 {return DateOfRent; }
	 
	 public void setDateOfRent(Date dateOfRent)
	 {this.DateOfRent = dateOfRent;}
	 
	 public Date getDateOfReturn()
	 {return DateOfReturn; }
	
	 public void setDateOfReturn(Date dateOfReturn)
	 {this.DateOfReturn = dateOfReturn;}
	 
	 public Date getDateOfRealReturn()
	 {return DateOfRealReturn; }
	 
	 public void setDateOfRealReturn(Date dateOfRealReturn)
	 {this.DateOfRealReturn = dateOfRealReturn;}
	 
	 /**
	  * Create a string for rent with name of book.
	  */
	 @Override
		public String toString() {
			return Serial.toString() + " " + Name.toString();
		}
	 
	 /*
	 public void setEmail(String adress)
	 {
		 Email=adress;
	 }
	 
	 
	 public String getEmail()
	 {return Email;}//.get();}
	
	 */
}
