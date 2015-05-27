package page.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import generators.EventSetUpGenerator;
import page.Page;
import page.PageObject;
import util.XMLFileReader;

public class EventSettings extends Page
{
	private PageObject enable_registrant_email_permission, enable_donor_email_permission, enable_registrant_postmail_permission;
	private PageObject enable_donor_postmail_permission, enable_registrant_search_consent_permission;
	private PageObject enable_display_event_information, enable_participant_msgboard, enable_personalized_pagename, enable_qas_validation, enable_direct_donation;
	
	private PageObject allow_organization_donations, enforce_minimun_donation_amount, minimum_donation_amount, allow_processing_fee, processing_fee_percentage, processing_fee_amount;
	private PageObject enable_organization_donations_taxreceipts, enable_location_taxreceipts, enable_individual_cash_cheque_lumpsum_donations;
	private PageObject enable_individual_cash_cheque_donations_receiptable, enable_individual_cash_cheque_lumpsum_donations_nonreceiptable;
	private PageObject enable_team_split,enable_team_donation, enable_donations_to_team_only, enable_team_cash_cheque_lumpsum_donations, enable_team_cash_cheque_donations_receiptable; 
	private PageObject enable_team_cash_cheque_lumpsum_donations_nonreceiptable, enable_payment_cash_cheque_pledges_by_creditcard; 
		
	private PageObject enable_mobile_optimized_fundraising, enable_mobile_app_promotion, itunes_store_link_en, itunes_store_link_fr, google_playstore_link_en, google_playstore_link_fr;
	private PageObject enable_mobile_direct_donation, enable_mobile_team_donations;
	
	private PageObject enable_enhanced_fundraising;
	
	
	private PageObject enable_account_verification_new_registrants, enable_friends_family_registration;
	private PageObject proxy_registrant_email_permission, proxy_registrant_postmail_permission, proxy_registrant_search_consent_permission;
	private PageObject proxy_registrant_create_account_permission, prepopulate_friend_family_address_during_registration, enable_self_sponsor_during_registration;
	private PageObject enable_self_sponsor_post_registration, enable_issue_tax_receipt_self_sponsor, enable_sponsor_search, enable_fundraising_goal;
	private PageObject enable_customize_personal_page_during_registration, enable_fundraising_during_registration, collect_emerg_contact,  enable_welcome_page, enable_previous_donor_address_book; 
	
	
	private PageObject enable_account_verification_team_members, enable_teams, restrict_team_registration_to_allow_only_joining;
	private PageObject restrict_team_registration_by_team_captain_location,  restrict_team_registration_by_teamcaptain_wave, make_teams_mandatory;	
	private PageObject enable_team_message_board, enable_team_page_public_message_board, enable_team_captain_report;
	private PageObject enable_team_member_listing_on_team_page, group_events;
	
	private String DEFAULTVALUE = "yes";
	
	public EventSettings(XMLFileReader params, int stepIndex) throws InvalidInputException{
		super(params,stepIndex);

		/************************  general ***************************/		
		enable_registrant_email_permission = new PageObject(params, stepIndex,"enable_registrant_email_permission");  
		enable_donor_email_permission = new PageObject(params, stepIndex,"enable_donor_email_permission");  
		enable_registrant_postmail_permission = new PageObject(params, stepIndex,"enable_registrant_postmail_permission"); 
		enable_donor_postmail_permission = new PageObject(params, stepIndex,"enable_donor_postmail_permission");  
		enable_registrant_search_consent_permission = new PageObject(params, stepIndex,"enable_registrant_search_consent_permission");  
		enable_display_event_information = new PageObject(params, stepIndex,"enable_display_event_information"); 
		enable_participant_msgboard = new PageObject(params, stepIndex,"enable_participant_msgboard"); 
		enable_personalized_pagename = new PageObject(params, stepIndex,"enable_personalized_pagename"); 
		enable_qas_validation = new PageObject(params, stepIndex,"enable_qas_validation");  
		enable_direct_donation = new PageObject(params, stepIndex,"enable_direct_donation"); 
		
/************************  donation ***************************/
		allow_organization_donations = new PageObject(params, stepIndex,"allow_organization_donations"); 
		enforce_minimun_donation_amount = new PageObject(params, stepIndex,"enforce_minimun_donation_amount"); 
		minimum_donation_amount = new PageObject(params, stepIndex,"minimum_donation_amount"); 
		allow_processing_fee = new PageObject(params, stepIndex,"allow_processing_fee"); 
		processing_fee_percentage = new PageObject(params, stepIndex,"processing_fee_percentage"); 
		processing_fee_amount = new PageObject(params, stepIndex,"processing_fee_amount");
		enable_organization_donations_taxreceipts = new PageObject(params, stepIndex,"enable_organization_donations_taxreceipts");
		enable_location_taxreceipts = new PageObject(params, stepIndex,"enable_location_taxreceipts"); 
		enable_individual_cash_cheque_lumpsum_donations = new PageObject(params, stepIndex,"enable_individual_cash_cheque_lumpsum_donations"); 
		enable_individual_cash_cheque_donations_receiptable = new PageObject(params, stepIndex,"enable_individual_cash_cheque_donations_receiptable"); 
		enable_individual_cash_cheque_lumpsum_donations_nonreceiptable = new PageObject(params, stepIndex,"enable_individual_cash_cheque_lumpsum_donations_nonreceiptable"); 
		enable_team_donation = new PageObject(params, stepIndex,"enable_team_donation"); 
		enable_team_split = new PageObject(params, stepIndex,"enable_team_split");
		enable_donations_to_team_only = new PageObject(params, stepIndex,"enable_donations_to_team_only");  
		enable_team_cash_cheque_lumpsum_donations = new PageObject(params, stepIndex,"enable_team_cash_cheque_lumpsum_donations"); 
		enable_team_cash_cheque_donations_receiptable = new PageObject(params, stepIndex,"enable_team_cash_cheque_donations_receiptable");  
		enable_team_cash_cheque_lumpsum_donations_nonreceiptable = new PageObject(params, stepIndex,"enable_team_cash_cheque_lumpsum_donations_nonreceiptable");   
		enable_payment_cash_cheque_pledges_by_creditcard = new PageObject(params, stepIndex,"enable_payment_cash_cheque_pledges_by_creditcard"); 
		
/************************  mobile fundraising settings ***************************/		
		enable_mobile_optimized_fundraising = new PageObject(params, stepIndex,"enable_mobile_optimized_fundraising"); 
		enable_mobile_app_promotion = new PageObject(params, stepIndex,"enable_mobile_app_promotion"); 	
		itunes_store_link_en = new PageObject(params, stepIndex,"itunes_store_link_en");
		itunes_store_link_fr = new PageObject(params, stepIndex,"itunes_store_link_fr");
		google_playstore_link_en = new PageObject(params, stepIndex,"google_playstore_link_en");
		google_playstore_link_fr = new PageObject(params, stepIndex,"google_playstore_link_fr");
		enable_mobile_direct_donation = new PageObject(params, stepIndex,"enable_mobile_direct_donation");  
		enable_mobile_team_donations = new PageObject(params, stepIndex,"enable_mobile_team_donations"); 

/************************  enhanced fundraising hub settings ***************************/		
		enable_enhanced_fundraising= new PageObject(params, stepIndex,"enable_enhanced_fundraising");    
		
/************************  registration - individual ***************************/			
		enable_account_verification_new_registrants = new PageObject(params, stepIndex,"enable_account_verification_new_registrants");  
		enable_friends_family_registration = new PageObject(params, stepIndex,"enable_friends_family_registration"); 	
		proxy_registrant_email_permission = new PageObject(params, stepIndex,"proxy_registrant_email_permission");  
		proxy_registrant_postmail_permission = new PageObject(params, stepIndex,"proxy_registrant_postmail_permission"); 
		proxy_registrant_search_consent_permission = new PageObject(params, stepIndex,"proxy_registrant_search_consent_permission");  
		proxy_registrant_create_account_permission = new PageObject(params, stepIndex,"proxy_registrant_create_account_permission");  
		prepopulate_friend_family_address_during_registration = new PageObject(params, stepIndex,"prepopulate_friend_family_address_during_registration");  
		enable_self_sponsor_during_registration = new PageObject(params, stepIndex,"enable_self_sponsor_during_registration");  
		enable_self_sponsor_post_registration = new PageObject(params, stepIndex,"enable_self_sponsor_post_registration");   
		enable_issue_tax_receipt_self_sponsor = new PageObject(params, stepIndex,"enable_issue_tax_receipt_self_sponsor");  
		enable_sponsor_search = new PageObject(params, stepIndex,"enable_sponsor_search");   
		enable_fundraising_goal = new PageObject(params, stepIndex,"enable_fundraising_goal");  
		enable_customize_personal_page_during_registration = new PageObject(params, stepIndex,"enable_customize_personal_page_during_registration");   
		enable_fundraising_during_registration = new PageObject(params, stepIndex,"enable_fundraising_during_registration");  
		collect_emerg_contact = new PageObject(params, stepIndex,"collect_emerg_contact"); 
		enable_welcome_page = new PageObject(params, stepIndex,"enable_welcome_page");  
		enable_previous_donor_address_book = new PageObject(params, stepIndex,"enable_previous_donor_address_book");  

/************************  registration - group ***************************/		
		enable_account_verification_team_members = new PageObject(params, stepIndex,"enable_account_verification_team_members"); 
		enable_teams = new PageObject(params, stepIndex,"enable_teams");  
		restrict_team_registration_to_allow_only_joining = new PageObject(params, stepIndex,"restrict_team_registration_to_allow_only_joining");  
		restrict_team_registration_by_team_captain_location = new PageObject(params, stepIndex,"restrict_team_registration_by_team_captain_location");  
		restrict_team_registration_by_teamcaptain_wave = new PageObject(params, stepIndex,"restrict_team_registration_by_teamcaptain_wave");  
		make_teams_mandatory = new PageObject(params, stepIndex,"make_teams_mandatory");  
		enable_team_message_board = new PageObject(params, stepIndex,"enable_team_message_board");  
		enable_team_page_public_message_board  = new PageObject(params, stepIndex,"enable_team_page_public_message_board");
		enable_team_captain_report  = new PageObject(params, stepIndex,"enable_team_captain_report");  
		enable_team_member_listing_on_team_page  = new PageObject(params, stepIndex,"enable_team_member_listing_on_team_page");  
		group_events  = new PageObject(params, stepIndex,"group_events");  

		
		this.nextPages.add(PageType.EVENT_SUMMARY);
		this.nextPages.add(PageType.EVENT_EMAILS);
		this.nextPages.add(PageType.EVENT_REG_TYPE_MANAGE);
		this.nextPages.add(PageType.EVENT_USER_INTERFACE);
		this.nextPages.add(PageType.EVENT_ECARD_MANAGE);
		this.nextPages.add(PageType.EVENT_PAPER_PLEDGE);
		this.nextPages.add(PageType.EVENT_TICKETING_MANAGE);
		this.nextPages.add(PageType.EVENT_SUMMARY_LINKS);
		this.nextPages.add(PageType.EVENT_PAYMENT_TYPES);
		this.nextPages.add(PageType.EVENT_LOCATIONS);
		this.nextPages.add(PageType.EVENT_WAIVER);
		this.nextPages.add(PageType.EVENT_WIDGETS_MANAGE);
		this.nextPages.add(PageType.VOLUNTEER_MANAGEMENT);
		this.nextPages.add(PageType.EVENT_SUGGESTED_DONATION_AMOUNTS);
		this.nextPages.add(PageType.CONTENT_QUESTION_LIST);
		this.nextPages.add(PageType.CONTENT_SELECT_PAGE);
		this.nextPages.add(PageType.EVENT_MEDIA_VIEWER_MANAGE);
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		if (PageType.EVENT_SUMMARY.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Submit Button", new By.ById("buttonSubmit"), null, null);
		}
		else if (PageType.EVENT_LOCATIONS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Locations", new By.ByLinkText("Locations"), null, null);
		}
		else if (PageType.EVENT_REG_TYPE_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Registrations", new By.ByLinkText("Registrations"), null, null);
		}
		else if (PageType.VOLUNTEER_MANAGEMENT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Volunteer Management", new By.ByLinkText("Volunteer Management"), null, null);
		}
		else if (PageType.EVENT_SUGGESTED_DONATION_AMOUNTS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Suggested Donation Amounts", new By.ByLinkText("Suggested Donation Amounts"), null, null);
		}
		else if (PageType.EVENT_WAIVER.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Waiver", new By.ByLinkText("Waiver"), null, null);
		}
		else if (PageType.EVENT_TICKETING_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Purchase Items", new By.ByLinkText("Purchase Items"), null, null);
		}
		else if (PageType.EVENT_PAPER_PLEDGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Downloadable Forms", new By.ByLinkText("Downloadable Forms"), null, null);
		}
		else if (PageType.CONTENT_QUESTION_LIST.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "User Defined Fields", new By.ByLinkText("User Defined Fields"), null, null);
		}
		else if (PageType.EVENT_WIDGETS_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Widgets", new By.ByLinkText("Widgets"), null, null);
		}
		else if (PageType.EVENT_ECARD_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Card Management", new By.ByLinkText("Card Management"), null, null);
		}
		else if (PageType.EVENT_EMAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Emails", new By.ByLinkText("Emails"), null, null);
		}
		else if (PageType.EVENT_PAYMENT_TYPES.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Payment Types", new By.ByLinkText("Payment Types"), null, null);
		}
		else if (PageType.CONTENT_SELECT_PAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Content", new By.ByLinkText("Content"), null, null);
		}
		else if (PageType.EVENT_MEDIA_VIEWER_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Media", new By.ByLinkText("Media"), null, null);
		}
		else if (PageType.EVENT_USER_INTERFACE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Style Settings", new By.ByLinkText("Style Settings"), null, null);
		}
		
	}

	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException {
		if(actionType.equals(ActionType.MET_EVENT_SETTING)){
			METEventSetting(driver);
		}
		else if(actionType.equals(ActionType.DD_EVENT_SETTING)){
			DDEventSetting(driver);
		}
		else if(actionType.equals(ActionType.TRIBUTE_EVENT_SETTING)){
			TRIBUTEEventSetting(driver);		
		}
		else if(actionType.equals(ActionType.PI_EVENT_SETTING)){
			PIEventSetting(driver);
		}
		else if(actionType.equals(ActionType.PET_EVENT_SETTING)){
			PETEventSetting(driver);
		}
		
	}
	
	private void METEventSetting(WebDriver driver) throws SeleniumException {

		/************************  general ***************************/
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: registrant email permission", new By.ById("checkboxDefaultContactByEmailParticipant"), enable_registrant_email_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: donor email permission", new By.ById("checkboxDefaultContactByEmailDonor"), enable_donor_email_permission,  DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: registrant post mail permission", new By.ById("checkboxDefaultContactByPostMailParticipant"), enable_registrant_postmail_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: donor post mail permission", new By.ById("checkboxDefaultContactByPostMailDonor"), enable_donor_postmail_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: registrant search consent permission", new By.ById("checkboxDefaultSearchConsent"), enable_registrant_search_consent_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "display the event information (within fundraising section)", new By.ById("checkboxEventInformation"), enable_display_event_information, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable participant message board", new By.ById("checkboxEnableRegMsgBoard"), enable_participant_msgboard, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable personalized page name (for personal and team page)", new By.ById("checkboxEnablePersonalizedPageName"), enable_personalized_pagename, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable qas address validation", new By.ById("checkboxEnableAddressValidation"), enable_qas_validation, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable direct donation", new By.ById("checkboxGeneralDirectDonation"), enable_direct_donation, DEFAULTVALUE);
		
		/************************  donation ***************************/
		performInteraction(driver, OperationType.TOGGLE, "allow for donations made by an organization", new By.ById("checkboxOrganizationDonationType"), allow_organization_donations, DEFAULTVALUE);
		
		performInteraction(driver, OperationType.TOGGLE, "enforce a minimum donation amount", new By.ById("checkboxOrganizationDonationType"), enforce_minimun_donation_amount, DEFAULTVALUE);
		
		performInteraction(driver, OperationType.TOGGLE, "allow donor to cover the processing fee", new By.ById("chkEnableDonorCoversProcessingFee"), allow_processing_fee, DEFAULTVALUE);
		performInteraction(driver, OperationType.TYPE, "% of donated amount ", new By.ById("txtProcessingFeePercentage"), processing_fee_percentage, EventSetUpGenerator.processingFeePercentage());
		performInteraction(driver, OperationType.TYPE, "amount to be added to the donated amount ", new By.ById("txtProcessingFeeAmount"), processing_fee_amount, EventSetUpGenerator.processingFeeAmount());
		performInteraction(driver, OperationType.TOGGLE, "enable tax receipts for organization donations", new By.ById("checkboxOrganizationTaxReceipts"), enable_organization_donations_taxreceipts, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable tax receipts by location (ie: set up a unique receipt per location)", new By.ById("checkboxTaxBundle"), enable_location_taxreceipts, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable individual cash, cheque & lump sum donations", new By.ById("checkboxOfflineDonations"), enable_individual_cash_cheque_lumpsum_donations, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable individual cash & cheque donations (receiptable)", new By.ById("checkboxIndividualDonation"), enable_individual_cash_cheque_donations_receiptable, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable individual cash & cheque lump sum donations (non-receiptable)", new By.ById("checkboxLumpSumDonation"), enable_individual_cash_cheque_lumpsum_donations_nonreceiptable, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team donations", new By.ById("checkboxTeamGroupDonationPage"), enable_team_donation, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "split team donations amongst team members", new By.ById("donationsForTeamMembers"), enable_team_split, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "assign donations to team only", new By.ById("donationsForTeam"), enable_donations_to_team_only, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team cash, cheque & lump sum donations", new By.ById("checkboxTeamGroupOfflineDonations"), enable_team_cash_cheque_lumpsum_donations, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team cash & cheque donations (receiptable)", new By.ById("checkboxTeamDonation"), enable_team_cash_cheque_donations_receiptable, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team cash & cheque lump sum donations (non-receiptable)", new By.ById("checkboxTeamLumpSumDonation"), enable_team_cash_cheque_lumpsum_donations_nonreceiptable, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable payment of cash & cheque pledges by credit card", new By.ById("checkboxPayInOfflineDonations"), enable_payment_cash_cheque_pledges_by_creditcard, DEFAULTVALUE);
		
		
		/************************  mobile fundraising settings ***************************/

		performInteraction(driver, OperationType.TOGGLE, "enable mobile optimized fundraising (includes donation and personal profile fundraising pages)", new By.ById("checkboxMobilePersonalPage"), enable_mobile_optimized_fundraising, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable mobile app promotion", new By.ById("checkboxMobileAppPromotion"), enable_mobile_app_promotion, DEFAULTVALUE);

		performInteraction(driver, OperationType.TYPE, "itunes store english link", new By.ById("itunesStoreLinkEn"), itunes_store_link_en, "http://store.apple.com/ca");
		performInteraction(driver, OperationType.TYPE, "itunes store french link", new By.ById("itunesStoreLinkFr"), itunes_store_link_fr, "http://store.apple.com/fr");
		

		performInteraction(driver, OperationType.TYPE, "google play store english link", new By.ById("androidMarketLinkEn"), google_playstore_link_en, "https://play.google.com/store?hl=en");
		performInteraction(driver, OperationType.TYPE, "google play store french link", new By.ById("androidMarketLinkFr"), google_playstore_link_fr, "https://play.google.com/store?hl=fr");
		
		performInteraction(driver, OperationType.TOGGLE, "enable mobile direct donation", new By.ById("checkboxMobileDirectDonation"), enable_mobile_direct_donation, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable mobile team donations", new By.ById("checkboxMobileTeamDonationPage"), enable_mobile_team_donations, DEFAULTVALUE);
		
		
		/************************  enhanced fundraising hub settings ***************************/	
		performInteraction(driver, OperationType.TOGGLE, "enable enhanced fundraising hub", new By.ById("checkboxFundraisingHub"), enable_enhanced_fundraising, DEFAULTVALUE);
		
		/************************  registration - individual ***************************/	
		performInteraction(driver, OperationType.TOGGLE, "enable account verification for new registrants", new By.ById("checkboxVerifyNewRegistrants"), enable_account_verification_new_registrants, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable friends & family registration", new By.ById("checkboxRegisterOthers"), enable_friends_family_registration, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: proxy registrant email permission", new By.ById("checkboxProxyByEmailParticipantDefault"), proxy_registrant_email_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: proxy registrant post mail permission", new By.ById("checkboxProxyByPostMailParticipantDefault"), proxy_registrant_postmail_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: proxy registrant search consent permission", new By.ById("checkboxProxySearchConsentDefault"), proxy_registrant_search_consent_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "set opted-in as the default for: proxy registrant create account permission", new By.ById("checkboxProxyCreateAccountDefault"), proxy_registrant_create_account_permission, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "prepopulate friend’s & family address during registration", new By.ById("checkboxProxyPrepopAddress"), prepopulate_friend_family_address_during_registration, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable self-sponsor: during registration", new By.ById("checkboxEnableDonationDuringRegistration"), enable_self_sponsor_during_registration, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable self-sponsor: post registration", new By.ById("checkboxSelfSponsor"), enable_self_sponsor_post_registration, DEFAULTVALUE);	
		performInteraction(driver, OperationType.TOGGLE, "issue tax receipt for self sponsor", new By.ById("checkboxTaxReceiptForSelfSponsor"), enable_issue_tax_receipt_self_sponsor, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable sponsor search", new By.ById("checkboxIndividualDonationPage"), enable_sponsor_search, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable fundraising goal", new By.ById("checkboxIndividualOnlineGoal"), enable_fundraising_goal, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable customize personal page during registration", new By.ById("checkboxCustomizePersonalPage"), enable_customize_personal_page_during_registration, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable fundraising during registration", new By.ById("checkboxSolicitOthersDuringRegistration"), enable_fundraising_during_registration, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "collect emergency contact information", new By.ById("checkboxEmergencyContact"), collect_emerg_contact, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable welcome page", new By.ById("checkboxWalkathonWelcomePage"), enable_welcome_page, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable previous donor address book", new By.ById("checkboxEnablePrevDonorAddressBook"), enable_previous_donor_address_book, DEFAULTVALUE);

		
		/************************  registration - group ***************************/		
		performInteraction(driver, OperationType.TOGGLE, "enable account verification for team members", new By.ById("checkboxWelcomeBackfromTeamPage"), enable_account_verification_team_members, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable teams", new By.ById("checkboxTeamGroupFunctionality"), enable_teams, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "restrict team registration to only allow joining (not creating) a team", new By.ById("checkboxTeamGroupsPreEntered"), restrict_team_registration_to_allow_only_joining, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "restrict team registration by team captain's location", new By.ById("checkboxTeamJoinLimitToCaptainLocation"), restrict_team_registration_by_team_captain_location, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "restrict team registration by team captain's wave", new By.ById("checkboxTeamJoinLimitToCaptainWave"), restrict_team_registration_by_teamcaptain_wave, DEFAULTVALUE);

		performInteraction(driver, OperationType.TOGGLE, "make teams mandatory", new By.ById("checkboxTeamGroupsRequired"), make_teams_mandatory, DEFAULTVALUE);	
		performInteraction(driver, OperationType.TOGGLE, "enable team message board", new By.ById("checkboxTeamMessageBoard"), enable_team_message_board, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team page public message board", new By.ById("checkboxTeamPagePublicMessageBoard"), enable_team_page_public_message_board, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team captain report", new By.ById("checkboxEnableTeamCaptainReport"), enable_team_captain_report, DEFAULTVALUE);
		performInteraction(driver, OperationType.TOGGLE, "enable team member listing on team page", new By.ById("checkboxEnableTeamPageMemberListing"), enable_team_member_listing_on_team_page, DEFAULTVALUE);
		performInteraction(driver, OperationType.SELECT, "group this event with", new By.ById("dropdownGroupEvents"), group_events, "No Grouping");
		
	}

	private void PETEventSetting(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	private void PIEventSetting(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	private void TRIBUTEEventSetting(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	private void DDEventSetting(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

}
