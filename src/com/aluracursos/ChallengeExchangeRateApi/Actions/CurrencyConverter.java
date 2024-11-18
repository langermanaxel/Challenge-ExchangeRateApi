package com.aluracursos.ChallengeExchangeRateApi.Actions;

import com.aluracursos.ChallengeExchangeRateApi.Models.ExchangeRate;

public class CurrencyConverter {
    private final ConsultExchangeRate consultation = new ConsultExchangeRate();

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        ExchangeRate exchangeRate = consultation.searchRate(fromCurrency);
        double fromRate = exchangeRate.conversionRates().get(fromCurrency);
        double toRate = exchangeRate.conversionRates().get(toCurrency);
        double conversionRate = toRate / fromRate;
        return amount * conversionRate;
    }

    public ExchangeRate getExchangeRate(String baseCurrency) {
        return consultation.searchRate(baseCurrency);
    }
}

