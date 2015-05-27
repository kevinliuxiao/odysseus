package page;

import runner.TestRunner;
import enumeration.PageType;
import exception.InvalidInputException;
import util.XMLFileReader;

public class PageObject {

	private String input = "";
	private String key = "";
	

	public PageObject(XMLFileReader params, int stepIndex, String tag) throws InvalidInputException 
	{	
		key = tag;
		input = params.getInput(stepIndex, tag).trim();
		
//		if(input != null && !input.isEmpty())
//		{
//			TestRunner.setReuseValue(makeReuseKey(pageType, tag), input);
//		}
	}
	
	public PageObject(XMLFileReader params, int stepIndex, String tag, String input) throws InvalidInputException {	
		key = tag;
		if(input == null){
			this.input = "";
		}
		else{
			this.input = input;
		}
	}
	
	public String value()
	{
		return input;
	}
	
	public String key()
	{
		return key;
	}
	
//	private String makeReuseKey(PageType pageType, String tag)
//	{
//		return pageType.toString() + "." + tag;
//	}

}
