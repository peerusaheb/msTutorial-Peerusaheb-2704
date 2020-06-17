package com.ibm.ms.currencyconversionservice;

import java.math.BigDecimal;

public class ExchangeResponse {
	
	private long id;
	
	private String from;
	
	private String to;
	
	private BigDecimal conversionFactor;
	private int port;
	
	ExchangeResponse(){}

	public ExchangeResponse(long id, String from, String to, BigDecimal conversionFactor) {
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
