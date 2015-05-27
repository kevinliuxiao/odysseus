package page.impl;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;
import generators.BizRulesGenerator;
import generators.EventSetUpGenerator;
import generators.WaveGenerator;
import page.Page;
import page.PageObject;
import runner.TestRunner;
import util.WebUtils;
import util.XMLFileReader;

public class EventRegTypeIndBizRules extends Page{

	private PageObject enable_self_sponsor_reg; 
	private PageObject self_sponsor_amount;
	private PageObject enable_charge_fee_to_register;
	private PageObject reg_fee_amount;
	private PageObject enable_wave_fee_by_raising;
	private PageObject funds_raised;
	private PageObject enable_first_discount_period;
	private PageObject early_bird_reg_fee_amount;
	private PageObject early_bird_reg_on_or_before_day;
	private PageObject early_bird_reg_on_or_before_month;
	private PageObject early_bird_reg_on_or_before_year;
	
	private PageObject enable_second_discount_period;
	private PageObject late_bird_reg_fee_amount;
	private PageObject late_bird_reg_on_or_before_day;
	private PageObject late_bird_reg_on_or_before_month;
	private PageObject late_bird_reg_on_or_before_year;
	
	private PageObject enable_default_fundraising_goal;
	private PageObject default_fundraising_goal_amount;
	private PageObject reg_type;
	
	private String month = "", day = "", year = "";
	
	private String defaultToggle = "yes";
	
	public EventRegTypeIndBizRules(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		
		this.nextPages.add(PageType.EVENT_REG_TYPE_MANAGE);
		this.nextPages.add(PageType.EVENT_REG_TYPE_IND_SETTINGS);
		this.nextPages.add(PageType.EVENT_REG_TYPE_IND_LOCATIONS);
		
		this.enable_self_sponsor_reg = new PageObject(params, stepIndex,"enable_self_sponsor_reg");    
		this.self_sponsor_amount = new PageObject(params, stepIndex,"self_sponsor_amount");    
		this.enable_charge_fee_to_register = new PageObject(params, stepIndex,"enable_charge_fee_to_register");    
		this.reg_fee_amount = new PageObject(params, stepIndex,"reg_fee_amount");    
		this.enable_wave_fee_by_raising = new PageObject(params, stepIndex,"enable_wave_fee_by_raising");    
		this.funds_raised = new PageObject(params, stepIndex,"funds_raised");    
		
		this.enable_first_discount_period = new PageObject(params, stepIndex,"enable_first_discount_period");    
		this.early_bird_reg_fee_amount = new PageObject(params, stepIndex,"early_bird_reg_fee_amount");    
		this.early_bird_reg_on_or_before_day = new PageObject(params, stepIndex,"early_bird_reg_on_or_before_day");    
		this.early_bird_reg_on_or_before_month = new PageObject(params, stepIndex,"early_bird_reg_on_or_before_month");    
		this.early_bird_reg_on_or_before_year = new PageObject(params, stepIndex,"early_bird_reg_on_or_before_year");    
		
		this.enable_second_discount_period = new PageObject(params, stepIndex,"enable_second_discount_period");    
		this.late_bird_reg_fee_amount = new PageObject(params, stepIndex,"late_bird_reg_fee_amount");    
		this.late_bird_reg_on_or_before_day = new PageObject(params, stepIndex,"late_bird_reg_on_or_before_day");   
		this.late_bird_reg_on_or_before_month = new PageObject(params, stepIndex,"late_bird_reg_on_or_before_month");  
		this.late_bird_reg_on_or_before_year = new PageObject(params, stepIndex,"late_bird_reg_on_or_before_year");  
		
		this.enable_default_fundraising_goal = new PageObject(params, stepIndex,"enable_default_fundraising_goal");    
		this.default_fundraising_goal_amount = new PageObject(params, stepIndex,"default_fundraising_goal_amount"); 
		this.reg_type = new PageObject(params, stepIndex,"reg_type"); 
	}
	
	protected void Goto(WebDriver driver, PageType nextPage)throws InvalidInputException, SeleniumException, VerificationException {
		if (PageType.EVENT_REG_TYPE_IND_SETTINGS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "General", new By.ByLinkText("General"), null, null);
		} 
		if (PageType.EVENT_REG_TYPE_IND_LOCATIONS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Locations", new By.ById("buttonNext"),null, null);
		}
		if (PageType.EVENT_REG_TYPE_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "buttonSubmit", new By.ById("buttonSubmit"),null, null);
		}
	}
	
	protected List<String> checkPageError(WebDriver driver)throws InvalidInputException, SeleniumException,VerificationException {
		return null;
	}

	protected void performAction(WebDriver driver, ActionType actionType)throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.SETUP_BIZ_RULES)){
			setupBizRules(driver);
		}
	}

	private void setupBizRules(WebDriver driver) throws SeleniumException, InvalidInputException {
		
		performInteraction(driver, OperationType.TOGGLE, "Require Self-Sponsorship to register", new By.ById("checkboxSelfSponsorAmount"), enable_self_sponsor_reg, defaultToggle);
		performInteraction(driver, OperationType.TYPE, "Self-Sponsor Amount", new By.ById("textboxSelfSponsorAmount"), self_sponsor_amount, BizRulesGenerator.self_sponsor_amount());
		performInteraction(driver, OperationType.TOGGLE, "Charge a fee to register",new By.ById("checkboxFeeAmount"), enable_charge_fee_to_register, defaultToggle);
		
		int amount = (reg_fee_amount.value().isEmpty()) ? Integer.parseInt(EventSetUpGenerator.minimumDonationAmount()) :  Integer.parseInt(reg_fee_amount.value());
		performInteraction(driver, OperationType.TYPE, "Registration Fee Amount",new By.ById("textboxFeeAmount"), reg_fee_amount, BizRulesGenerator.reg_fee_amount());
		performInteraction(driver, OperationType.TOGGLE, "Waive Fee by raising",new By.ById("checkboxWaiveAmount"), enable_wave_fee_by_raising, defaultToggle);
		
		performInteraction(driver, OperationType.TYPE, "Funds Raised", new By.ById("textboxWaiveAmount"), funds_raised, BizRulesGenerator.funds_raised());
		
		performInteraction(driver, OperationType.TOGGLE, "1st Discount Period", new By.ById("checkboxFeeAmountEarlyBird1"), enable_first_discount_period, defaultToggle);
		performInteraction(driver, OperationType.TYPE, "Early Bird Registration Fee Amount", new By.ById("textboxFeeAmountEarlyBird1"), early_bird_reg_fee_amount, BizRulesGenerator.early_bird_reg_fee_amount());
		
		performInteraction(driver, OperationType.SELECT, "Month", new By.ById("dropdownlistEarlyBird1Month"), early_bird_reg_on_or_before_month, BizRulesGenerator.early_bird_reg_on_or_before_month());
		performInteraction(driver, OperationType.SELECT, "Day", new By.ById("dropdownlistEarlyBird1Day"), early_bird_reg_on_or_before_day, BizRulesGenerator.early_bird_reg_on_or_before_day());
		performInteraction(driver, OperationType.SELECT, "Year", new By.ById("dropdownlistEarlyBird1Year"), early_bird_reg_on_or_before_year, BizRulesGenerator.early_bird_reg_on_or_before_year());

		performInteraction(driver, OperationType.TOGGLE, "2nd Discount Period", new By.ById("checkboxFeeAmountEarlyBird2"), enable_second_discount_period, defaultToggle);
		performInteraction(driver, OperationType.TYPE, "Late Bird Registration Fee Amount", new By.ById("textboxFeeAmountEarlyBird2"), late_bird_reg_fee_amount, BizRulesGenerator.late_bird_reg_fee_amount());

		performInteraction(driver, OperationType.SELECT, "Month", new By.ById("dropdownlistEarlyBird2Month"), late_bird_reg_on_or_before_month, BizRulesGenerator.late_bird_reg_on_or_before_month());
		performInteraction(driver, OperationType.SELECT, "Day", new By.ById("dropdownlistEarlyBird2Day"), late_bird_reg_on_or_before_day, BizRulesGenerator.late_bird_reg_on_or_before_day());
		performInteraction(driver, OperationType.SELECT, "Year", new By.ById("dropdownlistEarlyBird2Year"), late_bird_reg_on_or_before_year, BizRulesGenerator.late_bird_reg_on_or_before_year());
		
		
		performInteraction(driver, OperationType.TOGGLE, "Default Fundraising Goal",new By.ById("checkboxDefaultIndGoal"), enable_default_fundraising_goal, defaultToggle);
		performInteraction(driver, OperationType.TYPE, "Default Fundraising Goal Amount", new By.ById("textboxDefaultIndGoal"), default_fundraising_goal_amount, BizRulesGenerator.default_fundraising_goal_amount());
		
		if(reg_type.value().equalsIgnoreCase("Single Registration") || reg_type.value().isEmpty())
		{	performInteraction(driver, OperationType.CLICK, "Single Registration",new By.ById("radiobuttonSingleRegistration"), null, null); }
		else if(reg_type.value().equalsIgnoreCase("Package Registration"))
		{ performInteraction(driver, OperationType.CLICK, "Package Registration",new By.ById("radiobuttonPackageRegistration"), null, null); }
	}
	
	  
}
