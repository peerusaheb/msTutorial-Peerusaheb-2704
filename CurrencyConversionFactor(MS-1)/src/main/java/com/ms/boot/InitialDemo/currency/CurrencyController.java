package com.ms.boot.InitialDemo.currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.boot.InitialDemo.CurrencyRequest;
import com.ms.boot.InitialDemo.CurrencyRequest2;



@RestController
@RequestMapping(path = "/currency")
public class CurrencyController {

	@Autowired
	private CurrencyPARepository repo;

	@RequestMapping(path = "/getAllConversionFactors", method = RequestMethod.GET)
	public List<Currency> getAllProducts() {
		return repo.findAll();
	}
	
	@RequestMapping(path = "/addConversionFactor", method = RequestMethod.POST)
	public void addConversionFactor(@RequestBody CurrencyRequest obj) {
		System.out.println("Json data = " + obj);
		repo.save(new Currency(obj.getCountryCode(), obj.getFactor()));
		
	}
	
	@RequestMapping(path = "/updateConversionFactor", method = RequestMethod.POST)
	public void updateConversionFactor(@RequestBody CurrencyRequest obj) {
		Currency oCurrency = repo.findByCountryCode(obj.getCountryCode());	
		
		if(oCurrency != null)
		{
			repo.save(new Currency(obj.getCountryCode(), obj.getFactor()));
			repo.flush();
		}
		else
		{
			System.out.println("Entry does not exits");
		}
		
	}

	@RequestMapping(path = "/getConversionFactor", method = RequestMethod.GET)
	public Currency  getConversionFactor(@RequestBody CurrencyRequest2 obj) {
		Currency oCurrency = repo.findByCountryCode(obj.getCountryCode());	
		
		if(oCurrency != null)
		{
			System.out.println("Currency from Database:"+oCurrency.toString());
			return oCurrency;
		}
		return null;
	}

}
