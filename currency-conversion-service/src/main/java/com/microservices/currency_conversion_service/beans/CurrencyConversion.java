package com.microservices.currency_conversion_service.beans;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CurrencyConversion {
	private Integer id;
	private String fromCurrency;
	private String toCurrency;
	private Double coversionMultiple;
	private Integer quantity;
	private Double totalCalculatedAmount;
	private String environment;
	
}