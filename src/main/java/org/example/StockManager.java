package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class StockManager {
    private static final String API_URL = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=";

    public static JSONObject getStockData(String ticker) {
        OkHttpClient client = new OkHttpClient();
        String url = API_URL + ticker;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                return new JSONObject(jsonResponse);
            } else {
                System.err.println("Error al obtener datos de la API. Código HTTP: " + response.code());
            }
        } catch (Exception e) {
            System.err.println("Error en la llamada a la API: " + e.getMessage());
        }
        return null;
    }

    public Stock getStockQuote(String symbol) {
        return null;
    }
}


