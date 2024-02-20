package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {
            JsonArray values = readJsonArrayFromFile(valuesFilePath);
            JsonArray tests = readJsonArrayFromFile(testsFilePath);

            fillValues(tests, values);

            writeJsonArrayToFile(tests, reportFilePath);

            System.out.println("Report created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonArray readJsonArrayFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, JsonArray.class);
        }
    }

    private static void fillValues(JsonArray tests, JsonArray values) {
        for (JsonElement testElement : tests) {
            JsonObject test = testElement.getAsJsonObject();
            long testId = test.get("id").getAsLong();
            String testValue = findValue(testId, values);
            test.addProperty("value", testValue);

            if (test.has("values")) {
                fillValues(test.get("values").getAsJsonArray(), values);
            }
        }
    }

    private static String findValue(long testId, JsonArray values) {
        for (JsonElement valueElement : values) {
            JsonObject value = valueElement.getAsJsonObject();
            long valueId = value.get("id").getAsLong();
            if (valueId == testId) {
                return value.get("value").getAsString();
            }
        }
        return "";
    }

    private static void writeJsonArrayToFile(JsonArray jsonArray, String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(jsonArray, writer);
        }
    }
}
