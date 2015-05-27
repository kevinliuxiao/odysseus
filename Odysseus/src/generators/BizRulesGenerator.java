package generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Random;

import exception.InvalidInputException;
import runner.TestRunner;
import util.DateTimeUtils;
import util.WebUtils;

public class BizRulesGenerator {

//	private static String enable_self_sponsor_reg; 
	private static String self_sponsor_amount;
//	private static String enable_charge_fee_to_register;
	private static String reg_fee_amount;
//	private static String enable_wave_fee_by_raising;
	private static String funds_raised;
//	private static String enable_first_discount_period;
	private static String early_bird_reg_fee_amount;
	private static String early_bird_reg_on_or_before_day;
	private static String early_bird_reg_on_or_before_month;
	private static String early_bird_reg_on_or_before_year;
	
//	private static String enable_second_discount_period;
	private static String late_bird_reg_fee_amount;
	private static String late_bird_reg_on_or_before_day;
	private static String late_bird_reg_on_or_before_month;
	private static String late_bird_reg_on_or_before_year;
	
//	private static String enable_default_fundraising_goal;
	private static String default_fundraising_goal_amount;
	
	private static void generate() throws InvalidInputException 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    
	    String randomNum = String.valueOf(System.currentTimeMillis()/1000);
	    Random randGoal = new Random();
	    
		String earlyBirdDate = WebUtils.randomDatesWithinRange(TestRunner.getReuseValue("EventSetupWizard.event_start_date"), TestRunner.getReuseValue("EventSetupWizard.event_end_date"));
		String lateBirdDate = WebUtils.randomDatesWithinRange(earlyBirdDate, TestRunner.getReuseValue("EventSetupWizard.event_end_date"));
		
		Hashtable<String,String> earlyBirdHash = WebUtils.parseDate(earlyBirdDate);
		Hashtable<String,String> lateBirdHash = WebUtils.parseDate(lateBirdDate);
		
	    self_sponsor_amount = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);
	    reg_fee_amount = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);
		funds_raised = String.valueOf(Integer.parseInt(reg_fee_amount) * 2);
		early_bird_reg_fee_amount = String.valueOf(Integer.parseInt(reg_fee_amount) / 2);
		early_bird_reg_on_or_before_day = ((earlyBirdHash.size() == 0) ? String.valueOf(10) : earlyBirdHash.get("day"));
		try{
			early_bird_reg_on_or_before_month = ((earlyBirdHash.size() == 0) ? "Oct" : DateTimeUtils.getMonthName(Integer.parseInt(earlyBirdHash.get("month"))));
		}
		catch(Exception e){
			early_bird_reg_on_or_before_month = "Oct";
		}
		early_bird_reg_on_or_before_year = ((earlyBirdHash.size() == 0) ? String.valueOf(2015) : earlyBirdHash.get("year"));

		late_bird_reg_fee_amount = String.valueOf(Integer.parseInt(reg_fee_amount) / 3);
		late_bird_reg_on_or_before_day = ((lateBirdHash.size() == 0) ? String.valueOf(10) : lateBirdHash.get("day"));
		try{
			late_bird_reg_on_or_before_month = ((lateBirdHash.size() == 0) ? "Oct" : DateTimeUtils.getMonthName(Integer.parseInt(lateBirdHash.get("month"))));
		}
		catch(Exception e){
			late_bird_reg_on_or_before_month = "Oct";
		}
		
		late_bird_reg_on_or_before_year = ((lateBirdHash.size() == 0) ? String.valueOf(2015) : lateBirdHash.get("year"));
		
		default_fundraising_goal_amount = String.valueOf(randGoal.nextInt((1000000 - 1) + 1) + 1);

	}
	
	public static String self_sponsor_amount() throws InvalidInputException{
		generate();
		return self_sponsor_amount;
	}
	
	public static String reg_fee_amount() throws InvalidInputException{
		generate();
		return reg_fee_amount;
	}
	
	public static String funds_raised() throws InvalidInputException{
		generate();
		return funds_raised;
	}
	
	public static String early_bird_reg_fee_amount() throws InvalidInputException{
		generate();
		return early_bird_reg_fee_amount;
	}
	
	public static String early_bird_reg_on_or_before_day() throws InvalidInputException{
		generate();
		return early_bird_reg_on_or_before_day;
	}
	
	public static String early_bird_reg_on_or_before_month() throws InvalidInputException{
		generate();
		return early_bird_reg_on_or_before_month;
	}
	
	public static String early_bird_reg_on_or_before_year() throws InvalidInputException{
		generate();
		return early_bird_reg_on_or_before_year;
	}
	
	public static String late_bird_reg_fee_amount() throws InvalidInputException{
		generate();
		return late_bird_reg_fee_amount;
	}

	public static String late_bird_reg_on_or_before_day() throws InvalidInputException{
		generate();
		return late_bird_reg_on_or_before_day;
	}
	
	public static String late_bird_reg_on_or_before_month() throws InvalidInputException{
		generate();
		return late_bird_reg_on_or_before_month;
	}
	
	public static String late_bird_reg_on_or_before_year() throws InvalidInputException{
		generate();
		return late_bird_reg_on_or_before_year;
	}
	
	public static String default_fundraising_goal_amount() throws InvalidInputException{
		generate();
		return default_fundraising_goal_amount;
	}
	
}
