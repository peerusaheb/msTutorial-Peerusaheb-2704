package com.ms.boot.Convertcurrencyms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "convertcurrency")
public class ConvertCurrency {

	@Id
	private Integer countryCode;
	private double amount;
	private double resultAmount;
	
	

	public ConvertCurrency( Integer countryCode,double amount,double resultAmount) {
		super();
		
		this.countryCode = countryCode;
		this.amount = amount;
		this.resultAmount = resultAmount;
	}

	public ConvertCurrency() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	public double getResultAmount() {
		return resultAmount;
	}

	public void setResultAmount(double resultAmount) {
		this.resultAmount = resultAmount;
	}
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString()
	{
		return "{countryCode:"+countryCode+" amount:"+getAmount()+"resultAmount:"+getResultAmount()+"}";
	}
	
	

}
