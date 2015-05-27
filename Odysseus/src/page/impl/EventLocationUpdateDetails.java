package page.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;
import generators.EventSetUpGenerator;
import generators.LocationSetUpGenerator;
import generators.WaveGenerator;
import page.Page;
import page.PageGroupObject;
import page.PageObject;
import util.DateTimeUtils;
import util.XMLFileReader;

public class EventLocationUpdateDetails extends Page
{
	private PageObject fundraising_goal;
	private PageObject event_start_date;
	private PageObject event_end_date;
	private PageObject registration_start_date;
	private PageObject registration_end_date;
	
	private PageObject wave_name;
	private PageObject wave_language;
	private PageObject wave_export_id;
	private PageObject wave_is_this_wave_private;
	private PageObject wave_start_date;
	private PageObject wave_registrant_block;
	private PageObject wave_team_block;
	private PageObject wave_starting_registrant_no;
	
	private PageObject eventstartmonth;
	private PageObject eventstartday;
	private PageObject eventstartyear;
	private PageObject eventstarthour;
	private PageObject eventstartminute;
	private PageObject eventstartperiod;
	
	private PageObject eventendmonth;
	private PageObject eventendday;
	private PageObject eventendyear;
	private PageObject eventendhour;
	private PageObject eventendminute;
	private PageObject eventendperiod;
	
	
	
	private PageObject eventregstartmonth;
	private PageObject eventregstartday;
	private PageObject eventregstartyear;
	private PageObject eventregstarthour;
	private PageObject eventregstartminute;
	private PageObject eventregstartperiod;
	
	private PageObject eventregendmonth;
	private PageObject eventregendday;
	private PageObject eventregendyear;
	private PageObject eventregendhour;
	private PageObject eventregendminute;
	private PageObject eventregendperiod;
	
	
	private PageObject wavestartday;
	private PageObject wavestartmonth;
	private PageObject wavestartyear;
	private PageObject wavestarthour;
	private PageObject wavestartminute;
	private PageObject wavestartperiod;
	
	private boolean isWaveOrLocationAction;

	public EventLocationUpdateDetails(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		//nextPages.add(PageType.EVENT_LOCATIONS);
		nextPages.add(PageType.EVENT_LOCATION_DETAILS);
		
		fundraising_goal = new PageObject(params, stepIndex,"fundraising_goal"); 
		event_start_date = new PageObject(params, stepIndex,"event_start_date"); 
		event_end_date = new PageObject(params, stepIndex,"event_end_date"); 
		registration_start_date = new PageObject(params, stepIndex,"registration_start_date"); 
		registration_end_date = new PageObject(params, stepIndex,"registration_end_date");

		wave_name = new PageObject(params, stepIndex, "wave_name"); 
		wave_language = new PageObject(params, stepIndex, "wave_language"); 
		wave_export_id = new PageObject(params, stepIndex, "wave_export_id"); 
		wave_is_this_wave_private = new PageObject(params, stepIndex, "wave_is_this_wave_private"); 
		wave_start_date = new PageObject(params, stepIndex, "wave_start_date");
		wave_registrant_block = new PageObject(params, stepIndex, "wave_registrant_block"); 
		wave_team_block = new PageObject(params, stepIndex, "wave_team_block"); 
		wave_starting_registrant_no = new PageObject(params, stepIndex, "wave_starting_registrant_no");

		
		eventstartmonth = new PageObject(params, stepIndex, "eventstartmonth"); 
		eventstartday = new PageObject(params, stepIndex, "eventstartday"); 
		eventstartyear = new PageObject(params, stepIndex, "eventstartyear"); 
		eventstarthour = new PageObject(params, stepIndex, "eventstarthour"); 
		eventstartminute = new PageObject(params, stepIndex, "eventstartminute");
		eventstartperiod = new PageObject(params, stepIndex, "eventstartperiod"); 
		
		eventendmonth = new PageObject(params, stepIndex, "eventendmonth"); 
		eventendday = new PageObject(params, stepIndex, "eventendday"); 
		eventendyear = new PageObject(params, stepIndex, "eventendyear");
		
		eventendhour = new PageObject(params, stepIndex, "eventendhour"); 
		eventendminute = new PageObject(params, stepIndex, "eventendminute"); 
		eventendperiod = new PageObject(params, stepIndex, "eventendperiod"); 
		eventregstartmonth = new PageObject(params, stepIndex, "eventregstartmonth"); 
		eventregstartday = new PageObject(params, stepIndex, "eventregstartday");
		eventregstartyear = new PageObject(params, stepIndex, "eventregstartyear"); 
		eventregstarthour = new PageObject(params, stepIndex, "eventregstarthour"); 
		eventregstartminute = new PageObject(params, stepIndex, "eventregstartminute");
		
		eventregstartperiod = new PageObject(params, stepIndex, "eventregstartperiod"); 
		eventregendmonth = new PageObject(params, stepIndex, "eventregendmonth"); 
		eventregendday = new PageObject(params, stepIndex, "eventregendday"); 
		eventregendyear = new PageObject(params, stepIndex, "eventregendyear"); 
		eventregendhour = new PageObject(params, stepIndex, "eventregendhour");
		eventregendminute = new PageObject(params, stepIndex, "eventregendminute"); 
		eventregendperiod = new PageObject(params, stepIndex, "eventregendperiod"); 
		
		wavestartday = new PageObject(params, stepIndex, "wavestartday");
		wavestartmonth = new PageObject(params, stepIndex, "wavestartmonth");
		wavestartyear = new PageObject(params, stepIndex, "wavestartyear");
		wavestarthour = new PageObject(params, stepIndex, "wavestarthour");
		wavestartminute = new PageObject(params, stepIndex, "wavestartminute");
		wavestartperiod = new PageObject(params, stepIndex, "wavestartperiod");
		
	}
	
//	private void waveParseDate(PageObject waveStartDate) throws InvalidInputException 
//	{
//		Pattern datePattern = Pattern.compile("^(\\d+{2})/(\\d+{2})/(\\d{4}) (1[012]|[1-9]):([0-5][0-9]) (?i)(AM|PM)$");
//
//		if(waveStartDate != null && !waveStartDate.value().isEmpty())
//		{
//			Matcher startMatcher = datePattern.matcher(waveStartDate.value());
//			if (!startMatcher.find()) {
//				throw new InvalidInputException(waveStartDate.value());
//			}
//			wavestartday = startMatcher.group(1);
//			wavestartmonth = DateTimeUtils.getMonthName(Integer.parseInt(startMatcher.group(2)));
//			wavestartyear = startMatcher.group(3);
//			
//			wavestarthour = startMatcher.group(4);
//			wavestartminute = startMatcher.group(5);
//			wavestartperiod = startMatcher.group(6);
//		}
//	}

	@Override
	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException, VerificationException, InvalidInputException {
//		if (PageType.EVENT_LOCATIONS.equals(nextPage)) {
//			performInteraction(driver, OperationType.CLICK,"",  new By.ById("buttonSubmit"), null, null);
//			if(isWaveOrLocationAction == true){
//				verify(driver, "feedback", new By.ById("feedback"), "Location Updated Successfully", null);
//			}
//		}
//		else 
		if(PageType.EVENT_LOCATION_DETAILS.equals(nextPage)){
			performInteraction(driver, OperationType.CLICK,"",  new By.ById("buttonSubmit"), null, null);
			if(isWaveOrLocationAction == true){
				verify(driver, "feedback", new By.ById("feedback"), "Location Updated Successfully", null);
			}
		}
	}

	@Override
	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.SETUP_LOCATION_EVENT_DETAILS)){
			setUpLocationEventDetails(driver);
		}
		else if(actionType.equals(ActionType.CREATE_WAVE)){
			createWave(driver);
		}
		
	}

	private void setUpLocationEventDetails(WebDriver driver) throws SeleniumException, VerificationException, InvalidInputException {
		isWaveOrLocationAction = true;
		performInteraction(driver, OperationType.TYPE, "Fundraising Goal", new By.ById("ucEventLocationDetail_textboxFundraisingGoal"), fundraising_goal, EventSetUpGenerator.fundraisingGoal());
		
		performInteraction(driver, OperationType.SELECT, "Event Start Month", new By.ById("ucEventLocationDetail_dropdownlistStartMonth"), eventstartmonth, LocationSetUpGenerator.eventstartmonth());
		performInteraction(driver, OperationType.SELECT, "Event Start Day", new By.ById("ucEventLocationDetail_dropdownlistStartDay"), eventstartday, LocationSetUpGenerator.eventstartday());
		performInteraction(driver, OperationType.SELECT, "Event Start Year", new By.ById("ucEventLocationDetail_dropdownlistStartYear"), eventstartyear, LocationSetUpGenerator.eventstartyear());
		performInteraction(driver, OperationType.SELECT, "Event Start Hour", new By.ById("ucEventLocationDetail_dropdownlistStartHour"), eventstarthour, LocationSetUpGenerator.eventstarthour());
		performInteraction(driver, OperationType.SELECT, "Event Start Minute", new By.ById("ucEventLocationDetail_dropdownlistStartMinute"), eventstartminute, LocationSetUpGenerator.eventstartminute());
		
		if (eventstartperiod.value().equals("AM") || eventstartperiod.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Event Start AM", new By.ById("ucEventLocationDetail_radiobuttonStartAM"), null, null);
		} else if (eventstartperiod.value().equals("PM")){
			performInteraction(driver, OperationType.CLICK, "Event Start PM", new By.ById("ucEventLocationDetail_radiobuttonStartPM"), null, null);
		}
		
		performInteraction(driver, OperationType.SELECT, "Event End Month", new By.ById("ucEventLocationDetail_dropdownlistEndMonth"), eventendmonth, LocationSetUpGenerator.eventendmonth());
		performInteraction(driver, OperationType.SELECT, "Event End Day", new By.ById("ucEventLocationDetail_dropdownlistEndDay"), eventendday, LocationSetUpGenerator.eventendday());
		performInteraction(driver, OperationType.SELECT, "Event End Year", new By.ById("ucEventLocationDetail_dropdownlistEndYear"), eventendyear, LocationSetUpGenerator.eventendyear());
		performInteraction(driver, OperationType.SELECT, "Event End Hour", new By.ById("ucEventLocationDetail_dropdownlistEndHour"), eventendhour, LocationSetUpGenerator.eventendhour());
		performInteraction(driver, OperationType.SELECT, "Event End Minute", new By.ById("ucEventLocationDetail_dropdownlistEndMinute"), eventendminute, LocationSetUpGenerator.eventendminute());
		
		if (eventendperiod.value().equals("AM") || eventendperiod.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Event End AM", new By.ById("ucEventLocationDetail_radiobuttonEndAM"),null, null);
		} else if (eventendperiod.value().equals("PM")){
			performInteraction(driver, OperationType.CLICK, "Event End PM", new By.ById("ucEventLocationDetail_radiobuttonEndPM"), null, null);
		}
		
		performInteraction(driver, OperationType.SELECT, "Registration Start Month", new By.ById("ucEventLocationDetail_dropdownlistRegStartMonth"), eventregstartmonth, LocationSetUpGenerator.eventregstartmonth());
		performInteraction(driver, OperationType.SELECT, "Registration Start Day", new By.ById("ucEventLocationDetail_dropdownlistRegStartDay"), eventregstartday, LocationSetUpGenerator.eventregstartday());
		performInteraction(driver, OperationType.SELECT, "Registration Start Year", new By.ById("ucEventLocationDetail_dropdownlistRegStartYear"), eventregstartyear, LocationSetUpGenerator.eventregstartyear());
		performInteraction(driver, OperationType.SELECT, "Registration Start Hour", new By.ById("ucEventLocationDetail_dropdownlistRegStartHour"), eventregstarthour, LocationSetUpGenerator.eventregstarthour());
		performInteraction(driver, OperationType.SELECT, "Registration Start Minute", new By.ById("ucEventLocationDetail_dropdownlistRegStartMinute"), eventregstartminute, LocationSetUpGenerator.eventregstartminute());
		
		if (eventregstartperiod.value().equals("AM") || eventregstartperiod.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Registration Start AM", new By.ById("ucEventLocationDetail_radiobuttonRegStartAM"), null, null);
		} else if (eventregstartperiod.value().equals("PM")){
			performInteraction(driver, OperationType.CLICK, "Registration Start PM", new By.ById("ucEventLocationDetail_radiobuttonRegStartPM"), null, null);
		}
		
		performInteraction(driver, OperationType.SELECT, "Registration End Month", new By.ById("ucEventLocationDetail_dropdownlistRegEndMonth"), eventregendmonth, LocationSetUpGenerator.eventregendmonth());
		performInteraction(driver, OperationType.SELECT, "Registration End Day", new By.ById("ucEventLocationDetail_dropdownlistRegEndDay"), eventregendday, LocationSetUpGenerator.eventregendday());
		performInteraction(driver, OperationType.SELECT, "Registration End Year", new By.ById("ucEventLocationDetail_dropdownlistRegEndYear"), eventregendyear, LocationSetUpGenerator.eventregendyear());
		performInteraction(driver, OperationType.SELECT, "Registration End Hour", new By.ById("ucEventLocationDetail_dropdownlistRegEndHour"), eventregendhour, LocationSetUpGenerator.eventregendhour());
		performInteraction(driver, OperationType.SELECT, "Registration End Minute", new By.ById("ucEventLocationDetail_dropdownlistRegEndMinute"), eventregendminute, LocationSetUpGenerator.eventregendminute());
		
		if (eventregendperiod.value().equals("AM") || eventregendperiod.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Registration End AM", new By.ById("ucEventLocationDetail_radiobuttonRegEndAM"), null, null);
		} else if (eventregendperiod.value().equals("PM")){
			performInteraction(driver, OperationType.CLICK, "Registration End PM", new By.ById("ucEventLocationDetail_radiobuttonRegEndPM"), null, null);
		}
		
		
//		verify(driver, "feedback", new By.ById("feedback"), "Location Updated Successfully", null);
	}
	
	private void createWave(WebDriver driver) throws SeleniumException, VerificationException, InvalidInputException {
		isWaveOrLocationAction = true;
		performInteraction(driver, OperationType.TYPE, "Wave Name", new By.ById("ucEventLocationWave_textboxEventLcoationWaveName"), wave_name, WaveGenerator.wave_name());
		performInteraction(driver, OperationType.SELECT, "Wave Language", new By.ById("ucEventLocationWave_ucSelectLanguage_dropdownlistLanguages"), wave_language, WaveGenerator.wave_language());
		performInteraction(driver, OperationType.TYPE, "Wave Export ID", new By.ById("ucEventLocationWave_textboxEventLocationWaveExportID"), wave_export_id, WaveGenerator.wave_export_id());
		performInteraction(driver, OperationType.CLICK, "Is this wave private", new By.ById("ucEventLocationWave_checkboxEventLocationWaveIsPrivate"), wave_is_this_wave_private, "yes");
				
		performInteraction(driver, OperationType.SELECT, "Wave Start Month", new By.ById("ucEventLocationWave_dropdownlistStartMonth"), wavestartmonth, WaveGenerator.wavestartmonth());
		performInteraction(driver, OperationType.SELECT, "Wave Start Day", new By.ById("ucEventLocationWave_dropdownlistStartDay"), wavestartday, WaveGenerator.wavestartday());
		performInteraction(driver, OperationType.SELECT, "Wave Start Year", new By.ById("ucEventLocationWave_dropdownlistStartYear"), wavestartyear, WaveGenerator.wavestartyear());
		performInteraction(driver, OperationType.SELECT, "Wave Start Hour", new By.ById("ucEventLocationWave_dropdownlistStartHour"), wavestarthour, WaveGenerator.wavestarthour());
		performInteraction(driver, OperationType.SELECT, "Wave Start Minute", new By.ById("ucEventLocationWave_dropdownlistStartMinute"), wavestartminute, WaveGenerator.wavestartminute());
		
		if (wavestartperiod.value().equals("AM") || wavestartperiod.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Wave Start AM", new By.ById("ucEventLocationWave_radiobuttonStartAM"), null, null);
		} else if (wavestartperiod.equals("PM")){
			performInteraction(driver, OperationType.CLICK, "Wave Start PM", new By.ById("ucEventLocationWave_radiobuttonStartPM"), null, null);
		}
					
		performInteraction(driver, OperationType.TYPE, "Registrant Block", new By.ById("ucEventLocationWave_textboxEventLcoationWaveMaxNumParticipants"), wave_registrant_block, WaveGenerator.wave_registrant_block());
		performInteraction(driver, OperationType.TYPE, "Team Block", new By.ById("ucEventLocationWave_textboxEventLcoationWaveTeamReserveInvites"), wave_team_block, WaveGenerator.wave_team_block());
		performInteraction(driver, OperationType.TYPE, "Starting Registrant #", new By.ById("ucEventLocationWave_textboxEventLcoationWaveParticipantStartNum"), wave_starting_registrant_no, WaveGenerator.wave_starting_registrant_no());
		
		performInteraction(driver, OperationType.CLICK, "Submit Button", new By.ById("ucEventLocationWave_buttonAddWave"), null, null);
		
//		verify(driver, "feedback", new By.ById("feedback"), "Location Updated Successfully", null);

		
	}

}
