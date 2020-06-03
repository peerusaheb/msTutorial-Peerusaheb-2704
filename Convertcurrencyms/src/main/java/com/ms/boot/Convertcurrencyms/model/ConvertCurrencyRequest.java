package com.ms.boot.Convertcurrencyms.model;

import javax.validation.constraints.Null;

public class ConvertCurrencyRequest {
	
	private Integer countryCode;
	
	private double amount;
	
	public ConvertCurrencyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConvertCurrencyRequest(Integer countryCode, double  amount) {
		super();
		this.countryCode = countryCode;
		this.amount = amount;
		
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	public String toString()
	{
		return "countryCode:"+getCountryCode()+";"+"amount:"+getAmount();
	}
	
	
	

}
