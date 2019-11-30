package com.tipcalculators.main;

public class TipCalculatorDAO {
	private double goodService;// = 0.2;
	private double fairService;// = 0.15;
	private double badService;// = 0.1;
	
	public TipCalculatorDAO() {
		goodService = 0.2;
		fairService = 0.15;
		badService = 0.1;
	}
	
	public TipCalculatorDAO(double goodService, double fairService, double badService) {
		this.goodService = goodService;
		this.fairService = fairService;
		this.badService = badService;
	}
	
	public double getGoodServiceTip() {
		return this.goodService;
	}
	
	public double getFairServiceTip() {
		return this.fairService;
	}
	
	public double getBadServiceTip() {
		return this.badService;
	}
	
	public double setGoodServiceTip(double goodService) {
		return this.goodService;
	}
	
	public double setFairServiceTip(double fairService) {
		return this.fairService;
	}
	
	public double setBadServiceTip(double badService) {
		return this.badService;
	}
	
	
}
