package generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.openqa.selenium.By.ById;

import enumeration.OperationType;

public class EventSetUpGenerator {
	
	private static String eventNameEn;
	private static String eventNameFr;
	private static String descriptionEn;
	private static String descriptionFr;
	
	private static String fundraisingGoal;
	private static String eventStartDate;
	private static String eventEndDate;
	
	private static String regStartDate;
	private static String regEndDate;
	private static String eventExportID;
	private static String paymentGateway;
	
	private static String eventStartTime;
	private static String eventEndTime;
	private static String regStartTime;
	private static String regEndTime;
	
	//=======================================================
	private static String locationName;
	private static String locationNamefr;
	private static String locationExportID;
	
	private static String senderName;
	private static String replyToEmail;
			
	
	private static String minimumDonationAmount;
	
	private static String processingFeePercentage;
	private static String processingFeeAmount;
	
	private static String taxReceiptsTemplete;
	private static String taxReceiptsTempleteFr;
	
	private static String registrationName;
	private static String registrationTypeDisplayName;
	private static String registrationTypeDisplayNameFr;
	private static String registrationTypeExportID;
	private static String registrationTypeDescriptionEn;
	private static String registrationTypeDescriptionFr;
	
	private static String registrationTypeFee;

	private static String waiverContentEn;
	private static String waiverContentFr;
	
	private static String headerEn;
	private static String headerFr;
	private static String footerEn;
	private static String footerFr;
	private static String mobileHeaderEn;
	private static String mobileHeaderFr;
	private static String mobileFooterEn;
	private static String mobileFooterFr;	
	
	
	private static void generate() 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    
	    String randomNum = String.valueOf(System.currentTimeMillis()/1000);
	    Random randGoal = new Random();
	    		
	    eventNameEn = "EVENT" + randomNum;
	    eventNameFr = "EVENTFR" + randomNum;
	    descriptionEn = eventNameEn + " description";
	    descriptionFr = eventNameFr + " description";
	    fundraisingGoal = String.valueOf(randGoal.nextInt((1000000 - 1) + 1) + 1);
	    eventStartDate = "20/10/2014";
	    eventEndDate = "20/10/2016";
	    regStartDate = "20/10/2014";
	    regEndDate = "20/10/2016";
	    eventExportID = eventNameEn + "EXPORTID";
	    paymentGateway = "TicketMaster Test";
	    
	    //================================================
		locationName = "locationName" + randomNum;
		locationNamefr = "locationNamefr" + randomNum;
		locationExportID = "locationExportID" + randomNum;
		
		senderName = "senderName" + randomNum;
		replyToEmail ="replyToEmail@google.fr";
				
		
		minimumDonationAmount = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);
		
		processingFeePercentage = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);
		processingFeeAmount = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);
		taxReceiptsTemplete = "ACCF-English";
		taxReceiptsTempleteFr = "ACCF-French";
		
		registrationName = "registrationName" + randomNum;
		registrationTypeDisplayName = "registrationTypeDisplayName" + randomNum;
		registrationTypeDisplayNameFr = "registrationTypeDisplayNameFr" + randomNum;
		registrationTypeExportID = "registrationTypeExportID" + randomNum;
		registrationTypeDescriptionEn = "registrationTypeDescriptionEn" + randomNum;
		registrationTypeDescriptionFr = "registrationTypeDescriptionFr" + randomNum; 
		
		registrationTypeFee = String.valueOf(randGoal.nextInt((100 - 1) + 1) + 1);

		waiverContentEn= "waiverContentEn" + randomNum; 
		waiverContentFr= "waiverContentFr" + randomNum; 
		
		headerEn= "header.png"; 
		headerFr= "footer.png"; 
		footerEn= "footer.png"; 
		footerFr= "footer.png"; 
		mobileHeaderEn= "mobile_header.png"; 
		mobileHeaderFr= "mobile_header.png"; 
		mobileFooterEn= "mobile_footer.png"; 
		mobileFooterFr= "mobile_footer.png"; 
		
		eventStartTime = "9:00am";
		eventEndTime = "10:00am";
		regStartTime = "11:00am";
		regEndTime = "12:00am";

	}

	private static String randomStringGenerator(int length) 
	{
	    SecureRandom random = new SecureRandom();
	    String rand = new BigInteger(130, random).toString(32);
	    return rand.substring(0, length);
	}
	
	public static String eventNameEn()
	{
		generate();
		return eventNameEn;
	}
	
	public static String eventNameFr()
	{
		generate();
		return eventNameFr;
	}
	
	public static String descriptionEn()
	{
		generate();
		return descriptionEn;
	}
	
	public static String descriptionFr()
	{
		generate();
		return descriptionFr;
	}
	public static String fundraisingGoal()
	{
		generate();
		return fundraisingGoal;
	}
	public static String eventStartDate()
	{
		generate();
		return eventStartDate;
	}
	public static String eventEndDate()
	{
		generate();
		return eventEndDate;
	}
	public static String regStartDate()
	{
		generate();
		return regStartDate;
	}
	public static String regEndDate()
	{
		generate();
		return regEndDate;
	}
	public static String eventExportID()
	{
		generate();
		return eventExportID;
	}
	public static String paymentGateway()
	{
		generate();
		return paymentGateway;
	}

	public static String locationName()
	{
		generate();
		return locationName;
	}
	
	public static String locationNamefr()
	{
		generate();
		return locationNamefr;
	}
	
	public static String locationExportID()
	{
		generate();
		return locationExportID;
	}
	
	public static String senderName()
	{
		generate();
		return senderName;
	}
	public static String replyToEmail()
	{
		generate();
		return replyToEmail;
	}
	public static String minimumDonationAmount()
	{
		generate();
		return minimumDonationAmount;
	}
	public static String processingFeePercentage()
	{
		generate();
		return processingFeePercentage;
	}
	public static String processingFeeAmount()
	{
		generate();
		return processingFeeAmount;
	}
	public static String taxReceiptsTemplete()
	{
		generate();
		return taxReceiptsTemplete;
	}
	public static String taxReceiptsTempleteFr()
	{
		generate();
		return taxReceiptsTempleteFr;
	}
	public static String registrationName()
	{
		generate();
		return registrationName;
	}
	
	public static String registrationTypeDisplayName()
	{
		generate();
		return registrationTypeDisplayName;
	}
	
	public static String registrationTypeDisplayNameFr()
	{
		generate();
		return registrationTypeDisplayNameFr;
	}
	
	public static String registrationTypeExportID()
	{
		generate();
		return registrationTypeExportID;
	}
	public static String registrationTypeDescriptionEn()
	{
		generate();
		return registrationTypeDescriptionEn;
	}
	public static String registrationTypeDescriptionFr()
	{
		generate();
		return registrationTypeDescriptionFr;
	}
	public static String registrationTypeFee()
	{
		generate();
		return registrationTypeFee;
	}
	public static String waiverContentEn()
	{
		generate();
		return waiverContentEn;
	}
	public static String waiverContentFr()
	{
		generate();
		return waiverContentFr;
	}

	public static String headerEn()
	{
		generate();
		return headerEn;
	}
	public static String headerFr()
	{
		generate();
		return headerFr;
	}
	public static String footerEn()
	{
		generate();
		return footerEn;
	}
	public static String footerFr()
	{
		generate();
		return footerFr;
	}
	public static String mobileHeaderEn()
	{
		generate();
		return mobileHeaderEn;
	}
	public static String mobileHeaderFr()
	{
		generate();
		return mobileHeaderFr;
	}
	public static String mobileFooterEn()
	{
		generate();
		return mobileFooterEn;
	}
	public static String mobileFooterFr()
	{
		generate();
		return mobileFooterFr;
	}
	public static String eventStartTime()
	{
		generate();
		return eventStartTime;
	}
	public static String eventEndTime()
	{
		generate();
		return eventEndTime;
	}
	public static String regStartTime()
	{
		generate();
		return regStartTime;
	}
	public static String regEndTime()
	{
		generate();
		return regEndTime;
	}
}
