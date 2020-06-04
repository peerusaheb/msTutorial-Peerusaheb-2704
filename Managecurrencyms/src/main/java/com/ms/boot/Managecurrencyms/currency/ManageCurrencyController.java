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


@Controller
@RestController
@RequestMapping(path = "/currency")
public class ManageCurrencyController {

	@Autowired(required=true)
	private ManageCurrencyJPARepository repo;
	
	@RequestMapping(path = "/default", method = RequestMethod.GET)
	public String getGreeting() {
		return "This ManageCurrency Ms-1 Service";
	}

	@RequestMapping(path = "/getAllConversionFactors", method = RequestMethod.GET)
	public List<ManageCurrency> getAllProducts() {
		return repo.findAll();
	}
	
	@RequestMapping(path = "/addConversionFactor", method = RequestMethod.POST)
	public void addConversionFactor(@RequestBody ManageCurrencyRequest obj) {
		System.out.println("Json data = " + obj);
		repo.save(new ManageCurrency(obj.getCountryCode(), obj.getFactor()));
		
	}
	
	@RequestMapping(path = "/updateConversionFactor", method = RequestMethod.POST)
	public void updateConversionFactor(@RequestBody ManageCurrencyRequest obj) {
		ManageCurrency oCurrency = repo.findByCountryCode(obj.getCountryCode());	
		
		if(oCurrency != null)
		{
			repo.save(new ManageCurrency(obj.getCountryCode(), obj.getFactor()));
			repo.flush();
		}
		else
		{
			System.out.println("Entry does not exits");
		}
		
	}

	@RequestMapping(path = "/getConversionFactor/{countryCode}", method = RequestMethod.GET)
	public ManageCurrencyResponse  getConversionFactor1(@PathVariable Integer countryCode) {
		ManageCurrency oCurrency = repo.findByCountryCode(countryCode);	
		
		if(oCurrency != null)
		{
			System.out.println("Currency from Database:"+oCurrency.toString());
			return new ManageCurrencyResponse(oCurrency.getCountryCode(),oCurrency.getFactor());
		}
		return null;
	}
	

}
