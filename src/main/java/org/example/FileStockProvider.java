package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileStockProvider implements StockProvider {
    private static final String FILE_PATH = "C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones";  // Ajusta esta ruta a la ubicación de tu archivo

    @Override
    public Stock getStockQuote(String symbol) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fileSymbol = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String currency = parts[2].trim();
                    if (fileSymbol.equalsIgnoreCase(symbol)) {
                        return new Stock(fileSymbol, price, currency);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de cotizaciones: " + e.getMessage());
        }
        return null; // Retorna null si no encuentra el símbolo
    }
}

