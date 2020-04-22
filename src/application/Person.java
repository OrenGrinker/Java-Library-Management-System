package application;

import java.sql.Date;
//import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class Person {//1996,07, 18
//	private LocalDate localDate2 = LocalDate.of(2015, 12, 31);
	private Date birth=Date.valueOf("2014-02-14");
	
	/*private SimpleStringProperty Name;
	private SimpleStringProperty A=new SimpleStringProperty("hey");
	private SimpleStringProperty B=new SimpleStringProperty("hey");
	private SimpleStringProperty C=new SimpleStringProperty("hey");
	*/
	private String Name;
	private String A=new String("a");
	private String B=new String("b");
	private String C=new String("c");
	
 public Person(String name)
 { this.Name=new String(name);
	//	 birth=parseDate("2014-02-14");
 }
 public String getName()
 {return Name;}//.get();}
 
 public String getA()
 {return A;}//.get();}
 
 public String getB()
 {return B;}//.get();}
 
 public String getC()
 {return C;}//.get();}
 
 public Date getBirth()
 {return birth;}
 
 public void setBirth(Date birth) {
		this.birth = birth;
	}
}
