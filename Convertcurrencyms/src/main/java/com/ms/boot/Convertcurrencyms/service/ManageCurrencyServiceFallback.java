package com.ms.boot.Convertcurrencyms.service;

import org.springframework.stereotype.Component;

import com.ms.boot.Convertcurrencyms.model.ManageCurrencyRequest;
import com.ms.boot.Convertcurrencyms.model.ManageCurrencyRequest2;
import com.ms.boot.Convertcurrencyms.model.ManageCurrencyResponse;


@Component
public class ManageCurrencyServiceFallback implements Managecurrencyserviceproxy {
	
	@Override
	public ManageCurrencyResponse getConversionFactor(ManageCurrencyRequest2 dRequest) {

		return  new ManageCurrencyResponse(dRequest.getCountryCode(),0.0);
	}

}
