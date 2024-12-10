package com.microservices.currency_conversion_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.currency_conversion_service.beans.CurrencyConversion;
import com.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

@RestController
public class CurrencyConversionController {
	@Autowired
	private Environment env;

	@Autowired
	CurrencyExchangeProxy cexProxy;
	
	 @Autowired
	    private RestTemplate restTemplate;

	@GetMapping("/currency-conversion/{from}/to/{to}/quantity")
	public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to,
			@RequestParam Integer quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity =restTemplate.getForEntity(
				"http://localhost:8080/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getCoversionMultiple(),
				quantity, 65.00 * quantity, currencyConversion.getEnvironment());
	}

	@GetMapping("/currency-conversion-feign/{from}/to/{to}/quantity")
	public CurrencyConversion convertCurrencyUsingFeign(@PathVariable String from, @PathVariable String to,
			@RequestParam Integer quantity) {

		CurrencyConversion currencyConversion = cexProxy.convertCurrency(from, to);
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getCoversionMultiple(),
				quantity, 65.00 * quantity, currencyConversion.getEnvironment());
	}

}
