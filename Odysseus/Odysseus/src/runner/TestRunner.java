package runner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import javax.swing.UIManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import controller.Controller;
import ui.component.FileTree;
import util.Logger;
import util.WebUtils;
import enumeration.*;
import exception.InvalidInputException;
import util.XMLFileReader;

public class TestRunner
{

//	public static int EventLocationsCounter = 0;
	
	public static final String ERROR_LOGGING = "Error creating the system log file. Printing system logs to screen.";
	
	private static int failureCount, successCount, errorCount, totalCount;
	private static String zipResultsName;
	private static String systemName;
	private static String browser = BrowserType.FIREFOX.toString();
	private static boolean recursive = false;
	private static String testDir;
	private static ArtezInstance testInstance;
	private static String userAgent;
	public static File screenShotFolder;
	private static BrowserType browserType;
	private static String systemType;
//	private static DateFormat EndTimeFormat;
//	private static Date time;
	private static Properties reuseProps;
	protected static XMLFileReader params;
	
	//add Kevin's own FF plugins for ease of debugging
	//public static final String FIREBUG_PATH  = "C:\\FFProfile\\firebug.xpi";
	//public static final String FIREXPATH_PATH = "C:\\FFProfile\\FireXPath.xpi";

	public static Logger logger;
	public static WebDriver driver;
	public static int implicitWaitTime = 60;
	
	public static  long getEndTime()
	{
		return System.currentTimeMillis();
	}

	public static File[] chooseFiles(String[] args) throws IllegalArgumentException{
		File[] cases;
		
		if (!(testDir == null)){
			File f = new File(testDir);
			if (f != null && f.isDirectory()) {
				if (recursive) {
					cases = FileUtils.convertFileCollectionToFileArray(FileUtils.listFiles(f, null, true));
				} else {
					cases = f.listFiles();
				}
			} else {
				throw new IllegalArgumentException("Test case directory is invalid");
			}			
		} else {
			
			Collection<File> files;
			FileTree fileTree = new FileTree(new File("./TestCases"));
			
			files = fileTree.getSelectedFiles();
			
			while (files == null)
			{
					files = fileTree.getSelectedFiles();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			cases = FileUtils.convertFileCollectionToFileArray(files); 
		}
		
		return cases;
	}

	public static void runTests(File[] cases)  
	{

		logger = new Logger();

		if (cases.length <= 0) {
			return;
		}

		totalCount = 0;
		successCount = 0;
		errorCount = 0;
		failureCount = 0;
		
/*
		FirefoxProfile profile = new FirefoxProfile();
		if (userAgent != null) {
			profile.setPreference("general.useragent.override", userAgent);
		}
*/
		// Use the following line to change the User Agent for mobile testing. Find other User Agent strings online for other platforms/devices.
		//profile.setPreference("general.useragent.override", "Mozilla/5.0 (Linux; U; Android 2.1-update1; de-de; E10i Build/2.0.2.A.0.24) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
		
		

		// adding FF extensions before FF starts   
		  /* try {
			profile.addExtension(new File(FIREBUG_PATH));
			profile.addExtension(new File(FIREXPATH_PATH));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		   
		
//		driver = new FirefoxDriver(profile);	
		WebUtils.setImplicitWait(driver,  implicitWaitTime);
		logger.ProgressLog(0, 0, 0, cases.length);

		// passing the test cases and setting up the logger
		for (File c : cases) 
		{		
			try 
			{	
				reuseProps = new Properties();
				
				System.out.println("==Test File: " + c.getPath() + "==");
				totalCount ++;
				logger.addCase(c.getPath());
				
				try 
				{
					params = new XMLFileReader(c);
					Controller a = new Controller(params, testInstance);
					if (a.perform(driver)) 
					{
						logger.setResult(ResultType.SUCCESS);
						System.out.println("SUCCESS");
						successCount ++;
					} else {
						logger.setResult(ResultType.FAILURE);
						System.out.println("FAILURE");
						failureCount ++;
					}
				}
				catch ( InvalidInputException e) 
				{
						// System level errors are handled here (errors that happen outside/in between test cases.)
						System.err.println(e.getMessage());
						System.out.println("ERROR");
						logger.setResult(ResultType.ERROR);
						logger.addLogInfo(OperationType.SYSTEM_ERROR, "SYSTEM_ERROR","", e.getMessage(), "", ResultType.ERROR);
						errorCount ++;
				} 
				
				logger.ProgressLog(successCount, failureCount, errorCount, cases.length);

			} catch (RuntimeException rte) {
				driver.quit();
				throw rte;
			} finally {
				System.out.println();
			}
			
//			resetClassCounters();

		}

		driver.quit();
		

		System.out.println("*****SUMMARY*****");
		System.out.println("Total: " + totalCount);
		System.out.println("Success: " + successCount);
		System.out.println("Failure: " + failureCount);
		System.out.println("Error: " + errorCount);

		File results = new File("logs/results.html");
		try {
			logger.writeResultsToFile(results);
			logger.zipResults(results, zipResultsName);
		} catch (IOException e) {
			System.err.println("Error writing results to file. File : " + results.getName());
		}

	}
	
	
	
	
	public static void parseArgs(String[] args) throws IllegalArgumentException, InvalidInputException {
		
		String errorMessage = "Usage: testingSite [-directory [-r] testDirectory] [-z zipName] [-s system] [-u userAgent] [-b browser]";
		try {
			if (args[0].startsWith("-")) {
				System.err.println(errorMessage);
				throw new IllegalArgumentException();
			}
			testInstance = ArtezInstance.fromString(args[0]);
			
			int i = 1;
			while (i < args.length) {
				if (args[i].equals("-directory")) {
					i++;
	            	if (args[i].equals("-r")) {
	            		recursive = true;
	            		i++;
	            	} else recursive = false;
	            	testDir = args[i];
	            	i++;
	            	while (i < args.length &&!args[i].startsWith("-")) {
	            		testDir = testDir + " " + args[i];
	            		i++;
	            	}
	            } else if(args[i].equals("-z")) {
					i++;
					zipResultsName = args[i];
					i++;
					while (i < args.length && !args[i].startsWith("-")) {
						zipResultsName = zipResultsName + " " + args[i];
	            		i++;
	            	}
				} else if(args[i].equals("-s")) {
					i++;
					systemName = args[i];
					i++;
					while (i < args.length && !args[i].startsWith("-")) {
						systemName = systemName + " " + args[i];
	            		i++;
	            	}
				} else if(args[i].equals("-u")) {
					i++;
					userAgent = args[i];
					i++;
					while (i < args.length && !args[i].startsWith("-")) {
						userAgent = userAgent + " " + args[i];
	            		i++;
	            	}
				} 
				else if(args[i].equals("-b")) 
				{
					i++;
					browser = args[i];
					i++;
					while (i < args.length && !args[i].startsWith("-")) {
						browser = browser + " " + args[i];
	            		i++;
	            	}
				}
				else {
					System.err.println(errorMessage);
					throw new IllegalArgumentException();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(errorMessage);
			throw new IllegalArgumentException();
		}
		
	}

	public static void main(String[] args) {
		
		 try 
		 {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 systemType = System.getProperty("os.arch");  // Note: This checks the JRE version either 64 or 32bit (Not the actual system type architecture)
	
			DateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date time = new Date();
	
			File logFolder = new File("logs");
			if (!logFolder.isDirectory()) {
				logFolder.mkdirs();
			}
			
			screenShotFolder = new File("logs/Screenshots");
			if(screenShotFolder.exists())
			{
				File[] listSelectFile = screenShotFolder.listFiles();
				for(File c : listSelectFile)
				{
					c.delete();
				}
			}

			if (!screenShotFolder.isDirectory()) {
				screenShotFolder.mkdirs();
			}
			
			File file  = new File("logs/sys" + dateFormat.format(time) + ".log");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.err.println(ERROR_LOGGING);
				}
			}
			PrintStream printStream;
	
			try {
				printStream = new PrintStream(new FileOutputStream(file, true));
				System.setOut(printStream);
				System.setErr(printStream);
			} catch (FileNotFoundException e) {
				System.err.println(ERROR_LOGGING);
			}
	
			System.out.println("\n#######" + timeFormat.format(time) + "######\n");
			try {
				if (!(args.length >= 1)) {
					throw new IllegalArgumentException();
				} else {
					parseArgs(args);
					File[] tests = chooseFiles(args);
					browserSelect();
					runTests(tests);
					if(failureCount > 0 || errorCount > 0) {
						System.exit(1);
					}
					System.exit(0);
				}
			} catch (IllegalArgumentException e) {
				System.err.println(e);
			}	
		 }
		 catch(Exception e)
		 {
				System.err.println(e);
		 }
		
		 killTask();
	}
	
	private static void browserSelect() 
	{
		try
		{				
			browserType = BrowserType.fromString(browser);
			DesiredCapabilities setBrowserType;
			Process proc;
			FirefoxProfile profile;
			
			File runFile = new File("libs/run.ini");	
			if (!runFile.exists()) {
				try {
					runFile.createNewFile();
				} catch (IOException e) {
					System.err.println("ERROR CREATING run.ini file");
				}
			}
			FileWriter fw = new FileWriter(runFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("InstallDirectoryPath=" + runFile.getAbsolutePath());
			
			switch (browserType) 
			{
				case CHROME:		
					setBrowserType = DesiredCapabilities.chrome();
					proc = Runtime.getRuntime().exec("libs/chromedriver.exe /INI=c:/libs/run.ini"); 
					System.out.println("Testing in Browser: "+ browser);					
					ChromeOptions options = new ChromeOptions();
					options.addArguments(new String[]{"test-type"});
					options.addArguments("--disable-extensions");
					setBrowserType.setCapability(ChromeOptions.CAPABILITY, options);				
					driver = new RemoteWebDriver(new URL("http://localhost:9515"), setBrowserType);
					driver = new Augmenter().augment(driver);
//					Runtime.getRuntime().exec("taskkill /IM chromedriver.exe");

					
					break;
				case FIREFOX:
					System.out.println("Testing in Browser: "+ browser);
					profile = new FirefoxProfile();	
					if (userAgent != null) 
					{
						profile.setPreference("general.useragent.override", userAgent);
					}
					driver = new FirefoxDriver(profile);	
					break;
				case INTERNET_EXPLORER:
					if(systemType.contains("64"))
					{
						System.out.println("System Type: " + systemType);
						
						setBrowserType = DesiredCapabilities.internetExplorer();
						proc = Runtime.getRuntime().exec("libs/IEDriverServer64.exe /INI=c:/libs/run.ini"); 
						System.out.println("Testing in Browser: "+ browser);
						driver = new RemoteWebDriver(new URL("http://localhost:5555"), setBrowserType);
						driver = new Augmenter().augment(driver);
	//					Runtime.getRuntime().exec("taskkill /IM IEDriverServer64.exe");
					}
					else
					{
						System.out.println("System Type: " + systemType);
						
						setBrowserType = DesiredCapabilities.internetExplorer();
						proc = Runtime.getRuntime().exec("libs/IEDriverServer32.exe /INI=c:/libs/run.ini"); 
						System.out.println("Testing in Browser: "+ browser);
						driver = new RemoteWebDriver(new URL("http://localhost:5555"), setBrowserType);
						driver = new Augmenter().augment(driver);
	//					Runtime.getRuntime().exec("taskkill /IM IEDriverServer64.exe");
					}
					break;
				case SAFARI:	
					System.out.println("Testing in Browser: "+ browser);
					driver = new SafariDriver();	
					break;
			}
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

	}
	
	private static void killTask() 
	{
		try
		{
			Process p;
			switch (browserType) 
			{
				case CHROME:
					p = Runtime.getRuntime().exec("taskkill /IM chromedriver.exe");
					p.waitFor();
					break;
				case INTERNET_EXPLORER:
					if(systemType.contains("64"))					
					{
						p = Runtime.getRuntime().exec("taskkill /IM IEDriverServer64.exe");
						p.waitFor();
					}
					else
					{
						p = Runtime.getRuntime().exec("taskkill /IM IEDriverServer32.exe");
						p.waitFor();
					}
					break;
			}
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
	}
	
	public static String getBrowser() 
	{
		return browserType.toString();
	}
/*	
	public static String getSite() 
	{
		return strSite;
	}
*/


	public static ArtezInstance getTestInstance() throws InvalidInputException 
	{
		return testInstance;
	}
	
	
	public static String getReuseValue(String key) 
	{		
		if(reuseProps.getProperty(key.toLowerCase()) == null){
			return "";
		}
		return reuseProps.getProperty(key.toLowerCase());
	}
	
	
	public static void setReuseValue(String key, String value) 
	{		
		if (reuseProps.containsKey(key.toLowerCase())) {
			System.out.println("WARNING: Key " + key + " already exists. The original value was: " + (String) reuseProps.getProperty(key) + " Now: " + value); 
		}
		if (value != null)
		{
			reuseProps.put(key.toLowerCase(), value);

		}
	}

/*	
	private static void resetClassCounters()
	{
		EventLocationsCounter = 0;
	}
*/	
}
