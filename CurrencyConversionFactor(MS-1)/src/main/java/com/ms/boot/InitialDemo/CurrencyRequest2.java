package com.ms.boot.InitialDemo;



public class CurrencyRequest2 {
	
	private Integer countryCode;
	
	
		
	public CurrencyRequest2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurrencyRequest2(Integer countryCode) {
		super();
		this.countryCode = countryCode;
		
		
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public String toString()
	{
		return "countryCode:"+getCountryCode();
	}
	
	
	

}
