package org.example;

public class Stock {
    private String symbol;
    private double price;
    private String currency;

    public Stock(String symbol, double price, String currency) {
        this.symbol = symbol;
        this.price = price;
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
