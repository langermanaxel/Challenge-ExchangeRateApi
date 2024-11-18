package com.aluracursos.ChallengeExchangeRateApi.Actions;

import com.aluracursos.ChallengeExchangeRateApi.Models.ExchangeRate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public void saveJson(ExchangeRate exchangeRate, String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writing = new FileWriter(fileName + ".json");
        writing.write(gson.toJson(exchangeRate));
        writing.close();
    }
}