package com.ms.boot.Convertcurrencyms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.ms.boot.Convertcurrencyms.model.ManageCurrencyRequest;
import com.ms.boot.Convertcurrencyms.model.ManageCurrencyRequest2;
import com.ms.boot.Convertcurrencyms.model.ManageCurrencyResponse;

@FeignClient(name = "manageCurrencyms", fallback = ManageCurrencyServiceFallback.class)
public interface Managecurrencyserviceproxy {
	
	@RequestMapping(value = "/convertCurrency", method = RequestMethod.POST) //TODO : this mapping is not required
	public ManageCurrencyResponse getConversionFactor(ManageCurrencyRequest2 cRequest);

}
