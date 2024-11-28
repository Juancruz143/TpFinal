package org.example;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks;  // Mapa que asocia un símbolo de acción con la cantidad que posees.

    public Portfolio() {
        this.stocks = new HashMap<>();
    }

    // Método para comprar acciones
    public void buyStock(String symbol, int quantity) {
        // Si ya tenemos acciones de este símbolo, sumamos la cantidad, si no, las añadimos
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
        System.out.println("Compraste " + quantity + " acciones de " + symbol + ".");
    }

    // Método para vender acciones
    public void sellStock(String symbol, int quantity) {
        if (stocks.containsKey(symbol)) {
            int currentQuantity = stocks.get(symbol);
            if (currentQuantity >= quantity) {
                // Restar la cantidad vendida
                stocks.put(symbol, currentQuantity - quantity);
                System.out.println("Vendiste " + quantity + " acciones de " + symbol + ".");
            } else {
                System.out.println("No tienes suficientes acciones de " + symbol + " para vender.");
            }
        } else {
            System.out.println("No tienes acciones de " + symbol + " en tu portafolio.");
        }
    }

    // Mostrar el portafolio
    public void showPortfolio() {
        if (stocks.isEmpty()) {
            System.out.println("Tu portafolio está vacío.");
        } else {
            System.out.println("===== Tu Portafolio =====");
            for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
                System.out.println("Símbolo: " + entry.getKey() + " | Cantidad: " + entry.getValue());
            }
        }
    }

    // Método para verificar cuántas acciones tienes de un símbolo
    public int getStockQuantity(String symbol) {
        return stocks.getOrDefault(symbol, 0);
    }

    // Método para obtener todas las acciones en el portafolio (para mostrar antes de vender)
    public Map<String, Integer> getStocks() {
        return stocks;  // Devuelve el mapa de acciones
    }
}
