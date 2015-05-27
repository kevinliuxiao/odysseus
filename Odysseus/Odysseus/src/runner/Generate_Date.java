//package runner;
//
////This is sample program to print all the dates between range of start and end dates
//import java.util.Date;
//import java.util.Random;
//
//public class Generate_Date 
//{
//  public static String searchBetweenDates(String startStringDate, String endStringDate) 
//  {
//	  try
//	  {
//		  java.util.Date startDate = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(startStringDate);
//		  java.util.Date endDate = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(endStringDate);
//    	    
//	      java.util.Date begin = new Date(startDate.getTime());
//	      java.util.LinkedList list = new java.util.LinkedList();
//	      //list.add(new Date(begin.getTime()));
//	
//	      int count = 86400000;
//	      while(begin.compareTo(endDate)<0){
//	          begin = new Date(begin.getTime() + count);
//	          list.add(new Date(begin.getTime()));
//	      }
//
//	      list.remove(list.size() -1);
//	      Random rand = new Random();
//	      int i = rand.nextInt(((list.size()) - 1) + 1) + 1;
//	      String str = new java.text.SimpleDateFormat("dd/MM/yyyy").format(((java.util.Date)list.get(i-1)));
//	      return str;
//	  }
//	  catch(Exception e)
//	  {
//		  return "";
//	  }
//  }
//
//  public static void main(String[] args) throws Exception   
//  {   
////      java.util.Scanner input = new java.util.Scanner(System.in);
////      System.out.println("Enter the Start Date: dd/mm/yyyy");
////      String begin = new String();
////      begin = input.nextLine();
//
//      String begin = "20/10/2015";
//      
////      System.out.println("Enter the End Date: dd/mm/yyyy");
////      String end = new String();
////      end = input.nextLine();
//      
//      String end = "25/10/2015";
//
//     String date = searchBetweenDates(begin , end);
//
//     System.out.println(date);
//     
////      String[] comboDates = new String[hitList.size()];
////      for(int i=0; i<hitList.size(); i++)
////          comboDates[i] = new java.text.SimpleDateFormat("dd/MM/yyyy").format(((java.util.Date)hitList.get(i)));
////
////      for(int i=0; i<comboDates.length; i++)
////          System.out.println(comboDates[i]);
//
////      input.close();
//  }
//}
