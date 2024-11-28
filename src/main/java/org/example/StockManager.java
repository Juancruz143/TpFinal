package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;  // Importando FileWriter
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class StockManager {
    private static final String FILE_PATH = "C:\\Users\\Usuario.PC-INF-PRUEBA\\IdeaProjects\\TpFinal\\src\\acciones"; // Ruta al archivo con las cotizaciones

    // Método para agregar una nueva cotización al archivo
    public void addStockQuote(String symbol, double price, String currency) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) { // Usa FileWriter para escribir en el archivo
            writer.write(symbol + "," + price + "," + currency + "\n");
            System.out.println("Cotización agregada correctamente.");
        } catch (IOException e) {
            System.err.println("Error al agregar la cotización: " + e.getMessage());
        }
    }

    // Método para actualizar una cotización existente
    public void updateStockQuote(String symbol, double price) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
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
                Files.write(Paths.get(FILE_PATH), lines);
                System.out.println("Cotización modificada correctamente.");
            } else {
                System.out.println("Símbolo no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al modificar la cotización: " + e.getMessage());
        }
    }

    // Método para eliminar una cotización del archivo
    public void deleteStockQuote(String symbol) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
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
                Files.write(Paths.get(FILE_PATH), lines);
                System.out.println("Cotización eliminada correctamente.");
            } else {
                System.out.println("Símbolo no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar la cotización: " + e.getMessage());
        }
    }

    // Método para obtener la cotización de una acción desde el archivo
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
                        // Usamos StockFactory para crear el objeto Stock
                        return StockFactory.createStock(fileSymbol, price, currency);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }
}
