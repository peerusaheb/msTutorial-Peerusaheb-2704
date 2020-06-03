package com.ms.boot.Convertcurrencyms.model;

public class ManageCurrencyRequest {
	
	private Integer countryCode;
	private double factor;
	
		
	public ManageCurrencyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManageCurrencyRequest(Integer countryCode, double  factor) {
		super();
		this.countryCode = countryCode;
		this.factor = factor;
		
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	
	public String toString()
	{
		return "countryCode:"+getCountryCode()+";"+"factor:"+getFactor();
	}
	
	
}
