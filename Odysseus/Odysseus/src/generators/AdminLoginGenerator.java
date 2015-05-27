package generators;

import exception.InvalidInputException;
import runner.TestRunner;

public class AdminLoginGenerator 
{
	public static String organizationID;
	private static String username;
	private static String password;
	
	public static void Generate() throws InvalidInputException
    {
		switch (TestRunner.getTestInstance()) {		
		case QA_MOBILE:		
			break;
		case QA:

			break;
		case SANDBOX:

			break;
		case QA_2012:

			break;
		case DEV_2012:

			break;
		case QA_2012_LOADBALANCED:
			organizationID = "automation";
			username = "artez";
			password = "S6ay0ut!";
			break;
		default:
			throw new InvalidInputException("Admin site: '" + TestRunner.getTestInstance()  + "' is not supported in AdminGenerator");
		}
		
    }
	
	public static String organizationID(){
		return organizationID;
	}
	
	public static String username(){
		return username;
	}
	
	public static String password(){
		return password;
	}

}
