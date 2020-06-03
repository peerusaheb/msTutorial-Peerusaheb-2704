package com.ms.boot.Convertcurrencyms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.ms.boot.Convertcurrencyms.model.ConvertCurrency;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrencyDTO;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrencyRequest;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrencyRequest2;
import com.ms.boot.Convertcurrencyms.model.ManageCurrencyRequest2;
import com.ms.boot.Convertcurrencyms.model.ManageCurrencyResponse;
import com.ms.boot.Convertcurrencyms.repo.ConverCurrencyRepository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@Component
@RibbonClient(name = "discountms")
public class ConvertCurrencyService {
	@Autowired
	private ConverCurrencyRepository repo;

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
	
	@Autowired
	Managecurrencyserviceproxy managecurrencyProxy;

	
	
	public List<ConvertCurrency> getAllConversionFactors() {
		return repo.findAll();
	}
	
	public ConvertCurrency getConvertCurrency(Integer id) {
		Optional<ConvertCurrency> oConvertCurrency = repo.findById(id);
		if (oConvertCurrency.isPresent())
			return oConvertCurrency.get();
		else
			return null;

	}

	public ConvertCurrencyDTO applyConversion01(ConvertCurrency p) {
		ManageCurrencyRequest2 dRequest = createManageCurrencyRequest(p);

		List<ServiceInstance> instances = discoveryClient.getInstances("managems");
		System.out.println("Instances of managems found =" + instances.size());
		for (ServiceInstance instance : instances) {
			System.out.println(instance.getHost() + ":" + instance.getPort());
		}

		ServiceInstance instance = instances.get(0);
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/convertCurrency";
		System.out.println("Calling URL :" + url);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<ManageCurrencyRequest2> discountHttpEntity = new HttpEntity<ManageCurrencyRequest2>(dRequest);
		ResponseEntity<ManageCurrencyResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST,
				discountHttpEntity, ManageCurrencyResponse.class);

		ManageCurrencyResponse dResponse = dResponseEntity.getBody();
		return ceateConvertCurrency(dResponse, p);

	}
	
	public ConvertCurrencyDTO applyConversion02(ConvertCurrency p) {
		ManageCurrencyRequest2 dRequest = createManageCurrencyRequest(p);
		ServiceInstance instance = lbClient.choose("managems");
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/convertCurrency";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<ManageCurrencyRequest2> discountHttpEntity = new HttpEntity<ManageCurrencyRequest2>(dRequest);
		ResponseEntity<ManageCurrencyResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST,
				discountHttpEntity, ManageCurrencyResponse.class);

		ManageCurrencyResponse dResponse = dResponseEntity.getBody();
		return ceateConvertCurrency(dResponse, p);

	}
	
	public ConvertCurrencyDTO applyConversion03(ConvertCurrency p) {
		ManageCurrencyRequest2 dRequest = createManageCurrencyRequest(p);

		HttpEntity<ManageCurrencyRequest2> manageHttpEntity = new HttpEntity<ManageCurrencyRequest2>(dRequest);

		ResponseEntity<ManageCurrencyResponse> dResponseEntity = lbrestTemplate.exchange("http://managems/convertCurrency",
				HttpMethod.POST, manageHttpEntity, ManageCurrencyResponse.class);

		ManageCurrencyResponse dResponse = dResponseEntity.getBody();
		return ceateConvertCurrency(dResponse, p);

	}
	
	@HystrixCommand(fallbackMethod = "convertCurrencyfallback")
	public ConvertCurrencyDTO applyConversion04(ConvertCurrency p) {
		ManageCurrencyRequest2 dRequest = createManageCurrencyRequest(p);

		HttpEntity<ManageCurrencyRequest2> manageHttpEntity = new HttpEntity<ManageCurrencyRequest2>(dRequest);

		ResponseEntity<ManageCurrencyResponse> dResponseEntity = lbrestTemplate.exchange("http://managems/convertCurrency",
				HttpMethod.POST, manageHttpEntity, ManageCurrencyResponse.class);

		ManageCurrencyResponse dResponse = dResponseEntity.getBody();
		return ceateConvertCurrency(dResponse, p);

	}
	
	public ConvertCurrencyDTO applyConversion05(ConvertCurrency p) {
		ManageCurrencyRequest2 dRequest = createManageCurrencyRequest(p);
		ManageCurrencyResponse response = managecurrencyProxy.getConversionFactor(dRequest);
		
		return ceateConvertCurrency(response, p);
		
		
	}
	
    
	public ConvertCurrency convertCurrency(@RequestBody ConvertCurrencyRequest obj) {
		
		ManageCurrencyRequest2 mObj = new ManageCurrencyRequest2(obj.getCountryCode());
		ManageCurrencyResponse res = managecurrencyProxy.getConversionFactor(mObj);
		ConvertCurrency oCurrency = null;
		
		if(res != null)
		{
			System.out.println("ManageCurrencyResponse from MS-1 :"+res.toString());
			double resultAmount = res.getFactor() * obj.getAmount();
			oCurrency = new ConvertCurrency(res.getCountryCode(),obj.getAmount(),resultAmount);
			repo.save(oCurrency);
			System.out.println("Result Amount Successfully saved in DB");
			return oCurrency;
		}
		else
		{
			System.out.println("ManageCurrencyResponse from MS-1  is null");
		}
		return oCurrency;
		
	}
	
	public ConvertCurrencyDTO ceateConvertCurrency(ManageCurrencyResponse res,ConvertCurrency c)
	{
		ConvertCurrencyDTO ret = new ConvertCurrencyDTO();
		ret.setCountryCode(res.getCountryCode());
		ret.setFactor(res.getFactor());
		ret.setAmount(c.getAmount());
		ret.setResultAmount(res.getFactor()*c.getAmount());
		return ret;
	}

	//@RequestMapping(path = "/getConversionFactor", method = RequestMethod.GET)
	//public ConvertCurrency  getConversionFactor(@RequestBody ConvertCurrencyRequest2 obj) {
	//	ConvertCurrency oCurrency = repo.findByCountryCode(obj.getCountryCode());	
		
	//	if(oCurrency != null)
	//	{
	//		System.out.println("Currency from Database:"+oCurrency.toString());
	//		return oCurrency;
	//	}
	//	return null;
	//}
	
	public ManageCurrencyRequest2 createManageCurrencyRequest(ConvertCurrency p)
	{
		ManageCurrencyRequest2 obj = new ManageCurrencyRequest2();
		obj.setCountryCode(p.getCountryCode());
		return obj;
	}
	
	public ConvertCurrencyDTO convertCurrencyfallback(ConvertCurrency p) {
		
		ConvertCurrencyDTO pdto = new ConvertCurrencyDTO();
		pdto.setCountryCode(p.getCountryCode());
		pdto.setFactor(0.0); //TODO
		pdto.setAmount(p.getAmount());
		pdto.setResultAmount(p.getResultAmount());
		return pdto;
	}
}
