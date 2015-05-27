package generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import page.PageObject;

public class WaveGenerator {
	
	private static String wave_name;
	private static String wave_language;
	private static String wave_export_id;
//	private static String wave_start_date;
	private static String wave_registrant_block;
	private static String wave_team_block;
	private static String wave_starting_registrant_no;
	
	private static String wavestartday;
	private static String wavestartmonth;
	private static String wavestartyear;
	private static String wavestarthour;
	private static String wavestartminute;
	private static String wavestartperiod;
	
	private static void generate() 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    
	    String randomNum = String.valueOf(System.currentTimeMillis()/1000);
	    Random randGoal = new Random();
		
	    wave_name = "Wave" + randomNum;
	    wave_language = "English";
	    wave_export_id = wave_name + "ExportID";
//	    wave_start_date = location_name_en + " description";
	    wave_registrant_block = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);
	    wave_team_block = String.valueOf(randGoal.nextInt((Integer.parseInt(wave_registrant_block)-1 - 1) + 1) + 1);
	    wave_starting_registrant_no = "0";
	   
	    
	    //================================================
	    wavestartmonth = "Jan";
	    wavestartyear = "2014";
	    wavestartday = "20";
	    wavestarthour = "10"; 
	    wavestartminute = "15"; 
	    wavestartperiod ="AM";
		
	}

	private static String randomStringGenerator(int length) 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    return rand.substring(0, length);
	}
	
	public static String wave_name()
	{
		generate();
		return wave_name;
	}
	
	public static String wave_language()
	{
		generate();
		return wave_language;
	}
	public static String wave_export_id()
	{
		generate();
		return wave_export_id;
	}
//	public static String wave_start_date()
//	{
//		generate();
//		return wave_start_date;
//	}
	
	public static String wave_registrant_block()
	{
		generate();
		return wave_registrant_block;
	}
	public static String wave_team_block()
	{
		generate();
		return wave_team_block;
	}
	public static String wave_starting_registrant_no()
	{
		generate();
		return wave_starting_registrant_no;
	}
	public static String wavestartday()
	{
		generate();
		return wavestartday;
	}

	public static String wavestartmonth()
	{
		generate();
		return wavestartmonth;
	}
	public static String wavestartyear()
	{
		generate();
		return wavestartyear;
	}
	public static String wavestarthour()
	{
		generate();
		return wavestarthour;
	}
	public static String wavestartminute()
	{
		generate();
		return wavestartminute;
	}

	public static String wavestartperiod()
	{
		generate();
		return wavestartperiod;
	}

}
