package com.code;

import java.util.Scanner;

public class ArbolBinario {
    static class Nodo {
        int numero;
        Nodo izquierda;
        Nodo derecha;

        Nodo(int numero) {
            this.numero = numero;
        }
    }

    static Nodo raiz = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menuArbol();
    }

    // Menú principal del árbol
    static void menuArbol() {
        int opcion;
        do {
            System.out.println("\n\nGestión del árbol:");
            System.out.println("1 - Mostrar árbol.");
            System.out.println("2 - Añadir nodo.");
            System.out.println("3 - Buscar nodo.");
            System.out.println("4 - Crear árbol por defecto.");
            System.out.println("5 - Eliminar nodo.");
            System.out.println("6 - Salir.");
            System.out.println("7 - Mostrar cantidad de nodos.");
            System.out.print("Introduce una opción: ");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 7);

        switch (opcion) {
            case 1 -> mostrarArbol();
            case 2 -> agregarNodo();
            case 3 -> buscarNodo();
            case 4 -> crearArbolPorDefecto();
            case 5 -> eliminarNodo();
            case 6 -> System.exit(0);
            case 7 -> mostrarCantidadNodos();
        }
    }

    // Agregar nodo al árbol
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
        System.out.print("Introduce un número: ");
        int numero = scanner.nextInt();
        insertarNodo(numero);
        menuArbol();
    }

    // Crear árbol de ejemplo
    static void crearArbolPorDefecto() {
        insertarNodo(5);
        insertarNodo(4);
        insertarNodo(9);
        insertarNodo(-2);
        insertarNodo(8);
        insertarNodo(0);
        insertarNodo(3);
        insertarNodo(6);
        System.out.println("Árbol por defecto creado.");
        menuArbol();
    }

    // Mostrar árbol en diferentes órdenes
    static void mostrarArbol() {
        System.out.println("\nSelecciona el tipo de recorrido:");
        System.out.println("1 - Inorden");
        System.out.println("2 - Preorden");
        System.out.println("3 - Postorden");
        System.out.print("Introduce una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1 -> {
                System.out.println("\nÁRBOL EN ORDEN INORDEN:");
                mostrarInorden(raiz);
            }
            case 2 -> {
                System.out.println("\nÁRBOL EN ORDEN PREORDEN:");
                mostrarPreorden(raiz);
            }
            case 3 -> {
                System.out.println("\nÁRBOL EN ORDEN POSTORDEN:");
                mostrarPostorden(raiz);
            }
        }
        System.out.println();
        menuArbol();
    }

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

    // Buscar nodo
    static void buscarNodo() {
        System.out.print("¿Qué número deseas buscar?: ");
        int numero = scanner.nextInt();
        buscarNodoRecursivo(raiz, numero, 1, false);
        menuArbol();
    }

    static void buscarNodoRecursivo(Nodo actual, int numero, int vueltas, boolean eliminar) {
        if (actual == null) {
            System.out.println("Nodo " + numero + " no encontrado.");
            return;
        }

        if (actual.numero == numero) {
            if (!eliminar) {
                System.out.println("Nodo " + numero + " encontrado en " + vueltas + " vueltas.");
            } else {
                eliminarNodoRecursivo(raiz, null, numero);
            }
        } else if (numero < actual.numero) {
            buscarNodoRecursivo(actual.izquierda, numero, vueltas + 1, eliminar);
        } else {
            buscarNodoRecursivo(actual.derecha, numero, vueltas + 1, eliminar);
        }
    }

    // Eliminar nodo
    static void eliminarNodo() {
        System.out.print("¿Qué número deseas eliminar?: ");
        int numero = scanner.nextInt();
        buscarNodoRecursivo(raiz, numero, 1, true);
        menuArbol();
    }

    static Nodo eliminarNodoRecursivo(Nodo actual, Nodo padre, int valor) {
        if (actual == null) return null;

        if (valor < actual.numero) {
            actual.izquierda = eliminarNodoRecursivo(actual.izquierda, actual, valor);
        } else if (valor > actual.numero) {
            actual.derecha = eliminarNodoRecursivo(actual.derecha, actual, valor);
        } else {
            System.out.println("Nodo eliminado: " + actual.numero);
            if (actual.izquierda == null && actual.derecha == null) {
                return null;
            } else if (actual.izquierda == null) {
                return actual.derecha;
            } else if (actual.derecha == null) {
                return actual.izquierda;
            } else {
                Nodo sucesor = encontrarMinimo(actual.derecha);
                actual.numero = sucesor.numero;
                actual.derecha = eliminarNodoRecursivo(actual.derecha, actual, sucesor.numero);
            }
        }
        return actual;
    }

    static Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierda != null)
            nodo = nodo.izquierda;
        return nodo;
    }

    // Mostrar cantidad total de nodos
    static void mostrarCantidadNodos() {
        int total = contarNodos(raiz);
        System.out.println("Cantidad total de nodos: " + total);
        menuArbol();
    }

    static int contarNodos(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izquierda) + contarNodos(nodo.derecha);
    }


    static int calcularAltura(Nodo nodo) {
        if (nodo == null) return 0;
        int alturaIzq = calcularAltura(nodo.izquierda);
        int alturaDer = calcularAltura(nodo.derecha);
        return 1 + Math.max(alturaIzq, alturaDer);
    }
}

