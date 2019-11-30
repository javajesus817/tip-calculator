package com.tipcalculators.main;

import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TipCalculatorApp {
	static Scanner sc;
	static TipCalculatorDAO bill;
	static DecimalFormat df2;

	public static void main(String[] args) {
		double billSubtotal = 0;
		String serviceRating = "";
		
		askForBillAndService(billSubtotal, serviceRating);
		
	}
	
	
	protected static double calcTip(double billSubtotal, String serviceRating) {
		bill = new TipCalculatorDAO();
		double tip = 0;
		double totalTipAmount = 0;
		
		switch (serviceRating) {
		case "good":
			tip = bill.getGoodServiceTip();
			break;
		case "fair":
			tip = bill.getFairServiceTip();
			break;
		case "bad":
			tip = bill.getBadServiceTip();
			break;
		}
		
		totalTipAmount = billSubtotal * tip;
		
		return totalTipAmount;
		
	}
	
	protected static double getDefaultTipPercentage(String serviceRating) {
		bill = new TipCalculatorDAO();
		double tip = 0;
		
		switch (serviceRating) {
		case "good":
			tip = bill.getGoodServiceTip();
			break;
		case "fair":
			tip = bill.getFairServiceTip();
			break;
		case "bad":
			tip = bill.getBadServiceTip();
			break;
		}
		
		return tip;
	}
	
	protected static double setCustomTipPercentage (String serviceRating, double tip) {
		bill = new TipCalculatorDAO();
		
		
		switch(serviceRating) {
		case "good":
			bill.setGoodServiceTip(tip);
			break;
		case "fair":
			bill.setFairServiceTip(tip);
			break;
		case "bad":
			bill.setBadServiceTip(tip);
			break;
		}
		
		return tip;
		
	}
	
	protected static double calcCustomTip(double billSubtotal, String serviceRating, double tip) {
		double totalTipAmount = billSubtotal * setCustomTipPercentage(serviceRating, tip);
		
		return totalTipAmount;
	}
	
	protected static double calcTotalBill(double billSubtotal, double tip) {
		double totalBill;
		totalBill = billSubtotal + tip;
		
		return totalBill;
	}
	
	
	
	protected static void askForBillAndService(double billSubtotal, String serviceRating) {
		sc = new Scanner(System.in);
		String answerChoice = "";
		double tip;
		double userInputForTip;
		df2 = new DecimalFormat("#.00");
		df2.setRoundingMode(RoundingMode.HALF_UP);
		
		do {
			System.out.println("Please enter your bill's subtotal ($USD): " );
			billSubtotal = sc.nextDouble();
		} while (billSubtotal < 0);
		
		do {
			System.out.println("Please enter your suggested service rating (good, fair, or bad): ");
			serviceRating = sc.next().toLowerCase();
		} while(!(serviceRating.equals("good") || serviceRating.equals("fair") || serviceRating.equals("bad")));
		
		System.out.println("Based on your service selection of '" + serviceRating + "', the suggested tip percentage is " + df2.format((getDefaultTipPercentage(serviceRating) * 100)) + "%");
		
		do {
			System.out.println("Continue with this percentage? (Y/N): ");
			answerChoice = sc.next();
		} while(!(answerChoice.toUpperCase().equals("Y") || answerChoice.toUpperCase().equals("N")));
		
		if(answerChoice.toUpperCase().equals("Y")) {
			System.out.println("Please tip your server $" + df2.format(calcTip(billSubtotal, serviceRating)));
		} else {
			do {
				System.out.println("Please enter a new tip percentage (in whole numbers > 0): ");
				userInputForTip = sc.nextDouble();
				tip = userInputForTip / 100;
			} while(userInputForTip < 0);
			
			System.out.println("Your custom tip percentage is " + df2.format((setCustomTipPercentage(serviceRating, tip) * 100)) + 
					"%. Please tip your server $" + df2.format(calcCustomTip(billSubtotal, serviceRating, tip)) + ", making your total bill $" 
					+ df2.format(calcTotalBill(billSubtotal, userInputForTip)));
		}
		
		sc.close();
	}
	
	

}
