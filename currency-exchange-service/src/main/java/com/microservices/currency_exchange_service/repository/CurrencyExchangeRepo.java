package com.microservices.currency_exchange_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.currency_exchange_service.beans.CurrencyExchange;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange,Integer> {
    Optional<CurrencyExchange> findByFromCurrencyAndToCurrency(String fromCurrency,String toCurrency);
}
