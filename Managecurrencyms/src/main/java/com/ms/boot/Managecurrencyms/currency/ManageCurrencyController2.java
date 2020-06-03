package com.ms.boot.Managecurrencyms.currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ms.boot.Managecurrencyms.ManageCurrencyRequest;
import com.ms.boot.Managecurrencyms.ManageCurrencyRequest2;
import com.ms.boot.Managecurrencyms.ManageCurrencyResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RestController
public class ManageCurrencyController2 {
	private static Logger log = LoggerFactory.getLogger(ManageCurrencyController2.class);
	
	@Autowired(required=true)
	private ManageCurrencyJPARepository repo;

	@RequestMapping(value = "/convertCurrency", method = RequestMethod.POST)
	public ManageCurrencyResponse  getConversionFactor(@RequestBody ManageCurrencyRequest2 obj) {
		log.info(obj.toString());
		ManageCurrency oCurrency = repo.findByCountryCode(obj.getCountryCode());	
		
		if(oCurrency != null)
		{
			System.out.println("Currency from ManageCurrency Database:"+oCurrency.toString());
			return new ManageCurrencyResponse(oCurrency.getCountryCode(),oCurrency.getFactor());
		}
		return null;
	}

}
