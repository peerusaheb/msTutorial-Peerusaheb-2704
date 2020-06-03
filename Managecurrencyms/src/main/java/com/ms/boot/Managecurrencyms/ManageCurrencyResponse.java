package com.ms.boot.Managecurrencyms;



public class ManageCurrencyResponse {
	
	private Integer countryCode;
	private double factor;
	
		
	public ManageCurrencyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManageCurrencyResponse(Integer countryCode, double  factor) {
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
