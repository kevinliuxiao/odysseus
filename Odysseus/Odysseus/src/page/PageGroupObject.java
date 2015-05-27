package page;

import java.util.ArrayList;
import java.util.Hashtable;
import util.XMLFileReader;
import exception.InvalidInputException;

public class PageGroupObject
{
	private ArrayList<Hashtable<String, PageObject>> list = new ArrayList<Hashtable<String, PageObject>>(); 
	private PageObject defaultPageObj;
	private String groupName;
	
	public PageGroupObject(XMLFileReader params, String groupName, int stepIndex, String...tags) throws InvalidInputException {
		
		defaultPageObj = new PageObject(params, stepIndex, "");
		this.groupName = groupName;
		int index = 1;
		while(params.isTagPathPresent(stepIndex, groupName + index))
		{
			Hashtable<String, PageObject> keys = new Hashtable<String, PageObject>();
			for(String tag : tags)
			{
				String key = groupName + index + "/" + tag;
				String input = params.getInput(stepIndex, key).trim();
				PageObject pageObj = new PageObject(params, stepIndex, key, input);
				keys.put(pageObj.key(), pageObj);
			}
			list.add(keys);
			index++;
		}
	}
	
//	public PageGroupObject(XMLFileReader params, String groupName, int stepIndex, String...tags) throws InvalidInputException {
//		
//		defaultPageObj = new PageObject(params, stepIndex, "");
//		this.groupName = groupName;
//		int index = 1;
//		while(params.isTagPathPresent(stepIndex, groupName + index))
//		{
//			Hashtable<String, PageObject> keys = new Hashtable<String, PageObject>();
//			for(String tag : tags)
//			{
//				String input = params.getInput(stepIndex, groupName + index + "/" + tag).trim();
//				PageObject pageObj = new PageObject(params, stepIndex, tag, input);
//				keys.put(pageObj.key(), pageObj);
//			}
//			list.add(keys);
//			index++;
//		}
//	}
	
	public int size()
	{
		return list.size();
	} 
	
	public PageObject value(int groupIndex, String tag)
	{
		try
		{
			Hashtable<String, PageObject> keys = list.get(groupIndex -1);
			String key = groupName + groupIndex + "/" + tag;
			if(keys.get(key) == null)
			{
				return defaultPageObj;
			}
			return keys.get(key);
		}
		catch(Exception e)
		{
			return defaultPageObj;
		}
	} 

}
