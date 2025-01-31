package com.microservices.currency_exchange_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currency_exchange_service.beans.CurrencyExchange;
import com.microservices.currency_exchange_service.repository.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {
	Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment env;

	@Autowired
	CurrencyExchangeRepo currrepo;

	@GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyExchange retrirveCurrencyExchange(@PathVariable String fromCurrency,
			@PathVariable String toCurrency) {
		log.info("This is retrieve currency exchange method : {} to {}",fromCurrency,toCurrency);
		CurrencyExchange exchange = currrepo.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency).get();
		exchange.setEnvironment(env.getProperty("local.server.port"));
		return exchange;

	}

	@PostMapping("/total/currency-exchanges")
	public CurrencyExchange totalCurrencyExchanges(@RequestBody CurrencyExchange currencyExchange) {
		return currrepo.save(currencyExchange);
	}

}
