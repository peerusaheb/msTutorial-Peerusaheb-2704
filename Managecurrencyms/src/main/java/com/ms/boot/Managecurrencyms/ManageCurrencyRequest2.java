package com.ms.boot.Managecurrencyms;



public class ManageCurrencyRequest2 {
	
	private Integer countryCode;
	
	
		
	public ManageCurrencyRequest2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManageCurrencyRequest2(Integer countryCode) {
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
