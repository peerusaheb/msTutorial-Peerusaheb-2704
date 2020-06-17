package com.ibm.ms.currencyexchangeservice;

import javax.validation.constraints.Null;

public class ExchangeRequest {
	
	private long countryCode;
	@Null
	private String countryName;
	private double conversionfactor;
	
	public ExchangeRequest(long countryCode, String countryName, double conversionfactor) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.conversionfactor = conversionfactor;
	}

	public long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(long countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public double getConversionfactor() {
		return conversionfactor;
	}

	public void setConversionfactor(double conversionfactor) {
		this.conversionfactor = conversionfactor;
	}

}
