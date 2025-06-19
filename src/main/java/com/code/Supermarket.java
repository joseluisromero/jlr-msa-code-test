package com.code;

import java.util.Scanner;
import java.util.Random;

public class Supermarket {
    static Product productList = null;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    // Representa una unidad de producto con su código de barras
    static class ProductUnit {
        int barcode;
        ProductUnit next;

        ProductUnit(int barcode) {
            this.barcode = barcode;
            this.next = null;
        }
    }

    // Representa un producto en la lista
    static class Product {
        String name;
        ProductUnit top; // pila de unidades
        Product next; // siguiente producto en la lista

        Product(String name) {
            this.name = name;
            this.top = null;
            this.next = null;
        }

        void addUnits(int quantity, Random random) {
            for (int i = 0; i < quantity; i++) {
                int code = random.nextInt(1000);
                ProductUnit newUnit = new ProductUnit(code);
                newUnit.next = top;
                top = newUnit;
            }
        }

        void removeUnits(int quantity) {
            for (int i = 0; i < quantity; i++) {
                if (top == null) {
                    System.out.println("No hay más unidades del producto: " + name);
                    break;
                } else {
                    System.out.println("Eliminando unidad con código: " + top.barcode);
                    top = top.next;
                }
            }
        }

        void showUnits() {
            System.out.println("Producto: " + name);
            if (top == null) {
                System.out.println("No hay unidades disponibles.");
                return;
            }
            ProductUnit current = top;
            while (current != null) {
                System.out.println("Código de barras: " + current.barcode);
                current = current.next;
            }
        }
    }



    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu() {
        int option;
        do {
            System.out.println("\nGestión de productos del supermercado:");
            System.out.println("1 - Agregar nuevo tipo de producto");
            System.out.println("2 - Mostrar todos los productos y sus unidades");
            System.out.println("3 - Agregar unidades a un producto existente");
            System.out.println("4 - Eliminar unidades de un producto");
            System.out.println("5 - Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
        } while (option < 1 || option > 5);

        switch (option) {
            case 1 -> addProduct();
            case 2 -> showAllProducts();
            case 3 -> addUnitsToProduct();
            case 4 -> removeUnitsFromProduct();
            case 5 -> System.out.println("Saliendo del sistema de stock...");
        }

        if (option != 5) {
            mainMenu();
        }
    }

    static void addProduct() {
        System.out.print("Ingrese el nombre del nuevo producto: ");
        String name = scanner.nextLine();
        Product newProduct = new Product(name);
        newProduct.next = productList;
        productList = newProduct;
        System.out.println("Producto agregado: " + name);
    }

    static void showAllProducts() {
        if (productList == null) {
            System.out.println("No hay productos registrados.");
            return;
        }
        Product current = productList;
        while (current != null) {
            current.showUnits();
            System.out.println("-----");
            current = current.next;
        }
    }

    static Product findProduct(String name) {
        Product current = productList;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    static void addUnitsToProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        Product product = findProduct(name);
        if (product == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.print("¿Cuántas unidades desea agregar?: ");
        int qty = scanner.nextInt();
        product.addUnits(qty, random);
        System.out.println("Unidades agregadas correctamente.");
    }

    static void removeUnitsFromProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        Product product = findProduct(name);
        if (product == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.print("¿Cuántas unidades desea eliminar?: ");
        int qty = scanner.nextInt();
        product.removeUnits(qty);
    }
}