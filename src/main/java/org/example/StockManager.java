package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StockManager {
    private static final String FILE_PATH = "C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones"; // Ruta al archivo con las cotizaciones

    // Método para obtener los datos de la acción desde un archivo
    public Stock getStockQuote(String symbol) {
        // Leer el archivo línea por línea
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Separar la línea por comas
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fileSymbol = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String currency = parts[2].trim();

                    // Si el símbolo coincide con el solicitado, devolver un objeto Stock
                    if (fileSymbol.equalsIgnoreCase(symbol)) {
                        return new Stock(fileSymbol, price, currency);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Si no se encuentra el símbolo, devolver null
        return null;
    }
}



