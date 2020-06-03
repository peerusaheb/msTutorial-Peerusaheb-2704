package com.ms.boot.Convertcurrencyms.model;

import javax.validation.constraints.Null;

public class ConvertCurrencyRequest2 {
	
	private Integer countryCode;
	
	
		
	public ConvertCurrencyRequest2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConvertCurrencyRequest2(Integer countryCode) {
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
