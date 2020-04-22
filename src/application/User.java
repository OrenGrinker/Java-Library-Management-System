package application;

import java.util.Date;
import java.io.Serializable;
import java.sql.*;

/**
 * Class for Student.
 * @author OBL_Team13
 */
public class User implements Serializable {
	
	public static boolean isLoaded = false;
	public static String lookForThisUserByID = new String();
	private static String iD = "0000";
	private static String pass = "1111";
	private static String firstName = "Aa";
	private static String lastName = "Zz";
	private static String email = "A@mail.com";
	private static String phoneNumber = "012";
	private static java.sql.Date birthdate;
	private static String faculty = "fac";
	private static String gender = "gen";
	private static String getUpdatesToEmail = "1";
	private static String getUpdatesToPhone = "1";
	private static String status = "active";
	
	public static String getID() {
		return iD;
	}

	public static void setID(String iiD) {
		iD = iiD;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String ipass) {
		pass = ipass;
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String ifirstName) {
		firstName = ifirstName;
	}

	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String ilastName) {
		lastName = ilastName;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String iemail) {
		email = iemail;
	}

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(String iphoneNumber) {
		phoneNumber = iphoneNumber;
	}

	public static java.sql.Date getBirthdate() {
		return birthdate;
	}

	public static void setBirthdate(java.sql.Date ibirthdate) {
		birthdate = ibirthdate;
	}

	public static String getFaculty() {
		return faculty;
	}

	public static void setFaculty(String ifaculty) {
		faculty = ifaculty;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String igender) {
		gender = igender;
	}

	public static String getGetUpdatesToEmail() {
		return getUpdatesToEmail;
	}

	public static void setGetUpdatesToEmail(String igetUpdatesToEmail) {
		getUpdatesToEmail = igetUpdatesToEmail;
	}

	public static String getGetUpdatesToPhone() {
		return getUpdatesToPhone;
	}

	public static void setGetUpdatesToPhone(String igetUpdatesToPhone) {
		getUpdatesToPhone = igetUpdatesToPhone;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String istatus) {
		status = istatus;
	}	
	
	/**
	 * Reset the student.
	 */
	public static void reSet() {
		lookForThisUserByID = "";
		isLoaded = false;
		iD = null;
		pass = null;
		firstName = null;
		lastName = null;
		email = null;
		phoneNumber = null;
		faculty = null;
		gender = null;
		getUpdatesToEmail = null;
		getUpdatesToPhone = null;
		status = null;
	}
	
	/**
	 * Create a string for student with ID and password.
	 */
	public String toString() {
		return "U: " + iD + "P: " + pass;
	}
	
}
