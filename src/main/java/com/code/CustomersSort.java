package com.code;

public class CustomersSort {

    public static void main(String[] args) {
        int[] array = {8, 3, 2, 7, 4, 6, 8};

        holeSort(array);

    }

    public static void holeSort(int[] array) {
        System.out.println("Antes de ordenar: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }

        // 1. Encontrar el valor mínimo y máximo del arreglo
        int min = array[0];
        int max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
            if (array[i] > max)
                max = array[i];
        }

        // 2. Crear los "casilleros" (holes)
        int range = max - min + 1;
        int[] holes = new int[range];

        // 3. Colocar cada número en su casillero correspondiente
        for (int i = 0; i < array.length; i++) {
            holes[array[i] - min]++;
        }

        // 4. Reconstruir el arreglo ordenado
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (holes[i]-- > 0) {
                array[index++] = i + min;
            }
        }
        System.out.println("\nDespués de ordenar: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }
}