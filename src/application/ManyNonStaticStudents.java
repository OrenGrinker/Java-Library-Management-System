package application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for list of students.
 * @author OBL_Team13
 */
public class ManyNonStaticStudents implements Serializable{

	private ArrayList<NonStaticUser> manyNonStaticUsers;
	private static ArrayList<NonStaticUser> manyNonStaticStaticUsers = new ArrayList<NonStaticUser>();
	public static boolean isLoaded = false;
	public static String lookForThisUserBySerial = new String();
	
	/**
	 * Constructor for ManyStudents.
	 */
	public ManyNonStaticStudents() {
		manyNonStaticUsers = new ArrayList<NonStaticUser>();
		isLoaded = false;
	}
	
	/**
	 * Constructor for ManyStudents initialized with existing list of students.
	 * @param manyUsers	ManyStudents type.
	 */
	public ManyNonStaticStudents(ArrayList<NonStaticUser> manyUsers) {
		manyNonStaticStaticUsers = manyUsers;
		isLoaded = false;
	}


	/**
	 * Add student to ManyStudents.
	 * @param usr	student type.
	 */
	public void addUser(NonStaticUser usr) {
		manyNonStaticUsers.add(usr);
	}

	/**
	 * Add student to a static ManyStudents list.
	 * @param usr	student type.
	 */
	public static void addUserToStatic(NonStaticUser usr) {
		for(NonStaticUser emp : manyNonStaticStaticUsers)
			if(emp.getID().equals(usr.getID()))
				return;
		manyNonStaticStaticUsers.add(usr);
	}


	/**
	 * Remove a student from ManyStudents.
	 * @param usr	student type.
	 */
	public void removeUser(NonStaticUser usr) {
		manyNonStaticUsers.remove(usr);
	}

	/**
	 * Remove a student from a static ManyStudents list.
	 * @param usr	student type.
	 */
	public static void removeUserFromStatic(NonStaticUser usr) {
		manyNonStaticStaticUsers.remove(usr);
	}

	/**
	 * Clear a static ManyStudents list.
	 */
	public static void clearStaticbooks() {
		manyNonStaticStaticUsers.clear();
	}


	/**
	 * Get list of students.
	 * @return	list of students.
	 */
	public ArrayList<NonStaticUser> getAllUsers(){
		return manyNonStaticUsers;
	}

	/**
	 * Get a static list of students.
	 * @return	list of students.
	 */
	public static ArrayList<NonStaticUser> getAllStaticUsers(){
		return manyNonStaticStaticUsers;
	}

	/**
	 * Get a student by ID.
	 * @param id	ID of student.
	 * @return		return student.
	 */
	public static NonStaticUser getStaticUsers(String id){
		for(NonStaticUser usr : manyNonStaticStaticUsers)
			if(usr.getID().equals(id))
				return usr;
		return null;
	}

	/**
	 * Create a string for ManyStudents.
	 */
	@Override
	public String toString() {
		return manyNonStaticUsers.toString();
	}
	
}
