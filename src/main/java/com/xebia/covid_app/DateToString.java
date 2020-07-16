package com.xebia.covid_app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateToString {
	
	
	public static void main(String args[]){  
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");  
        String strDate = dateFormat.format(date);  
        System.out.println("Converted String: " + strDate);  
         
}  

}
