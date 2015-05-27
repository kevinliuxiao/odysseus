package generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InvalidInputException;
import util.DateTimeUtils;

public class LocationSetUpGenerator {
	
	private static String location_name_en;
	private static String location_name_fr;
	private static String location_exportid;
	private static String location_description;
	private static String address;

	private static String city_town; 
	private static String province_state;
	private static String postal_zipcode;
	private static String country;
	private static String language; 
	private static String image_a;
	private static String image_b; 
	private static String content_area_a;
	private static String content_area_b;
	
	
	private static String eventstartmonth;
	private static String eventstartday;
	private static String eventstartyear;
	private static String eventstarthour;
	private static String eventstartminute;
	private static String eventstartperiod;
	
	private static String eventendmonth;
	private static String eventendday;
	private static String eventendyear;
	private static String eventendhour;
	private static String eventendminute;
	private static String eventendperiod;
	
	
	
	private static String eventregstartmonth;
	private static String eventregstartday;
	private static String eventregstartyear;
	private static String eventregstarthour;
	private static String eventregstartminute;
	private static String eventregstartperiod;
	
	private static String eventregendmonth;
	private static String eventregendday;
	private static String eventregendyear;
	private static String eventregendhour;
	private static String eventregendminute;
	private static String eventregendperiod;
	
	private static void generate() 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    
	    String randomNum = String.valueOf(System.currentTimeMillis()/1000);
	    Random randGoal = new Random();
		
	    location_name_en = "Toronto" + randomNum;
	    location_name_fr = "Toronto Fr" + randomNum;
	    location_exportid = location_name_en;
	    location_description = location_name_en + " description";
	    address = "215 Spadina ave";
	    city_town = "Canada";
	    province_state = "Ontario";
	    postal_zipcode = "M3M 8S7";
	    
	    //================================================
	    country = "Canada";
	    language = "English";
	    image_a = "header.png"; 
		
	    image_b = "footer.png"; 
	    content_area_a ="'content area A' testing";
	    content_area_b = "'content area B' testing";
	    
	    parseDate("20/10/2012 9:00 AM", "20/12/2016 9:00 AM", "20/12/2012 9:00 AM", "20/12/2016 9:00 AM");
		
	}
	
	private static void parseDate(String startDateString, String endDateString, String regStartDateString, String regEndDateString)
	{
		try
		{
			Pattern datePattern = Pattern.compile("^(\\d+{2})/(\\d+{2})/(\\d{4}) (1[012]|[1-9]):([0-5][0-9]) (?i)(AM|PM)$");
	
			if(startDateString != null && !startDateString.isEmpty())
			{
				Matcher startMatcher = datePattern.matcher(startDateString);
				if (!startMatcher.find()) {
					throw new InvalidInputException(startDateString);
				}
				eventstartday = startMatcher.group(1);
				eventstartmonth = DateTimeUtils.getMonthName(Integer.parseInt(startMatcher.group(2)));
				eventstartyear = startMatcher.group(3);
				
				eventstarthour = startMatcher.group(4);
				eventstartminute = startMatcher.group(5);
				eventstartperiod = startMatcher.group(6);
			}
	
			if(endDateString != null && !endDateString.isEmpty())
			{
				Matcher endMatcher = datePattern.matcher(endDateString);
				if (!endMatcher.find()) {
					throw new InvalidInputException(endDateString);
				}
				eventendday = endMatcher.group(1);
				eventendmonth = DateTimeUtils.getMonthName(Integer.parseInt(endMatcher.group(2)));
				eventendyear = endMatcher.group(3);
				
				eventendhour = endMatcher.group(4);
				eventendminute = endMatcher.group(5);
				eventendperiod = endMatcher.group(6);
			}
			
			if(regStartDateString != null && !regStartDateString.isEmpty())
			{
				Matcher regStartMatcher = datePattern.matcher(regStartDateString);
				if (!regStartMatcher.find()) {
					throw new InvalidInputException(regStartDateString);
				}
				eventregstartday = regStartMatcher.group(1);
				eventregstartmonth = DateTimeUtils.getMonthName(Integer.parseInt(regStartMatcher.group(2)));
				eventregstartyear = regStartMatcher.group(3);
				
				eventregstarthour = regStartMatcher.group(4);
				eventregstartminute = regStartMatcher.group(5);
				eventregstartperiod = regStartMatcher.group(6);
			}
	
			if(regStartDateString != null && !regStartDateString.isEmpty())
			{
				Matcher regEndMatcher = datePattern.matcher(regEndDateString);
				if (!regEndMatcher.find()) {
					throw new InvalidInputException(regEndDateString);
				}
				eventregendday = regEndMatcher.group(1);
				eventregendmonth = DateTimeUtils.getMonthName(Integer.parseInt(regEndMatcher.group(2)));
				eventregendyear = regEndMatcher.group(3);
				
				eventregendhour = regEndMatcher.group(4);
				eventregendminute = regEndMatcher.group(5);
				eventregendperiod = regEndMatcher.group(6);
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	private static String randomStringGenerator(int length) 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    return rand.substring(0, length);
	}
	
	public static String location_name_en()
	{
		generate();
		return location_name_en;
	}
	
	public static String location_name_fr()
	{
		generate();
		return location_name_fr;
	}
	public static String location_exportid()
	{
		generate();
		return location_exportid;
	}
	public static String location_description()
	{
		generate();
		return location_description;
	}
	
	public static String address()
	{
		generate();
		return address;
	}
	public static String city_town()
	{
		generate();
		return city_town;
	}
	public static String province_state()
	{
		generate();
		return province_state;
	}
	public static String postal_zipcode()
	{
		generate();
		return postal_zipcode;
	}

	public static String country()
	{
		generate();
		return country;
	}
	public static String language()
	{
		generate();
		return language;
	}
	public static String image_a()
	{
		generate();
		return image_a;
	}
	public static String image_b()
	{
		generate();
		return image_b;
	}

	public static String content_area_a()
	{
		generate();
		return content_area_a;
	}
	
	public static String content_area_b()
	{
		generate();
		return content_area_b;
	}
	
	//===================================================================
	
	public static String eventstartday()
	{
		generate();
		return eventstartday;
	}
	
	public static String eventstartmonth()
	{
		generate();
		return eventstartmonth;
	}
	public static String eventstartyear()
	{
		generate();
		return eventstartyear;
	}
	public static String eventstarthour()
	{
		generate();
		return eventstarthour;
	}
	
	public static String eventstartminute()
	{
		generate();
		return eventstartminute;
	}
	public static String eventstartperiod()
	{
		generate();
		return eventstartperiod;
	}	
	public static String eventendday()
	{
		generate();
		return eventendday;
	}
	public static String eventendmonth()
	{
		generate();
		return eventendmonth;
	}

	public static String eventendyear()
	{
		generate();
		return eventendyear;
	}
	public static String eventendhour()
	{
		generate();
		return eventendhour;
	}
	public static String eventendminute()
	{
		generate();
		return eventendminute;
	}
	public static String eventendperiod()
	{
		generate();
		return eventendperiod;
	}

	public static String eventregstartday()
	{
		generate();
		return eventregstartday;
	}
	
	public static String eventregstartmonth()
	{
		generate();
		return eventregstartmonth;
	}
	public static String eventregstartyear()
	{
		generate();
		return eventregstartyear;
	}	
	public static String eventregstarthour()
	{
		generate();
		return eventregstarthour;
	}
	public static String eventregstartminute()
	{
		generate();
		return eventregstartminute;
	}

	public static String eventregstartperiod()
	{
		generate();
		return eventregstartperiod;
	}
	public static String eventregendday()
	{
		generate();
		return eventregendday;
	}
	public static String eventregendmonth()
	{
		generate();
		return eventregendmonth;
	}
	public static String eventregendyear()
	{
		generate();
		return eventregendyear;
	}

	public static String eventregendhour()
	{
		generate();
		return eventregendhour;
	}
	
	public static String eventregendminute()
	{
		generate();
		return eventregendminute;
	}
	public static String eventregendperiod()
	{
		generate();
		return eventregendperiod;
	}

}
