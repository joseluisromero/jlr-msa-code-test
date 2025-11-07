package com.code;

import java.util.Scanner;

public class SupermarketQueue {
    static QueueNode[] counters; // array dinámico de colas (una por caja)
    static int numberOfCounters;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Ingrese el número de cajas del supermercado: ");
        numberOfCounters = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        counters = new QueueNode[numberOfCounters]; // inicializa array de cajas
        callMenu();
    }

    static void callMenu() {
        int option;

        do {
            System.out.println("\nSistema de Colas del Supermercado:");
            System.out.println("1 - Mostrar una cola");
            System.out.println("2 - Mostrar todas las colas");
            System.out.println("3 - Agregar cliente a una cola");
            System.out.println("4 - Atender cliente de una cola");
            System.out.println("5 - Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
        } while (option < 1 || option > 5);

        switch (option) {
            case 1 -> showOneQueue();
            case 2 -> showAllQueues();
            case 3 -> addClientToQueue();
            case 4 -> AtentionClientFromQueue();
            case 5 -> System.out.println("Saliendo del sistema...");
        }

        if (option != 5) {
            callMenu();
        }
    }

    static void showOneQueue() {
        int index = askForCounter();
        showQueue(index);
    }

    static void showAllQueues() {
        for (int i = 0; i < numberOfCounters; i++) {
            System.out.println("\nCola en la caja " + (i + 1) + ":");
            showQueue(i);
        }
    }

    static void showQueue(int index) {
        QueueNode current = counters[index];
        int position = 1;

        if (current == null) {
            System.out.println("La cola está vacía.");
        } else {
            while (current != null) {
                System.out.println("Position " + position + ": " + current.name);
                current = current.prev;
                position++;
            }
        }
    }

    static void addClientToQueue() {
        int index = askForCounter();
        System.out.print("Ingrese el nombre del cliente: ");
        String name = scanner.nextLine();

        QueueNode newClient = new QueueNode(name);

        if (counters[index] == null) {
            counters[index] = newClient;
        } else {
            QueueNode aux = counters[index];
            while (aux.prev != null) {
                aux = aux.prev;
            }
            aux.prev = newClient;
        }

        System.out.println("Cliente agregado a la caja " + (index + 1));
    }

    static void AtentionClientFromQueue() {
        int index = askForCounter();

        if (counters[index] == null) {
            System.out.println("La cola está vacía en la caja  " + (index + 1));
        } else {
            System.out.println("Cliente atendido: " + counters[index].name);
            counters[index] = counters[index].prev;
        }
    }

    static int askForCounter() {
        int index;
        do {
            System.out.print("Ingrese el número de la caja (1 a " + numberOfCounters + "): ");
            index = scanner.nextInt() - 1;
            scanner.nextLine(); // limpiar buffer
        } while (index < 0 || index >= numberOfCounters);
        return index;
    }
}
class QueueNode {
    String name;
    QueueNode prev;

    QueueNode(String name) {
        this.name = name;
        this.prev = null;
    }
}
