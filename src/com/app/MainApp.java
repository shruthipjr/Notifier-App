package com.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


import com.util.XmlParser;

public class MainApp {
	
	 final static String url="http://rates.fxcm.com/RatesXML";
	 final static String xmlFileName="rates.xml";
	

	 private static String currencyPair="";
	 private static Float targetValue=0.0f;
	
	public static void main(String[] args) {
		
		Scanner kbReader= new Scanner(System.in);
		
		System.out.println("Enter the Currency Pair:");
		currencyPair = kbReader.next();
		
		System.out.println("You entered :"+currencyPair);
		
		System.out.println("Enter the Target Pair:");
		targetValue = Float.parseFloat(kbReader.next());
		
		System.out.println("You entered :"+targetValue);
		
		//Run the scheduler
		Timer timer = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
		timer.schedule(st, 0, 7000); // Create Repetitively task for every 7 secs
				
	}

	public static String getCurrencyPair() {
		return currencyPair;
	}

	public static void setCurrencyPair(String currencyPair) {
		MainApp.currencyPair = currencyPair;
	}

	public static Float getTargetValue() {
		return targetValue;
	}

	public static void setTargetValue(Float targetValue) {
		MainApp.targetValue = targetValue;
	}

}

 class ScheduledTask extends TimerTask{

	Date now; // to display current time
	
	@Override
	public void run() {
		
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
		
		try {
			XmlParser.processXml(new FileInputStream(new File(MainApp.xmlFileName)) , MainApp.getCurrencyPair(), MainApp.getTargetValue());
			
			//XmlParser.processXml(new URL(MainApp.url).openStream() , MainApp.getCurrencyPair(), MainApp.getTargetValue());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} /*catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
