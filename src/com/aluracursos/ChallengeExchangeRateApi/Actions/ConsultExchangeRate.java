package com.aluracursos.ChallengeExchangeRateApi.Actions;

import com.aluracursos.ChallengeExchangeRateApi.Models.ExchangeRate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultExchangeRate {

    public ExchangeRate searchRate(String baseCurrency) {
        URI url = URI.create("https://v6.exchangerate-api.com/v6/f97e5b3cb88c6e38d88a4f49/latest/" + baseCurrency);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear el JSON de la respuesta
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            // Verificar si la respuesta contiene las tasas de conversión
            if (jsonObject.has("conversion_rates")) {
                JsonObject conversionRatesJson = jsonObject.getAsJsonObject("conversion_rates");
                Map<String, Double> conversionRates = new HashMap<>();

                // Convertir el JSON de tasas a un Map<String, Double>
                for (String key : conversionRatesJson.keySet()) {
                    conversionRates.put(key, conversionRatesJson.get(key).getAsDouble());
                }

                // Crear y devolver el objeto ExchangeRate
                return new ExchangeRate(conversionRates);
            } else {
                throw new RuntimeException("Conversion rates not found in the response.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Exchange rate not found: " + e.getMessage());
        }
    }

    // Método para convertir entre dos monedas usando una tasa base
    public double convertCurrency(ExchangeRate exchangeRate, String fromCurrency, String toCurrency, double amount) {
        // Obtener las tasas de conversión para las monedas especificadas
        double fromRate = exchangeRate.conversionRates().get(fromCurrency);
        double toRate = exchangeRate.conversionRates().get(toCurrency);

        // Calcular la tasa de conversión relativa
        double conversionRate = toRate / fromRate;

        // Retornar el valor convertido
        return amount * conversionRate;
    }
}
