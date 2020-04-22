package application;

import java.util.Date;
import java.io.Serializable;
import java.sql.*;

/**
 * Class for students.
 * @author OBL_Team13
 */
public class NonStaticUser implements Serializable {
	
	private String iD = "0000";
	private String pass = "1111";
	private String firstName = "Aa";
	private String lastName = "Zz";
	private String email = "A@mail.com";
	private String phoneNumber = "012";
	private java.sql.Date birthdate;
	private String faculty = "fac";
	private String gender = "gen";
	private String getUpdatesToEmail = "1";
	private String getUpdatesToPhone = "1";
	private String status = "active";
	
	public String getID() {
		return iD;
	}

	public void setID(String iiD) {
		iD = iiD;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String ipass) {
		pass = ipass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String ifirstName) {
		firstName = ifirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String ilastName) {
		lastName = ilastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String iemail) {
		email = iemail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String iphoneNumber) {
		phoneNumber = iphoneNumber;
	}

	public java.sql.Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(java.sql.Date ibirthdate) {
		birthdate = ibirthdate;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String ifaculty) {
		faculty = ifaculty;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String igender) {
		gender = igender;
	}

	public String getGetUpdatesToEmail() {
		return getUpdatesToEmail;
	}

	public void setGetUpdatesToEmail(String igetUpdatesToEmail) {
		getUpdatesToEmail = igetUpdatesToEmail;
	}

	public String getGetUpdatesToPhone() {
		return getUpdatesToPhone;
	}

	public void setGetUpdatesToPhone(String igetUpdatesToPhone) {
		getUpdatesToPhone = igetUpdatesToPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String istatus) {
		status = istatus;
	}	
	
	/**
	 * Reset a student.
	 */
	public void reSet() {
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
