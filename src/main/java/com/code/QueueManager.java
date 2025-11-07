/*package com.code;

import java.util.Scanner;

public class QueueNode {
    String name;
    QueueNode prev;

    QueueNode(String name) {
        this.name = name;
        this.prev = null;
    }
}

public class QueueManager {
    static QueueNode checkOutCounter = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        callMenuQueue();
    }

    static void callMenuQueue() {
        int option;

        do {
            System.out.println("\n\nCustomer Queue Manager:");
            System.out.println("1 - Show queue");
            System.out.println("2 - Add client to queue");
            System.out.println("3 - Serve (remove) client from queue");
            System.out.println("4 - Exit");
            System.out.print("Type the option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Type a number: ");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline
        } while (option < 1 || option > 4);

        switch (option) {
            case 1: showQueue(); break;
            case 2: addElementQueue(); break;
            case 3: removeElementQueue(); break;
            case 4: System.out.println("Exiting..."); break;
        }
    }

    static void showQueue() {
        System.out.println("\nCustomers on CheckOutCounter:");
        QueueNode aux = checkOutCounter;
        int i = 1;
        if (aux == null) {
            System.out.println("Queue is empty.");
        }
        while (aux != null) {
            System.out.println("Position " + i + ": " + aux.name);
            aux = aux.prev;
            i++;
        }
        callMenuQueue();
    }

    static void addElementQueue() {
        System.out.print("Type the name of the new client in the queue: ");
        String name = scanner.nextLine().trim();

        QueueNode client = new QueueNode(name);

        if (checkOutCounter == null) {
            checkOutCounter = client;
        } else {
            QueueNode aux = checkOutCounter;
            while (aux.prev != null) {
                aux = aux.prev;
            }
            aux.prev = client;
        }

        System.out.println("Client added to queue.");
        callMenuQueue();
    }

    static void removeElementQueue() {
        if (checkOutCounter != null) {
            System.out.println("Customer named: " + checkOutCounter.name + " was served.");
            checkOutCounter = checkOutCounter.prev;
        } else {
            System.out.println("Queue is empty. No customer to serve.");
        }
        callMenuQueue();
    }
}*/
