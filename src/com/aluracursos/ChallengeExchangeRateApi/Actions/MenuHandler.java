package com.aluracursos.ChallengeExchangeRateApi.Actions;

public class MenuHandler {
    public void displayMenu() {
        System.out.println("******************************************");
        System.out.println("Sea bienvenido/a al conversor de moneda =]");
        System.out.println("1) Dolar =>> Peso Argentino\n" +
                "2) Peso Argentino =>> Dolar\n" +
                "3) Dolar =>> Real Brasileño\n" +
                "4) Real Brasileño =>> Dolar\n" +
                "5) Dolar =>> Peso Colombiano\n" +
                "6) Peso Colombiano =>> Dolar\n" +
                "7) Salir");
        System.out.println("Elija una opcion valida:");
    }

    public String[] getCurrencies(int rateNumber) {
        switch (rateNumber) {
            case 1:
                return new String[]{"USD", "ARS"};
            case 2:
                return new String[]{"ARS", "USD"};
            case 3:
                return new String[]{"USD", "BRL"};
            case 4:
                return new String[]{"BRL", "USD"};
            case 5:
                return new String[]{"USD", "COP"};
            case 6:
                return new String[]{"COP", "USD"};
            case 7:
                System.out.println("Saliendo...");
                return null;
            default:
                return null;
        }
    }
}
