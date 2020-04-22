package application;

import java.util.Date;
import java.io.Serializable;
import java.sql.*;

/**
 * Employee Class to represent employees.
 * @author OBL_Team13
 *
 */
public class Employee implements Serializable {

	private static String ID = "0000";
	private static String Roll = "rolling";	
	private static String Pass = "123";
	private static String FirstName = "John";
	private static String LastName = "Corvette";
	private static String Email = "12@hotFemail.com";
	private static String Department = "dept";
	private static java.sql.Date Birthdate;

	/**
	 * Constructor to create an empty employee.
	 */
	public Employee(){
		reSet();
	}

	public static String getRoll() {
		return Roll;
	}

	public static void setRoll(String roll) {
		Roll = roll;
	}

	public static String getID() {
		return ID;
	}

	public static void setID(String iD) {
		ID = iD;
	}

	public static String getPass() {
		return Pass;
	}

	public static void setPass(String pass) {
		Pass = pass;
	}

	public static String getFirstName() {
		return FirstName;
	}

	public static void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public static String getLastName() {
		return LastName;
	}

	public static void setLastName(String lastName) {
		LastName = lastName;
	}

	public static String getEmail() {
		return Email;
	}

	public static void setEmail(String email) {
		Email = email;
	}

	public static String getDepartment() {
		return Department;
	}

	public static void setDepartment(String department) {
		Department = department;
	}

	public static java.sql.Date getBirthdate() {
		return Birthdate;
	}

	public static void setBirthdate(java.sql.Date birthdate) {
		Birthdate = birthdate;
	}

	/**
	 * Reset the employee.
	 */
	public static void reSet() {
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

