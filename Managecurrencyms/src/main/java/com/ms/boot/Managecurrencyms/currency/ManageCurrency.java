package com.ms.boot.Managecurrencyms.currency;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "managecurrency")
public class ManageCurrency {

	@Id
	private Integer countryCode;
	private double factor;
	
	public ManageCurrency( Integer countryCode,double factor) {
		super();
		
		this.countryCode = countryCode;
		this.factor = factor;
		
	}

	public ManageCurrency() {
		super();
		// TODO Auto-generated constructor stub
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
		return "{countryCode:"+countryCode+" factor:"+factor+"}";
	}
	
	

}
