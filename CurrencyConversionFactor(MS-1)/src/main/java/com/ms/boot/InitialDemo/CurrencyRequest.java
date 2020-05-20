package com.ms.boot.InitialDemo;



public class CurrencyRequest {
	
	private Integer countryCode;
	private double factor;
	
		
	public CurrencyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurrencyRequest(Integer countryCode, double  factor) {
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
