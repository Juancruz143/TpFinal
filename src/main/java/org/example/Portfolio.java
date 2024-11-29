package org.example;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks;
    private StockManager stockManager;


    public Portfolio(StockManager stockManager) {
        this.stocks = new HashMap<>();
        this.stockManager = stockManager;
    }


    public void buyStock(String symbol, int quantity) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
        System.out.println("Compraste " + quantity + " acciones de " + symbol + ".");
    }


    public void sellStock(String symbol, int quantity) {
        if (stocks.containsKey(symbol)) {
            int currentQuantity = stocks.get(symbol);
            if (currentQuantity >= quantity) {
                stocks.put(symbol, currentQuantity - quantity);
                System.out.println("Vendiste " + quantity + " acciones de " + symbol + ".");
            } else {
                System.out.println("No tienes suficientes acciones de " + symbol + " para vender.");
            }
        } else {
            System.out.println("No tienes acciones de " + symbol + " en tu portafolio.");
        }
    }


    public void showPortfolio() {
        if (stocks.isEmpty()) {
            System.out.println("Tu portafolio está vacío.");
        } else {
            System.out.println("===== Tu Portafolio =====");
            for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
                String symbol = entry.getKey();
                int quantity = entry.getValue();
                Stock stock = stockManager.getStockQuote(symbol);
                if (stock != null) {
                    System.out.println("Símbolo: " + symbol + " | Cantidad: " + quantity + " | Precio: " + stock.getPrice() + " " + stock.getCurrency());
                } else {
                    System.out.println("Símbolo: " + symbol + " | Cantidad: " + quantity + " | Precio: N/D");
                }
            }
        }
    }

    public int getStockQuantity(String symbol) {
        return stocks.getOrDefault(symbol, 0);
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }
}




