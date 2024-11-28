package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StockManager stockManager = new StockManager();
        Portfolio portfolio = new Portfolio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Menú Bolsa de Valores =====");
            System.out.println("1. Obtener cotización de acción");
            System.out.println("2. Agregar nueva cotización");
            System.out.println("3. Modificar cotización");
            System.out.println("4. Eliminar cotización");
            System.out.println("5. Comprar acción");
            System.out.println("6. Vender acción");
            System.out.println("7. Ver portafolio");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 8) {
                System.out.println("Gracias por usar la Bolsa de Valores. ¡Adiós!");
                break;
            }

            if (opcion == 1) {
                System.out.print("Ingrese el símbolo de la acción (ejemplo: AAPL): ");
                String symbol = scanner.next();
                Stock stock = stockManager.getStockQuote(symbol);
                if (stock != null) {
                    System.out.println("Cotización actual: " + stock);
                } else {
                    System.out.println("Error: No se encontró la acción con el símbolo " + symbol);
                }
            } else if (opcion == 2) {
                System.out.print("Ingrese el símbolo de la acción: ");
                String symbol = scanner.next();
                System.out.print("Ingrese el precio de la acción: ");
                double price = scanner.nextDouble();
                System.out.print("Ingrese la moneda: ");
                String currency = scanner.next();
                stockManager.addStockQuote(symbol, price, currency);
            } else if (opcion == 5) {
                System.out.print("Ingrese el símbolo de la acción que desea comprar: ");
                String symbol = scanner.next();
                System.out.print("Ingrese la cantidad de acciones que desea comprar: ");
                int quantity = scanner.nextInt();

                Stock stock = stockManager.getStockQuote(symbol);
                if (stock != null) {
                    portfolio.buyStock(symbol, quantity);
                } else {
                    System.out.println("Error: No se encontró la acción con el símbolo " + symbol);
                }
            } else if (opcion == 6) {
                portfolio.showPortfolio();
                if (!portfolio.getStocks().isEmpty()) {
                    System.out.print("Ingrese el símbolo de la acción que desea vender: ");
                    String symbol = scanner.next();
                    System.out.print("Ingrese la cantidad de acciones que desea vender: ");
                    int quantity = scanner.nextInt();

                    if (portfolio.getStockQuantity(symbol) > 0) {
                        portfolio.sellStock(symbol, quantity);
                    } else {
                        System.out.println("No tienes suficientes acciones de " + symbol + " para vender.");
                    }
                } else {
                    System.out.println("No tienes ninguna acción en tu portafolio para vender.");
                }
            } else if (opcion == 7) {
                portfolio.showPortfolio();
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}


