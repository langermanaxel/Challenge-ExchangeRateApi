package com.aluracursos.ChallengeExchangeRateApi.Models;

import java.util.Map;

public record ExchangeRate(Map<String, Double> conversionRates) {
}
