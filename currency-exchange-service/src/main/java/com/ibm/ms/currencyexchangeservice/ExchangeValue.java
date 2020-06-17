package com.ibm.ms.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {
	
	@Id
	@Column(name ="Country_code")
	private long id;
	@Column(name ="Currency_from")
	private String from;
	@Column(name ="Currency_to")
	private String to;
	@Column(name ="Converion_factor")
	private BigDecimal conversionFactor;
	private int port;
	
	ExchangeValue(){}

	public ExchangeValue(long id, String from, String to, BigDecimal conversionFactor) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = conversionFactor;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}


	public void setConversionFactor(BigDecimal converisonFactor) {
		this.conversionFactor = converisonFactor;
	}
	
	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
	
	

}
