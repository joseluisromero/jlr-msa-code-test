package com.code;

import java.util.Scanner;

public class ArbolBinarioApp {

    static class Nodo {
        int numero;
        Nodo izquierda;
        Nodo derecha;

        Nodo(int numero) {
            this.numero = numero;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    static Nodo raiz = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menuArbol();
    }

    // Menú principal para gestionar el árbol
    static void menuArbol() {
        int opcion;

        do {
            System.out.println("\n\nGestor del Árbol:");
            System.out.println("1 - Mostrar árbol.");
            System.out.println("2 - Agregar nodo.");
            System.out.println("3 - Buscar nodo.");
            System.out.println("4 - Crear árbol por defecto.");
            System.out.println("5 - Eliminar nodo.");
            System.out.println("6 - Salir.");
            System.out.print("Ingresa una opción: ");
            opcion = sc.nextInt();

        } while (opcion < 1 || opcion > 6);

        switch (opcion) {
            case 1 -> mostrarArbol();
            case 2 -> agregarNodo();
            case 3 -> buscarNodo();
            case 4 -> crearArbolPorDefecto();
            case 5 -> eliminarNodo();
            case 6 -> System.out.println("Fin del programa.");
        }
    }

    // Insertar nodo ordenado
    static void insertarNodo(int numero) {
        Nodo nuevo = new Nodo(numero);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo actual = raiz;
            Nodo anterior = null;

            while (actual != null) {
                anterior = actual;
                if (numero < actual.numero)
                    actual = actual.izquierda;
                else
                    actual = actual.derecha;
            }

            if (numero < anterior.numero)
                anterior.izquierda = nuevo;
            else
                anterior.derecha = nuevo;
        }
    }

    static void agregarNodo() {
        System.out.print("Ingresa un número: ");
        int numero = sc.nextInt();
        insertarNodo(numero);
        menuArbol();
    }

    static void crearArbolPorDefecto() {
        insertarNodo(5);
        insertarNodo(4);
        insertarNodo(9);
        insertarNodo(-2);
        insertarNodo(8);
        insertarNodo(0);
        insertarNodo(3);
        insertarNodo(6);
        menuArbol();
    }

    // Métodos para mostrar el árbol
    static void mostrarInorden(Nodo nodo) {
        if (nodo != null) {
            mostrarInorden(nodo.izquierda);
            System.out.print(nodo.numero + " ");
            mostrarInorden(nodo.derecha);
        }
    }

    static void mostrarPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.numero + " ");
            mostrarPreorden(nodo.izquierda);
            mostrarPreorden(nodo.derecha);
        }
    }

    static void mostrarPostorden(Nodo nodo) {
        if (nodo != null) {
            mostrarPostorden(nodo.izquierda);
            mostrarPostorden(nodo.derecha);
            System.out.print(nodo.numero + " ");
        }
    }

    static void mostrarArbol() {
        int opcion;
        do {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1 - Inorden");
            System.out.println("2 - Preorden");
            System.out.println("3 - Postorden");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1 -> {
                System.out.println("\nÁrbol mostrado en INORDEN:");
                mostrarInorden(raiz);
            }
            case 2 -> {
                System.out.println("\nÁrbol mostrado en PREORDEN:");
                mostrarPreorden(raiz);
            }
            case 3 -> {
                System.out.println("\nÁrbol mostrado en POSTORDEN:");
                mostrarPostorden(raiz);
            }
        }
        System.out.println();
        menuArbol();
    }

    // Buscar un nodo
    static void buscarNodoEnArbol(int numero, boolean eliminar) {
        Nodo actual = raiz;
        Nodo anterior = null;
        int intentos = 0;

        while (actual != null) {
            intentos++;
            if (numero == actual.numero) break;
            anterior = actual;
            if (numero < actual.numero)
                actual = actual.izquierda;
            else
                actual = actual.derecha;
        }

        if (actual == null) {
            System.out.println("Nodo " + numero + " no encontrado.");
        } else {
            if (!eliminar)
                System.out.println("Nodo " + numero + " encontrado tras " + intentos + " intentos.");
            else
                eliminarNodoEnArbol(actual, anterior);
        }

        menuArbol();
    }

    static void buscarNodo() {
        System.out.print("¿Qué número deseas buscar?: ");
        int numero = sc.nextInt();
        buscarNodoEnArbol(numero, false);
    }

    static void eliminarNodo() {
        System.out.print("¿Qué número deseas eliminar?: ");
        int numero = sc.nextInt();
        buscarNodoEnArbol(numero, true);
    }

    // Eliminar un nodo del árbol (toda su rama)
    static void eliminarNodoEnArbol(Nodo nodo, Nodo padre) {
        if (nodo != null) {
            eliminarNodoEnArbol(nodo.izquierda, nodo);
            eliminarNodoEnArbol(nodo.derecha, nodo);
            System.out.println("Nodo eliminado: " + nodo.numero);

            if (padre != null) {
                if (nodo.numero < padre.numero)
                    padre.izquierda = null;
                else
                    padre.derecha = null;
            } else {
                raiz = null;
            }
        }
    }
}

