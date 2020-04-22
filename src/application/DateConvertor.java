package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date converter class to change from different date types.
 * @author OBL_Team13
 */
public class DateConvertor {

	private static LocalDate localDate;
	private static Date date = new Date();
	private static String string = new String();
	private static java.sql.Date sqlDate;
	private static java.util.Date utilDate;
	
	/**
	 * Convert Date type to sql.Date type.
	 * @param date	date in date type.
	 * @return		date in sql.Date type.
	 */
	public static java.sql.Date dateToSqlDate(Date date)
	{
		Date in = date;
		LocalDateTime PrintingDate1 = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		java.sql.Date sqlDate = java.sql.Date.valueOf(PrintingDate1.toLocalDate());
		
		return sqlDate;
	}
	
	/**
	 * Convert sql.Date type to LocalDate type.
	 * @param date	date in sql.Date type.
	 * @return		date in LocalDate type.
	 */
	public static LocalDate sqlDateToLocalDate(java.sql.Date date)
	{
		return date.toLocalDate();
	}
	
	
	/**
	 * Convert Date type to String.
	 * @param date	date in Date type.
	 * @return		date in String.
	 */
	public static String DateToString(Date date)
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = date;        
		String reportDate = df.format(today);
		System.out.println("Report Date: " + reportDate);
		
		return reportDate;
	}
	
	/**
	 * Convert util.Date type to LocalDate Type.
	 * @param utilDate	date in util.Date type.
	 * @return			date in LocalDate type.
	 */
	public static LocalDate DateToLocalDate(java.util.Date utilDate)
	{
		java.util.Date input = utilDate;
		LocalDate returnDate = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return returnDate;
	}
	
	/**
	 * Convert util.Date type to sql.Date type.
	 * @param date	date in util.Date type.
	 * @return		date in sql.Date type.
	 */
	public static java.sql.Date utilDateToSqlDate(java.util.Date date)
	{
	java.util.Date utilDate = date;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	final String stringDate= dateFormat.format(utilDate);
	final java.sql.Date sqlDate=  java.sql.Date.valueOf(stringDate);
	return sqlDate;
	}
	
	public static java.util.Date sqlDateToUtilDate(java.sql.Date date)
	{
	//converting java.sql.Date to java.util.Date back
	java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
	return utilDate;
	}
	
	/**
	 * Convert LocalDate type to util.Date type.
	 * @param localDate	date in LocalDate type.
	 * @return			date in util.Date type.
	 */
	public static java.util.Date localDateToUtilDate(LocalDate localDate){
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static java.sql.Date localDateToSqlDate(LocalDate localDate){
		java.util.Date tempDate = localDateToUtilDate(localDate);
		return utilDateToSqlDate(tempDate);
	}
	
}
