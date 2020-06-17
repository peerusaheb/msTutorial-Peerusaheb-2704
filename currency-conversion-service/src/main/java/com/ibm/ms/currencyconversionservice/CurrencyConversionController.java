package com.ibm.ms.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.ms.currencyconversionservice.CurrencyExchangeServiceProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@Autowired

	private DiscoveryClient discoveryClient;

	@Autowired
	LoadBalancerClient lbClient;

	@Bean
	@LoadBalanced
	RestTemplate createRestTemplate() {
		RestTemplateBuilder b = new RestTemplateBuilder();
		return b.build();
	}

	@Autowired
	@Lazy
	RestTemplate lbrestTemplate;
	/*
	 * @Autowired private EurekaClient discoveryClient;
	 */
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity)
	{
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity =
		new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class,uriVariables);
		CurrencyConversionBean response = responseEntity.getBody();
		System.out.println("quantity::"+quantity);
		System.out.println("response.getConversionFactor()::"+response.getConversionFactor());
		return new CurrencyConversionBean(
				response.getId(),
				from, 
				to, 
				response.getConversionFactor(), 
				quantity, 
				quantity.multiply(response.getConversionFactor()), 
				response.getPort());
		
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity)
	{
		
		CurrencyConversionBean response = proxy.retrieveExchangevalue(from, to);
		System.out.println("quantity::"+quantity);
		System.out.println("Feign response--> response.getConversionFactor()::"+response.getConversionFactor());
		return new CurrencyConversionBean(
				response.getId(),
				from, 
				to, 
				response.getConversionFactor(), 
				quantity, 
				quantity.multiply(response.getConversionFactor()), 
				response.getPort());
		
	}
	
	@GetMapping("/currency-converter-feign/v01/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean applyConversion01(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		ExchangeRequest dRequest = new ExchangeRequest();
		dRequest.setCountryName(from);
		
		List<ServiceInstance> instances = discoveryClient.getInstances("currency-exchange-service");
		System.out.println("Instances of currency-exchange-service found =" + instances.size());
		for (ServiceInstance instance : instances) {
			System.out.println(instance.getHost() + ":" + instance.getPort());
		}

		ServiceInstance instance = instances.get(0);
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/convert";
		System.out.println("Calling URL :" + url);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<ExchangeRequest> addRequestHttpEntity = new HttpEntity<ExchangeRequest>(dRequest);
		ResponseEntity<ExchangeResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST,
				addRequestHttpEntity, ExchangeResponse.class);

		ExchangeResponse res = dResponseEntity.getBody();
		BigDecimal totalAmount = quantity.multiply( res.getConversionFactor());
		
		return new CurrencyConversionBean(res.getId(), res.getFrom(), res.getTo(), res.getConversionFactor(), quantity,
				totalAmount,res.getPort());

	}
	
	@GetMapping("/currency-converter-feign/v02/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean applyConversion02(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		ExchangeRequest dRequest = new ExchangeRequest();
		dRequest.setCountryName(from);
		ServiceInstance instance = lbClient.choose("currency-exchange-service");
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/convert";
		System.out.println("Calling URL :" + url);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<ExchangeRequest> discountHttpEntity = new HttpEntity<ExchangeRequest>(dRequest);
		ResponseEntity<ExchangeResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST,
				discountHttpEntity, ExchangeResponse.class);

		ExchangeResponse res = dResponseEntity.getBody();
		BigDecimal totalAmount = quantity.multiply( res.getConversionFactor());
		
		return new CurrencyConversionBean(res.getId(), res.getFrom(), res.getTo(), res.getConversionFactor(), quantity,
				totalAmount,res.getPort());

	}
	
	@GetMapping("/currency-converter-feign/v03/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean applyConversion03(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		ExchangeRequest dRequest = new ExchangeRequest();
		dRequest.setCountryName(from);

		HttpEntity<ExchangeRequest> discountHttpEntity = new HttpEntity<ExchangeRequest>(dRequest);

		ResponseEntity<ExchangeResponse> dResponseEntity = lbrestTemplate.exchange("http://currency-exchange-service/convert",
				HttpMethod.POST, discountHttpEntity, ExchangeResponse.class);

		ExchangeResponse res = dResponseEntity.getBody();
		BigDecimal totalAmount = quantity.multiply( res.getConversionFactor());
		
		return new CurrencyConversionBean(res.getId(), res.getFrom(), res.getTo(), res.getConversionFactor(), quantity,
				totalAmount,res.getPort());

	}
	
	@GetMapping("/currency-converter-feign-fallback/v04/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod = "convertCurrencyFeignFallBack")
	public CurrencyConversionBean convertCurrencyFeign2(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		ExchangeRequest dRequest = new ExchangeRequest();
		dRequest.setCountryName(from);


		HttpEntity<ExchangeRequest> discountHttpEntity = new HttpEntity<ExchangeRequest>(dRequest);

		ResponseEntity<ExchangeResponse> dResponseEntity = lbrestTemplate.exchange("http://currency-exchange-service/convert",
				HttpMethod.POST, discountHttpEntity, ExchangeResponse.class);

		ExchangeResponse res = dResponseEntity.getBody();
		BigDecimal totalAmount = quantity.multiply( res.getConversionFactor());
		
		return new CurrencyConversionBean(res.getId(), res.getFrom(), res.getTo(), res.getConversionFactor(), quantity,
				totalAmount,res.getPort());

	}
	
	@GetMapping("/currency-converter-feign-fallback/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod = "convertCurrencyFeignFallBack")
	public CurrencyConversionBean convertCurrencyFeign1(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity)
	{
		CurrencyConversionBean response = proxy.retrieveExchangevalue(from, to);
		System.out.println("quantity::"+quantity);
		System.out.println("Feign response--> response.getConversionFactor()::"+response.getConversionFactor());
		return new CurrencyConversionBean(
				response.getId(),
				from, 
				to, 
				response.getConversionFactor(), 
				quantity, 
				quantity.multiply(response.getConversionFactor()), 
				response.getPort());
	}
	
	public CurrencyConversionBean convertCurrencyFeignFallBack(@PathVariable String from,
				@PathVariable String to,
				@PathVariable BigDecimal quantity)
		{
			
			CurrencyConversionBean response = proxy.retrieveExchangevalue(from, to);
			System.out.println("quantity::"+quantity);
			System.out.println("Feign response--> response.getConversionFactor()::"+response.getConversionFactor());
			return new CurrencyConversionBean(
					response.getId(),
					from, 
					to, 
					BigDecimal.valueOf(1000), // conversion factor
					quantity, 
					quantity.multiply(response.getConversionFactor()), 
					response.getPort());
			
		}

}	
