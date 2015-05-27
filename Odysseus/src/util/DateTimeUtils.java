package util;

import exception.InvalidInputException;

public class DateTimeUtils {

	public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	public static final String[] MONTHSFULL = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public static String getMonthName(int monthNum) throws InvalidInputException {
		return MONTHS[monthNum - 1];
	}
	
	public static String getMonthFullName(int monthNum) throws InvalidInputException {
		return MONTHSFULL[monthNum - 1];
	}

}
