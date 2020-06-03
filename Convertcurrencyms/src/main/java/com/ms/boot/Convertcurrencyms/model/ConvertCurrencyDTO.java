package com.ms.boot.Convertcurrencyms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "convertcurrencydto")
public class ConvertCurrencyDTO {

	@Id
	private Integer countryCode;
	private double amount;
	private double resultAmount;
	private double factor;
	

	public ConvertCurrencyDTO( Integer countryCode,double amount,double resultAmount,double factor) {
		super();
		
		this.countryCode = countryCode;
		this.amount = amount;
		this.resultAmount = resultAmount;
		this.factor = factor;
	}

	public ConvertCurrencyDTO() {
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
	
	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	public String toString()
	{
		return "{countryCode:"+countryCode+" Factor:"+getFactor()+" amount:"+getAmount()+"resultAmount:"+getResultAmount()+"}";
	}
	
	

}
