package org.example;

public class StockFactory {

    // Método para crear una nueva instancia de Stock
    public static Stock createStock(String symbol, double price, String currency) {
        return new Stock(symbol, price, currency);
    }
}

