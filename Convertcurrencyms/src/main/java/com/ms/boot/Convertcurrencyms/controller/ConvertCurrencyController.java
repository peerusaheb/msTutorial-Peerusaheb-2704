package com.ms.boot.Convertcurrencyms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.boot.Convertcurrencyms.model.ConvertCurrency;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrencyDTO;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrencyRequest;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrencyRequest2;
import com.ms.boot.Convertcurrencyms.service.ConvertCurrencyService;

@RestController
@RequestMapping(path = "/currency")
public class ConvertCurrencyController {

	@Autowired
	ConvertCurrencyService convertcurrencyService;
	
	@RequestMapping(path = "/default", method = RequestMethod.GET)
	public String getGreeting() {
		return "Hello! This is ConvertCurrency MS-2 project";
	}
	

	@RequestMapping(path = "/convertCurrency", method = RequestMethod.POST)
	public ResponseEntity<ConvertCurrency> convertCurrency(@RequestBody ConvertCurrencyRequest obj) {

		ConvertCurrency c = convertcurrencyService.convertCurrency(obj);
		if (c == null) {
			ResponseEntity<ConvertCurrency> response = new ResponseEntity<ConvertCurrency>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ResponseEntity<ConvertCurrency> response = new ResponseEntity<ConvertCurrency>(c, HttpStatus.FOUND);
			return response;

		}
	}
	
	@RequestMapping(path = "/v0/{countryCode}", method = RequestMethod.GET)
	public ResponseEntity<ConvertCurrency> getCurrency(@PathVariable Integer countryCode) {

		ConvertCurrency p = convertcurrencyService.getConvertCurrency(countryCode);
		if (p == null) {
			ResponseEntity<ConvertCurrency> response = new ResponseEntity<ConvertCurrency>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ResponseEntity<ConvertCurrency> response = new ResponseEntity<ConvertCurrency>(p, HttpStatus.FOUND);
			return response;

		}
	}

	@RequestMapping(path = "/v1/{countryCode}", method = RequestMethod.GET)
	public ResponseEntity<ConvertCurrencyDTO> getCurrencyv1(@PathVariable Integer countryCode) {

		ConvertCurrency p = convertcurrencyService.getConvertCurrency(countryCode);
		if (p == null) {

			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ConvertCurrencyDTO pDTO = convertcurrencyService.applyConversion01(p);
			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(pDTO, HttpStatus.FOUND);
			return response;

		}
	}

	@RequestMapping(path = "/v2/{countryCode}", method = RequestMethod.GET)
	public ResponseEntity<ConvertCurrencyDTO> getCurrencyv2(@PathVariable Integer countryCode) {

		ConvertCurrency p = convertcurrencyService.getConvertCurrency(countryCode);
		if (p == null) {

			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ConvertCurrencyDTO pDTO = convertcurrencyService.applyConversion02(p);
			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(pDTO, HttpStatus.FOUND);
			return response;

		}
	}

	@RequestMapping(path = "/v3/{countryCode}", method = RequestMethod.GET)
	public ResponseEntity<ConvertCurrencyDTO> getCurrencyv3(@PathVariable Integer countryCode) {

		ConvertCurrency p = convertcurrencyService.getConvertCurrency(countryCode);
		if (p == null) {

			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ConvertCurrencyDTO pDTO = convertcurrencyService.applyConversion03(p);
			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(pDTO, HttpStatus.FOUND);
			return response;

		}
	}
	
	@RequestMapping(path = "/v4/{countryCode}", method = RequestMethod.GET)
	public ResponseEntity<ConvertCurrencyDTO> getCurrencyv4(@PathVariable Integer countryCode) {

		ConvertCurrency p = convertcurrencyService.getConvertCurrency(countryCode);
		if (p == null) {

			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ConvertCurrencyDTO pDTO = convertcurrencyService.applyConversion04(p);
			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(pDTO, HttpStatus.FOUND);
			return response;

		}
	}
	
	@RequestMapping(path = "/v5/{countryCode}", method = RequestMethod.GET)
	public ResponseEntity<ConvertCurrencyDTO> getCurrencyv5(@PathVariable Integer countryCode) {

		ConvertCurrency p = convertcurrencyService.getConvertCurrency(countryCode);
		if (p == null) {

			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ConvertCurrencyDTO pDTO = convertcurrencyService.applyConversion05(p);
			ResponseEntity<ConvertCurrencyDTO> response = new ResponseEntity<ConvertCurrencyDTO>(pDTO, HttpStatus.FOUND);
			return response;

		}
	}
	

}
