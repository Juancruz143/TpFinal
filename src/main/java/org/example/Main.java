package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StockManager stockManager = new StockManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Menú Bolsa de Valores =====");
            System.out.println("1. Obtener cotización de acción");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 2) {
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
                    System.out.println("Error al obtener los datos de la acción.");
                }
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}


