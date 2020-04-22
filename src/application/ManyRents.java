package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.sql.*;

/**
 * Class for list of rents.
 * @author OBL_Team13
 */
public class ManyRents implements Serializable{
	
	private ArrayList<Rent> manyRents;	
	private static ArrayList<Rent> myStaticRents = new ArrayList<Rent>();
	public static boolean isLoaded = false;
	
	/**
	 * Constructor for ManyRents.
	 */
	public ManyRents() {
		manyRents = new ArrayList<Rent>();
		isLoaded = false;
	}
	
	/**
	 * Constructor for ManyRents initialized with existing list of rents.
	 * @param manyRents	list of rents.
	 */
	public ManyRents(ArrayList<Rent>  manyRents) {
		myStaticRents = manyRents;
		isLoaded = false;
		//myStaticRents.clear();
	}
	
	/**
	 * Add a rent to ManyRents.
	 * @param rent	rent type.
	 */
	public void addRent(Rent rent) {
		manyRents.add(rent);
	}
	
	/**
	 * Add a rent to a static ManyRents list.
	 * @param rent	rent type.
	 */
	public static void addRentToStatic(Rent rent) {
		myStaticRents.add(rent);
	}
	
	
	/**
	 * Remove a rent from ManyRents.
	 * @param rent	rent type.
	 */
	public void removeRent(Rent rent) {
		manyRents.remove(rent);
	}
	
	/**
	 * Remove a rent from a static ManyRents list.
	 * @param rent	rent type.
	 */
	public static void removeRentFromStatic(Rent rent) {
		myStaticRents.remove(rent);
	}
	
	/**
	 * Clear ManyRents.
	 */
	public static void clearStaticRents() {
		myStaticRents.clear();
	}
	
	/**
	 * Get ManyRents.
	 * @return	rents list.
	 */
	public ArrayList<Rent> getAllRents(){
		return manyRents;
	}
	
	/**
	 * Get ManyRents list.
	 * @return	rents list.
	 */
	public static ArrayList<Rent> getAllStaticRents(){
		return myStaticRents;
	}
	
	/**
	 * Get string for ManyRents.
	 */
	@Override
	public String toString() {
		return manyRents.toString();
	}
	
}
