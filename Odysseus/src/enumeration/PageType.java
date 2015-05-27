package enumeration;
import exception.InvalidInputException;


public enum PageType { 
	
	
	LOGIN("Login"), 
	START("Start"), 
	EVENT_SELECT("EventSelect"), 
	EVENT_SETUP_WIZARD("EventSetupWizard"),
	CREATE_EVENT_TYPE("CreateEventType"), 
	CREATE_EVENT_GENERAL("CreateEventGeneral"),
	
	CHANGE_PASSWORD("ChangePassword"),
	
	EVENT_SUMMARY("EventSummary"),
	EVENT_OPEN_CONFIRM("EventOpenConfirm"),
	EVENT_UPDATE("EventUpdate"),
	EVENT_SUMMARY_LINKS("EventSummaryLinks"),
	EVENT_SETTINGS("EventSettings"),
	
	EVENT_REG_TYPE_MANAGE("EventRegTypeManage"),
	EVENT_REG_TYPE_IND_SETTINGS("EventRegTypeIndSettings"),
	EVENT_REG_TYPE_IND_BIZ_RULES("EventRegTypeIndBizRules"),
	EVENT_REG_TYPE_IND_LOCATIONS("EventRegTypeIndLocations"),
	
	EVENT_REG_TYPE_TEAM_SETTINGS("EventRegTypeTeamSettings"),
	EVENT_REG_TYPE_TEAM_BIZ_RULES("EventRegTypeTeamBizRules"),
	EVENT_REG_TYPE_TEAM_LOCATIONS("EventRegTypeTeamLocations"),
	
	EVENT_LOCATIONS("EventLocations"),
	EVENT_LOCATION_DETAILS("EventLocationDetails"),
	EVENT_LOCATION_CREATE_CONTENT("EventLocationCreateContent"),
	EVENT_LOCATION_CREATE_DETAILS("EventLocationCreateDetails"),
	EVENT_LOCATION_RECEIPT_DETAILS("EventLocationReceiptDetails"),
	EVENT_LOCATION_UPDATE_DETAILS("EventLocationUpdateDetails"),
	
	EVENT_WAIVER("EventWaiver"),
	EVENT_USER_INTERFACE("EventUserInterface"),
	EVENT_USER_INTERFACE_HEADER("EventUserInterfaceHeader"),
	EVENT_ECARD_MANAGE("EventECardManage"),
	
	EVENT_PAPER_PLEDGE("EventPaperPledge"),
	EVENT_PAPER_PLEDGE_EDIT("EventPaperPledgeEdit"),
	
	EVENT_WIDGETS_MANAGE("EventWidgetsManage"),
	EVENT_SCOREBOARD_MANAGE("EventScoreBoardManage"),
	EVENT_SCOREBOARD_SETTINGS("EventScoreBoardSettings"),
	EVENT_DONOR_LISTING_SETTINGS("EventDonorListingSettings"),
	
	EVENT_THERMOMETERS_MANAGE("EventThermometersManage"),
	EVENT_THERMOMETER_SETTINGS("EventThermometersSettings"),
	EVENT_CHARITY_MESSAGES("EventCharityMessages"),
	EVENT_TICKETING_MANAGE("EventTicketingManage"),
	EVENT_TICKETING_DETAIL("EventTicketingDetail"),
	EVENT_TICKETING_ETICKET_EDIT("EventTicketingETicketEdit"),
	
	CORPORATE_TEAM_PAGE("CorporateTeamPage"),
	
	EVENT_EMAIL_LAYOUT_MANAGE("EventEmailLayoutManage"),
	EVENT_EMAIL_SETTINGS("EventEmailSettings"), 
	EVENT_EMAILS("EventEmails"),
	EVENT_EMAIL_MANAGE("EventEmailManage"),
	EVENT_EMAIL_PREFACE("EventEmailPreface"),
	
	EVENT_PAYMENT_TYPES("EventPaymentTypes"),
	EVENT_PAYMENT_TYPE_SUMMARY("EventPaymentTypeSummary"),
	EVENT_COUPON("EventCoupon"),
	
	EVENT_CLOSE_CONFIRM("EventCloseConfirm"),
	
	EVENT_LOCATION_UPDATE_CONTENT("EventLocationUpdateContent"),
	
	
	CONTENT_QUESTION_LIST("ContentQuestionList"), 	
	CONTENT_QUESTION_EDIT("ContentQuestionEdit"), 
	

	
	CONFIG_ADMINS("ConfigAdmins"),
	CONFIG_ORG_ELECTRONIC_PAYMENTS("ConfigOrgElectronicPayments"),
	CONFIG_ORG__PAYMENT_GATEWAY("ConfigOrgPayGateway"),
	CONFIG_ORG_MONTHLY_CREDITCARD("ConfigOrgMonthlyCreditCard"),
	CONFIG_ORG_MONTHLY_PLAN_CREATE("ConfigOrgMonthlyPlanCreate"),
	CONFIG_ORG_MONTHLY_PLAN_EDIT("ConfigOrgMonthlyPlanEdit"),
	CONFIG_ORG_BUNDLE_SUMMARY("ConfigOrgBundleSummary"),
	CONFIG_ORG_TAX_RECEIPTING("ConfigOrgTaxReceipting"),
	CONFIG_ORG_TAX_RECEIPT_EDIT("ConfigOrgTaxReceiptEdit"),
	CONFIG_ORG_TAX_RECEIPT_BLOCK("ConfigOrgTaxReceiptBlock"),
	CONFIG_ORG_TAX_RECEIPT_BUILD_TEMPLATE("ConfigOrgTaxReceiptBuildTemplate"),
	
	FIND_CONSTITUENT("FindConstituent"),
	CONSTITUENT("Constituent"),
	TAX_RECEIPT_SUMMARY("TaxReceiptSummary"),
	TAX_RECEIPT_DUPLICATE("TaxReceiptDuplicate"),
	
	DEBATCH_SELECT_BATCH("DEBatchSelectBatch"),
	DEBATCH_LIST_BATCHES("DEBatchListBatches"),
	DEBATCH_SETUP_BATCH("DEBatchSetupBatch"),
	DEBATCH_BATCH_SUMMARY("DEBatchSummary"),
	DEPLEDGE_ADD_EDIT_CONFIRM_PARTICIPANT("DEPledgeAddEditConfirmParticipant"),
	DEPLEDGE_ADD_EDIT_SPONSORS("DEPledgeAddEditSponsors"),
	
	DEFAULT("Default"),
	ORGANIZATIONS("Organizations"),
	ORGANIZATION_NEW("OrganizationNew"),
	ORGANIZATION("Organization"),
	ORGANIZATION_LICENSE("OrganizationLicense"),

	LOGIN_REGISTER("LoginRegister"),
	FORGET_PASSWORD("ForgetPassword"),
	MICRO_EVENT_SPONSOR_SEARCH("MicroEventSponsorSearch"),
	
	MOBILE_EVENT_INFO("mobileEventInfo"),
	
	REGV2("regV2"),
	CREATE_USER_ACCOUNT("CreateUserAccount"),
	CUSTOMIZE_PERSONAL_PAGE_REG("customizePersonalPageReg"),
	MY_PAGE_COMPOSING_REG("myPageComposerReg"),
	SOLICIT_OTHERS_REG("solicitOthersReg"),
	REGISTER_CONFIRMATION("RegisterConfirmation"),
	CREATE_USER_CONFIRM("CreateUserConfirm"),
	MY_EVENT_DETAILS_REG("myEventDetailsReg"),
	SEND_INVITATIONS_REG("SendInvitationsReg"),
	
	WELCOME("Welcome"),
	STATUS("Status"),
	OFFLINE_DONATION("offlineDonation"),
	PAY_OFFLINE_DONATIONS("PayOfflineDonations"),
	PAY_OFFLINE_DONATIONS_CONFIRM("PayOfflineDonationsConfirm"),
	USER_CONTACT_INFO("UserContactInfo"),
	SOLICIT_OTHERS("SolicitOthers"),
	TELL_A_FRIEND("tellAFriend"),
	VIEW_SPONSORS("ViewSponsors"),
	MANAGE_SPONSORS("ManageSponsors"),
	THANK_SPONSORS("thankSponsors"),
	THANK_SPONSORS_WITH_ECARD("ThankSponsorsWithECard"),
	DOWNLOAD_PLEDGE_FORM("downloadPledgeForm"),
	SEND_INVITATIONS("SendInvitations"),
	SELF_SPONSOR("SelfSponsor"),
	SELF_SPONSOR_CONFIRM("SelfSponsorConfirm"),
	PERSONAL_SETUP("PersonalSetup"),
	CUSTOMIZE_PERSONAL_PERSONAL_PAGE("customizePersonalPage"),
	MY_EVENT_DETAILS("MyEventDetails"),
	
	TEAM_SUMMARY("teamSummary"),
	SOLICIT_TEAM_MEMBERS("solicitTeamMembers"),
	VIEW_TEAM_SPONSORS("ViewTeamSponsors"),
	MANAGE_TEAM_SPONSORS("ManageTeamSponsors"),
	THANK_TEAM_SPONSORS("thankTeamSponsors"),
	TEAM_OFFLINE_DONATION("teamOfflineDonation"),
	
	PERSONAL_PAGE("PersonalPage"),
	TEAM_PAGE("TeamPage"),
	
	HOME("Home"),
	CHECK_RESULTS("CheckResults"),
	TEAM_RESULTS("TeamResults"),
	FUNDRAISING_PAGE("FundraisingPage"),
	TEAM_FUNDRAISING_PAGE("TeamFundraisingPage"),
	
	MOBILE_PERSONAL_PAGE("mobilePersonalPage"),
	MOBILE_TEAM_PAGE("mobileTeamPage"),

	DONATE("donate"),	
	DONATE_CONFIRM("donateConfirm"),
	DONATE_THANK_YOU("DonateThankYou"),
	SEND_ECARD("SendECard"), 
	SEND_ECARD_FINISHED("SendECardFinished"),
	
	MOBILE_DONATE("MobileDonate"),
	MOBILE_DONATE_CONFIRM("MobileDonateConfirm"),
	MOBILE_DONATE_THANK_YOU("MobileDonateThankYou"),

	TRIBUTE("tribute"),
	HONOR("honor"),
	MEMORIAM("memoriam"),
	SENDCARD("SendCard"),
	
	TICKETING_WELCOME("TicketingWelcome"),
	TICKETING_CATALOG("TicketingCatalog"),
	TICKETING_SUMMARY("TicketingSummary"),
	
	TESTPOST("testpost"),
	CONTENT_SELECT_PAGE("ContentSelectPage"), 
	
	VOLUNTEER_MANAGEMENT("VolunteerManagement"),
	EVENT_SUGGESTED_DONATION_AMOUNTS("EventSuggestedDonationAmounts"),
	EVENT_MEDIA_VIEWER_MANAGE("EventMediaViewerManage"),
	EVENT_LOCATION_TYPE_CREATE("EventLocationTypeCreate"),
	EVENT_LOCATION_TYPE_UPDATE("EventLocationTypeUpdate");


	private final String urlKeyword;
	
	PageType(String urlKeyword) {
		this.urlKeyword = urlKeyword;
	}
	
	public String urlKeyword() {
		return urlKeyword;
	}
	
	public static PageType fromString(String text) throws InvalidInputException {
		
		text = text.replaceAll("^\\s+", "");
		text = text.trim();
		
		if (text != null) {
	      for (PageType p : PageType.values()) {
	        if (text.equalsIgnoreCase(p.urlKeyword())) {
	          return p;
	        }
	      }
	    }
	    throw new InvalidInputException("pageType: '" + text + "' does not exist in PageType Class");
	  }
	
}
