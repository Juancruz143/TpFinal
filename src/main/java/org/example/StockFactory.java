package org.example;

public class StockFactory {

    public static Stock createStock(String symbol, double price, String currency) {
        return new Stock(symbol, price, currency);
    }
}

