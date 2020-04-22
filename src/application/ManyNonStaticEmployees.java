package application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for list of employees.
 * @author OBL_Team13
 */
public class ManyNonStaticEmployees implements Serializable{

	private ArrayList<NonStaticEmployee> manyNonStaticEmployees;
	private static ArrayList<NonStaticEmployee> manyNonStaticStaticEmployees = new ArrayList<NonStaticEmployee>();
	public static boolean isLoaded = false;
	public static String lookForThisEmployeeBySerial = new String();
	
	/**
	 * Constructor for ManyEmployees.
	 */
	public ManyNonStaticEmployees() {
		manyNonStaticEmployees = new ArrayList<NonStaticEmployee>();
		isLoaded = false;
	}

	public ManyNonStaticEmployees(ArrayList<NonStaticEmployee> manyEmployees) {
		manyNonStaticStaticEmployees = manyEmployees;
		isLoaded = false;
	}


	/**
	 * Add an employee to ManyEmployees.
	 * @param emp	employee type.
	 */
	public void addEmployee(NonStaticEmployee emp) {
		manyNonStaticEmployees.add(emp);
	}

	/**
	 * Add an employee to a static ManyEmployees list.
	 * @param employee	employee type.
	 */
	public static void addEmployeeToStatic(NonStaticEmployee employee) {
		for(NonStaticEmployee emp : manyNonStaticStaticEmployees)
			if(emp.getID().equals(employee.getID()))
				return;
		manyNonStaticStaticEmployees.add(employee);
	}


	/**
	 * Remove an employee from ManyEmployees.
	 * @param emp	employee type.
	 */
	public void removeEmployee(NonStaticEmployee emp) {
		manyNonStaticEmployees.remove(emp);
	}

	/**
	 * Remove an employee from a static ManyEmployees list.
	 * @param emp	employee type.
	 */
	public static void removeEmployeeFromStatic(NonStaticEmployee emp) {
		manyNonStaticStaticEmployees.remove(emp);
	}

	/**
	 * Clear a static ManyEmployees list.
	 */
	public static void clearStaticbooks() {
		manyNonStaticStaticEmployees.clear();
	}


	/**
	 * Get all employees in ManyEmployees.
	 * @return	list of ManyEmployees.
	 */
	public ArrayList<NonStaticEmployee> getAllEmployees(){
		return manyNonStaticEmployees;
	}

	/**
	 * Get all employees in a static ManyEmployees list.
	 * @return	list of ManyEmployees.
	 */
	public static ArrayList<NonStaticEmployee> getAllStaticEmployees(){
		return manyNonStaticStaticEmployees;
	}

	/**
	 * Return employee by ID.
	 * @param id	ID of employee
	 * @return		employee type.
	 */
	public static NonStaticEmployee getStaticEmployees(String id){
		for(NonStaticEmployee emp : manyNonStaticStaticEmployees)
			if(emp.getID().equals(id))
				return emp;
		return null;
	}

	/**
	 * Create a string to ManyEmployees.
	 */
	@Override
	public String toString() {
		return manyNonStaticEmployees.toString();
	}
	
}
