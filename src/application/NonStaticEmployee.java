package application;

import java.util.Date;
import java.io.Serializable;
import java.sql.*;

/**
 * Class for employee.
 * @author OBL_Team13
 */
public class NonStaticEmployee implements Serializable {

	private String ID = "0000";
	private String Roll = "rolling";	
	private String Pass = "123";
	private String FirstName = "John";
	private String LastName = "Corvette";
	private String Email = "12@hotFemail.com";
	private String Department = "dept";
	private java.sql.Date Birthdate;

	/**
	 * Create an employee.
	 */
	public NonStaticEmployee(){
		reSet();
	}

	public String getRoll() {
		return Roll;
	}

	public void setRoll(String roll) {
		Roll = roll;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public java.sql.Date getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(java.sql.Date birthdate) {
		Birthdate = birthdate;
	}

	/**
	 * Reset an employee.
	 */
	public void reSet() {
		ID = null;
		Roll = null;		
		Pass = null;
		FirstName = null;
		LastName = null;
		Email = null;
		Department = null;
	}
	
	/**
	 * Create a string for employee with ID and password.
	 */
	public String toString() {
		return "U: " + ID + "P: " + Pass;
	}
	
}

