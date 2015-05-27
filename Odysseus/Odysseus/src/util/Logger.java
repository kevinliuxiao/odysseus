package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import runner.TestRunner;
import enumeration.OperationType;
import enumeration.ResultType;


/**
 * @author czhang
 * 
 * This class is responsible for all log related tasks. It includes:
 * 
 * 1. Keeping track of all log information. The TestRunner has a global Logger object that is used throughout the entire testing process,
 * and all logs and results are stored in the same object.
 * 2. Output the logs in a html file.
 */
public class Logger extends TestRunner{
	private static int printCount= 0;
	private static final String OPERATION_TYPE= "Interaction";
	private static final String DESCRIPTION= "Description";
	private static final String ELEMENT_IDENTIFIER= "Object Identifier";
	private static final String TEST_VALUE= "Value";
	private static final String PAGE_URL= "Webpage URL";
	private static final String SCREENSHOT= "Failure Screenshot";

	

	private class CaseLog {

		private String caseName;
		private String fileName;
		private ResultType result;
		private List<LogInfo> details;

		public CaseLog(String fileName, String caseName) {
			this.caseName = caseName;
			this.fileName = fileName;
			this.result = ResultType.IN_PROGRESS;
			this.details = new ArrayList<LogInfo>();
		}

		public void setResult(ResultType result) {
			this.result = result;
		}
	}

	private class LogInfo {
		private OperationType type;
		private String description;
		private String target;
		private String value;
		private String currentUrl;
		private ResultType result;
		private String snapName;

		public LogInfo(OperationType preactionStart, String description, String target, String value, String currentUrl, ResultType result, String ... snapArray) {
			this.type = preactionStart;
			this.description = description;
			this.target = target;
			this.value = value;
			this.currentUrl = currentUrl;
			this.result = result;
			this.snapName = snapArray[0];
		}
	}

	private List<CaseLog> results;
	private CaseLog currentCase;
	DateFormat runDateFormat;
	DateFormat runTimeFormat;
	Date StartTime;
	Date time;
	long StartTimeMillis;
	long EndTimeMillis;
//	private XMLFileReader params;
	
	public CaseLog getCurrentCase() {
		return currentCase;
	}

	public void setCurrentCase(CaseLog currentCase) {
		this.currentCase = currentCase;
	}

	public Logger() {
		this.results = new ArrayList<CaseLog>();
		this.runDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		this.runTimeFormat = new SimpleDateFormat("HH:mm:ss");
		this.time = new Date();
		StartTimeMillis = System.currentTimeMillis();
		this.StartTime = new Date(StartTimeMillis);
	}

	public void addLogInfo(OperationType type, String description, String opTarget, String value, String currentUrl, ResultType result, String ... array) 
	{
		String SnapName = "";
		if(array.length > 0)
		{
			SnapName = array[0];
		}
		
		if (this.currentCase == null) {
			System.err.println("addOperation: Current case is null.");
		}

		LogInfo newOp = new LogInfo(type, description, opTarget, value, currentUrl, result, SnapName);

		this.currentCase.details.add(newOp);

	}

	public void addCase(String fileName) {
		
		int numCasesWithSameName = 0;
//		this.params = params;
		for (CaseLog c : this.results) {
			if (c.caseName.equals(fileName)) {
				numCasesWithSameName ++;
			}
		}
		
		CaseLog newCase;
		
		if (numCasesWithSameName > 0) {
			newCase = new CaseLog(fileName, fileName + " (" + numCasesWithSameName + ")");
		} else {
			newCase = new CaseLog(fileName, fileName);
		}
		this.results.add(newCase);
		this.currentCase = newCase;

	}

	public void setResult(ResultType result) {
		this.currentCase.setResult(result);
	}

	public void writeResultsToFile(File file) throws IOException {

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		writeHeader(bw);
		printSummary(bw);
		printCaseDetailTable(bw);
		writeFooter(bw);
		bw.close();
		fw.close();
	
	}
	
	public void zipResults(File file, String zipName) throws IOException {
		 FileInputStream in = new FileInputStream(file);
	        // out put file 
		 if (zipName == null || zipName.equals("")) zipName = "results";
		 
		 ZipOutputStream out = new ZipOutputStream(new FileOutputStream("logs/" + zipName +".zip"));
	         // name the file inside the zip  file 

		 out.putNextEntry(new ZipEntry("results.html")); 

		 byte[] b = new byte[1024];

		 int count;

		 while ((count = in.read(b)) > 0) {
			 System.out.println();

			 out.write(b, 0, count);
		 }
		 out.close();
		 in.close();
	}
	
	private void printSummary(BufferedWriter bw) throws IOException {
		writeLine(bw, "<table>");
		
		int totalCount = 0;
		int successCount = 0;
		int errorCount = 0;
		int failureCount = 0;
		
		for (CaseLog c : this.results) {
			totalCount ++;
			switch (c.result) {
			case SUCCESS:
				successCount ++;
				break;
			case FAILURE:
				failureCount ++;
				break;
			case ERROR:
				errorCount ++;
				break;
			default:
				break;
			}
		}
		
		if (totalCount == successCount) {
			writeLine(bw, "<tr class=  status_passed>");
			writeLine(bw, "<td><b>OVERALL RESULT:</b></td>");
			writeLine(bw, "<td><b>PASS</b></td>");
		} else {
			writeLine(bw, "<tr class=  status_failed>");
			writeLine(bw, "<td><b>OVERALL RESULT:</b></td>");
			writeLine(bw, "<td><b>FAIL</b></td>");
		}
		
		printSummaryRow(bw, "Total number of tests: ", totalCount);
		printSummaryRow(bw, "Total number of successes", successCount);
		printSummaryRow(bw, "Total number of failures: ", failureCount);
		printSummaryRow(bw, "Total number of errors: ", errorCount);
		
		printCaseListTable(bw);
		writeLine(bw, "</table>");
		
	}
	
	private void printCaseListTable(BufferedWriter bw) throws IOException {
		writeLine(bw, "<tr><td><table id=\"suiteTable\" class=\"selenium\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\">");
		writeLine(bw, "<tr class=\"title\"><td><b>Test Suite</b></td></tr>");
		
		int i = 0;
		for (CaseLog c : this.results) {
			if (ResultType.SUCCESS.equals(c.result)) {
				writeLine(bw, "<tr class=\"  status_passed\"><td><a href=\"#testresult" + i + "\">" + c.fileName + "</a></td></tr>");
			} else if (ResultType.FAILURE.equals(c.result) || ResultType.ERROR.equals(c.result)) {
				writeLine(bw, "<tr class=\"  status_failed\"><td><a href=\"#testresult" + i + "\">" + c.fileName + "</a></td></tr>");
			} else {
				writeLine(bw, "<tr><td><a href=\"#testresult" + i + "\">" + c.fileName + "</a></td></tr>");
			}
			
			i ++;
		}
		writeLine(bw, "</table></td></tr>");
	}
	
	private void printSummaryRow(BufferedWriter bw, String label, int count) throws IOException {
		writeLine(bw, "<tr>");
		writeLine(bw, "<td>" + label + "</td>");
		writeLine(bw, "<td>" + count + "</td>");
		writeLine(bw, "</tr>");
	}
	
	private void printCaseDetailTable(BufferedWriter bw) throws IOException {
		writeLine(bw, "<table>");
		
		int i = 0;
		for (CaseLog c: this.results) {
			if(i==0)
			{
				
			}
			printCaseDetails(bw, c, i);
			i ++;
		}
		writeLine(bw, "</table>");
	}
	
	private void printCaseDetails(BufferedWriter bw, CaseLog c, int i) throws IOException {
		//writeLine(bw, "<tr><td><a name=\"testresult" + i + "\">" + c.fileName + "</a><br/><div>");

		writeLine(bw, "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\">");
		writeLine(bw, "<thead>");
		if (ResultType.SUCCESS.equals(c.result)) {
			writeLine(bw, "<tr class=\"title status_passed\"><td rowspan=\"1\" colspan=\"4\">" + c.caseName + "</td></tr>");
		} else if (ResultType.FAILURE.equals(c.result) || ResultType.ERROR.equals(c.result)) {
			writeLine(bw, "<tr class=\"title status_failed\"><td rowspan=\"1\" colspan=\"4\">" + c.caseName + "</td></tr>");
			
		} else {
			writeLine(bw, "<tr class=\"title\"><td rowspan=\"1\" colspan=\"4\">" + c.caseName + "</td></tr>");
		}
		writeLine(bw, "</thead><tbody>");
		
		writeLine(bw, "\t<td><b>" + OPERATION_TYPE + "</b></td>");
		writeLine(bw, "\t<td><b>" + DESCRIPTION + "</b></td>");
		writeLine(bw, "\t<td><b>" + ELEMENT_IDENTIFIER + "</b></td>");
		writeLine(bw, "\t<td><b>" + TEST_VALUE + "</b></td>");
		writeLine(bw, "\t<td><b>" + PAGE_URL + "</b></td>");
		writeLine(bw, "\t<td><b>" + SCREENSHOT + "</b></td>");
		writeLine(bw, "</tr>");
		
		for (LogInfo o : c.details) {
			printOperation(o, bw);
		}
		
		writeLine(bw, "</tbody></table></div></td>");
		writeLine(bw, "<td>&nbsp;</td>");
		writeLine(bw, "</tr>");
	}
	
	private void printOperation(LogInfo o, BufferedWriter bw) throws IOException {
		printCount++;
		if (OperationType.PREACTION_START.equals(o.type) | OperationType.MAIN_ACTION_START.equals(o.type) | OperationType.REPORT_START.equals(o.type)) {
			writeLine(bw, "<tr class=\"  action_start\" style=\"cursor: pointer;\">");
		} else if (ResultType.SUCCESS.equals(o.result)) {
			writeLine(bw, "<tr class=\"  status_done\" style=\"cursor: pointer;\">");
		} else {
			writeLine(bw, "<tr class=\"  status_failed\" style=\"cursor: pointer;\">");
		}
	
		writeLine(bw, "\t<td>" + o.type + "</td>");
		writeLine(bw, "\t<td>" + o.description + "</td>");
		writeLine(bw, "\t<td>" + o.target + "</td>");
		writeLine(bw, "\t<td>" + o.value + "</td>");
		writeLine(bw, "\t<td>" + o.currentUrl + "</td>");
		if(o.snapName.equals("")|| o.snapName.equals(null))
		{
			writeLine(bw, "\t<td>" + o.snapName + "</td>");
		}
		else
		{
			String hrefShotLink = TestRunner.screenShotFolder.getAbsolutePath() + "/" + o.snapName;		
			writeLine(bw, "\t<td>" + "<a href=\"" + hrefShotLink + "\" \">" +  o.snapName + "</a></td>");
		}
		
		writeLine(bw, "</tr>");
		
	}
	
	private void writeHeader(BufferedWriter bw) throws IOException {
		writeLine(bw, "<html>");
		writeLine(bw, "<head><style type='text/css'>");
		writeLine(bw, "body, table {");
		writeLine(bw, "    font-family: Verdana, Arial, sans-serif;");
		writeLine(bw, "    font-size: 12;");
		writeLine(bw, "}");

		writeLine(bw, "table {");
		writeLine(bw, "    border-collapse: collapse;");
		writeLine(bw, "    border: 1px solid #ccc;");
		writeLine(bw, "}");

		writeLine(bw, "th, td {");
		writeLine(bw, "    padding-left: 0.3em;");
		writeLine(bw, "    padding-right: 0.3em;");
		writeLine(bw, "}");

		writeLine(bw, "a {");
		writeLine(bw, "    text-decoration: none;");
		writeLine(bw, "}");

		writeLine(bw, ".title {");
		writeLine(bw, "    font-style: italic;");
		writeLine(bw, "}");

		writeLine(bw, ".selected {");
		writeLine(bw, "    background-color: #ffffcc;");
		writeLine(bw, "}");
		
		writeLine(bw, ".action_start {");
		writeLine(bw, "    background-color: #eeeeee;");
		writeLine(bw, "}");
		
		writeLine(bw, ".status_done {");
		writeLine(bw, "    background-color: #eeffee;");
		writeLine(bw, "}");
		writeLine(bw, ".status_passed {");
		writeLine(bw, "    background-color: #ccffcc;");
		writeLine(bw, "}");

		writeLine(bw, ".status_failed {");
		writeLine(bw, "    background-color: #ffcccc;");
		writeLine(bw, "}");
		writeLine(bw, ".comment {");
		writeLine(bw, "	background-color: #F5F6CE;");
		writeLine(bw, "}");

		writeLine(bw, ".breakpoint {");
		writeLine(bw, "    background-color: #cccccc;");
		writeLine(bw, "    border: 1px solid black;");
		writeLine(bw, "}");
		writeLine(bw, "</style><title>Test suite results " + runDateFormat.format(time) + "</title></head>");
		writeLine(bw, "<body>");
		writeLine(bw, "<h1>Test suite results " + runDateFormat.format(time) + "</h1>");
		writeLine(bw, "<h1>Test Browser: " + TestRunner.getBrowser() + "</h1>");	
		
		EndTimeMillis = TestRunner.getEndTime();
		Date EndTime = new Date(EndTimeMillis);
		long TotalTimeMillis = EndTimeMillis - StartTimeMillis;
		
		writeLine(bw, "<h3>Start Time: " + runTimeFormat.format(StartTime) + "</h3>");
		writeLine(bw, "<h3>End Time: " + runTimeFormat.format(EndTime) + "</h3>");
		writeLine(bw, "<h3>Total Time: " + TotalTime(TotalTimeMillis)  + "</h3>");
	}
	
	private String TotalTime(long TotalTimeMillis)
	{
		int seconds = (int) (TotalTimeMillis / 1000) % 60 ;
		int minutes = (int) ((TotalTimeMillis / (1000*60)) % 60);
		int hours   = (int) ((TotalTimeMillis / (1000*60*60)) % 24);
		String hr = "Hrs ";
		String min = "Mins ";
		String sec = "Secs ";
		
		if(hours == 1)
		{
			hr = "Hr ";
		}
		if(minutes == 1)
		{
			min = "Min ";
		}
		if(seconds == 1)
		{
			sec = "Sec ";
		}
		
		if(hours != 0)
		{
			return hours + hr + minutes + min + seconds + sec;
		}
		else if(hours == 0 && minutes != 0)
		{
			return minutes + min + seconds + sec;
		}
		else if(hours == 0 && minutes == 0)
		{
			return seconds + sec;
		}
		else
		{
			return hours + hr + minutes + min + seconds + sec;
		}
		
	}

	
	private void writeFooter(BufferedWriter bw) throws IOException {
		writeLine(bw, "</body></html>");
	}
	
	private void writeLine(BufferedWriter bw, String line) throws IOException {
		bw.write(line + "\n");
	}
	
	public void ProgressLog(int pass, int fail, int error, int total) 
	{
//		DecimalFormat df = new DecimalFormat("#.##");
		pass = (int) Math.round((pass * 100.0)/total);
		fail = (int) Math.round((fail * 100.0)/total);
		error = (int) Math.round((error * 100.0)/total);
		int Tested = pass + fail + error;
		if(Tested > 100)
		{
			Tested = 100;
		}
		File chart = new File("logs/progress.html");	
		if (chart.exists() || !chart.exists()) {
			try {
				chart.createNewFile();
			} catch (IOException e) {
				System.err.println("ERROR CREATING chart file");
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(chart.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>");
			bw.write("<head>");
			bw.write("	<style> thead {color:green;}" /* tbody {color:blue;} tfoot {color:red;} */  + "table,th,td { border:1px solid black;}</style>");
			bw.write("</head>");
			bw.write("<body>");
			bw.write("<table>");
			bw.write("<thead><tr><th>Status</th><th>Percentage</th></tr></thead>");
			bw.write("<tfoot style=\"color:blue\"><tr><td>Total coverage</td><td>" + Tested + "%</td></tr></tfoot>");
			bw.write("<tbody>");
			bw.write("<tr style=\"color:green\"><td>SUCCESS</td><td>" + pass + "%</td></tr>");
			bw.write("<tr style=\"color:red\"><td>FAILURE</td><td>" + fail + "%</td></tr>");
			bw.write("<tr style=\"color:orange\"><td>ERROR</td><td>" + error + "%</td></tr>");
			bw.write("</tbody>");
			bw.write("</table>");			
			bw.write("</body>");
			bw.write("</html>");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
