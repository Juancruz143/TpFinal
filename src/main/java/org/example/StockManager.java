package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StockManager {
    private StockProvider stockProvider;


    public StockManager(StockProvider stockProvider) {
        this.stockProvider = stockProvider;
    }

    // Método para agregar cotización de una acción
    public void addStockQuote(String symbol, double price, String currency) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones", true))) {
            Stock stock = StockFactory.createStock(symbol, price, currency);
            writer.write(stock.getSymbol() + "," + stock.getPrice() + "," + stock.getCurrency() + "\n");
            System.out.println("Cotización agregada correctamente.");
        } catch (IOException e) {
            System.err.println("Error al agregar la cotización: " + e.getMessage());
        }
    }

    public Stock getStockQuote(String symbol) {
        return stockProvider.getStockQuote(symbol);
    }

    public void updateStockQuote(String symbol, double price) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones"));
            boolean updated = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(symbol)) {
                    lines.set(i, symbol + "," + price + "," + parts[2]);
                    updated = true;
                    break;
                }
            }

            if (updated) {
                Files.write(Paths.get("C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones"), lines);
                System.out.println("Cotización modificada correctamente.");
            } else {
                System.out.println("Símbolo no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al modificar la cotización: " + e.getMessage());
        }
    }

    public void deleteStockQuote(String symbol) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones"));
            boolean deleted = false;
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(symbol)) {
                    iterator.remove();
                    deleted = true;
                    break;
                }
            }

            if (deleted) {
                Files.write(Paths.get("C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones"), lines);
                System.out.println("Cotización eliminada correctamente.");
            } else {
                System.out.println("Símbolo no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar la cotización: " + e.getMessage());
        }
    }
}


