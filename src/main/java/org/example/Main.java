package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StockProvider stockProvider = new FileStockProvider();
        StockManager stockManager = new StockManager(stockProvider);
        Portfolio portfolio = new Portfolio(stockManager);

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

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el símbolo de la acción: ");
                    String symbol = scanner.next();
                    Stock stock = stockManager.getStockQuote(symbol);
                    if (stock != null) {
                        System.out.println("Cotización: " + stock);
                    } else {
                        System.out.println("No se encontró la cotización para el símbolo: " + symbol);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el símbolo de la acción: ");
                    symbol = scanner.next();
                    System.out.print("Ingrese el precio de la acción: ");
                    double price = scanner.nextDouble();
                    System.out.print("Ingrese la moneda: ");
                    String currency = scanner.next();
                    stockManager.addStockQuote(symbol, price, currency);
                    break;

                case 3:
                    System.out.print("Ingrese el símbolo de la acción: ");
                    symbol = scanner.next();
                    System.out.print("Ingrese el nuevo precio: ");
                    price = scanner.nextDouble();
                    stockManager.updateStockQuote(symbol, price);
                    break;

                case 4:
                    System.out.print("Ingrese el símbolo de la acción: ");
                    symbol = scanner.next();
                    stockManager.deleteStockQuote(symbol);
                    break;

                case 5:
                    System.out.print("Ingrese el símbolo de la acción: ");
                    symbol = scanner.next();
                    System.out.print("Ingrese la cantidad de acciones: ");
                    int quantity = scanner.nextInt();
                    portfolio.buyStock(symbol, quantity);
                    break;

                case 6:
                    System.out.print("Ingrese el símbolo de la acción: ");
                    symbol = scanner.next();
                    System.out.print("Ingrese la cantidad de acciones: ");
                    quantity = scanner.nextInt();
                    portfolio.sellStock(symbol, quantity);
                    break;

                case 7:
                    portfolio.showPortfolio();
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}








