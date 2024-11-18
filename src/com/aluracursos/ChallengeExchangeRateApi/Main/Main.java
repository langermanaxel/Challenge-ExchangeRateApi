package com.aluracursos.ChallengeExchangeRateApi.Main;

import com.aluracursos.ChallengeExchangeRateApi.Actions.CurrencyConverter;
import com.aluracursos.ChallengeExchangeRateApi.Actions.FileGenerator;
import com.aluracursos.ChallengeExchangeRateApi.Actions.MenuHandler;
import com.aluracursos.ChallengeExchangeRateApi.Actions.UserInput;
import com.aluracursos.ChallengeExchangeRateApi.Models.ExchangeRate;

public class Main {
    public static void main(String[] args) {
        // Inicialización de componentes
        MenuHandler menuHandler = new MenuHandler();
        UserInput userInput = new UserInput();
        CurrencyConverter currencyConverter = new CurrencyConverter();
        FileGenerator fileGenerator = new FileGenerator();

        int rateNumber = 0;

        do {
            // Mostrar el menú y obtener la selección del usuario
            menuHandler.displayMenu();
            rateNumber = userInput.readMenuOption();
            // Obtener las monedas seleccionadas
            String[] currencies = menuHandler.getCurrencies(rateNumber);
            if (currencies == null) {
                System.out.println("Opción inválida.");
                return;
            }
            String fromCurrency = currencies[0];
            String toCurrency = currencies[1];

            // Leer el monto a convertir
            double amount = userInput.readAmount();
            if (amount < 0) {
                System.out.println("Monto no válido.");
                return;
            }
            // Realizar la conversión
            try {
                double convertedAmount = currencyConverter.convertCurrency(fromCurrency, toCurrency, amount);
                System.out.printf("Monto convertido: %.2f %s\n", convertedAmount, toCurrency);

                // Guardar las tasas de conversión en un archivo JSON
                ExchangeRate exchangeRate = currencyConverter.getExchangeRate(fromCurrency);
                String fileName = fromCurrency + "_to_" + toCurrency;
                fileGenerator.saveJson(exchangeRate, fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (rateNumber != 7);
    }
}
