package com.ibm.ms.currencyexchangeservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repo;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangevalue(@PathVariable String from,@PathVariable String to)
	{
		//ExchangeValue exchangeValue = new ExchangeValue(1000l,from,to,BigDecimal.valueOf(75));
		ExchangeValue exchangeValue = repo.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	
	@PostMapping("/convert")
	public ExchangeResponse convert(@RequestBody ExchangeRequest obj)
	{
		ExchangeValue exchangeValue = repo.findByFromAndTo(obj.getCountryName(),"INR");
		ExchangeResponse res = new ExchangeResponse();
		if(exchangeValue != null)
		{
			res.setId(exchangeValue.getId());
			res.setFrom(exchangeValue.getFrom());
			res.setTo(exchangeValue.getTo());
			res.setConversionFactor(exchangeValue.getConversionFactor());
			res.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		}
		else
		{
			System.out.println("ExchangeResponse is null");
		}
		/*ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setId(obj.getCountryCode());
		exchangeValue.setFrom(obj.getCountryName());
		exchangeValue.setTo("INR");
		exchangeValue.setConversionFactor(BigDecimal.valueOf(obj.getConversionfactor())); 
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		repo.save(exchangeValue);*/
		return res;
		
	}
	
	@GetMapping("/currency-exchange-all")
	public List<ExchangeValue> retrieveAllExchangevalues()
	{
		List<ExchangeValue> exchangeValueCltn = repo.findAll();
		return exchangeValueCltn;
	}
	
	@PostMapping("/currency-exchange-add")
	public ExchangeValue addExchangevalue(@RequestBody AddRequest obj)
	{
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setId(obj.getCountryCode());
		exchangeValue.setFrom(obj.getCountryName());
		exchangeValue.setTo("INR");
		exchangeValue.setConversionFactor(BigDecimal.valueOf(obj.getConversionfactor()));
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		repo.save(exchangeValue);
		return exchangeValue;
		
	}
	
	@PostMapping("/currency-exchange-update")
	public ExchangeValue updateExchangevalue(@RequestBody AddRequest obj)
	{
		Optional<ExchangeValue> oExchangeValue = repo.findById(obj.getCountryCode());
		if( oExchangeValue.isPresent())
		{  
			ExchangeValue exchangeValue = oExchangeValue.get();
			exchangeValue.setId(obj.getCountryCode());
			exchangeValue.setFrom(obj.getCountryName());
			exchangeValue.setTo("INR");
			exchangeValue.setConversionFactor(BigDecimal.valueOf(obj.getConversionfactor()));
			exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			repo.save(exchangeValue);
			repo.flush();
			return exchangeValue;
		}
		else
		{
			System.out.println("Entry Not found to update");
			return null;
		}
		
		
	}

}
