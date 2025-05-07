package com.code;


public class MatricesDinamicas2 {

    public static void main(String[] args) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        matriz(diasPorMes);

    }


    public static void matriz(int[] diasPorMes) {

        String[] mesAnio = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Task2[][] calendary = new Task2[12][];

        for (int i = 0; i < mesAnio.length; i++) {
            calendary[i] = new Task2[diasPorMes[i]];
            for (int j = 0; j < diasPorMes[i]; j++) {
                calendary[i][j] = new Task2("Tarea " + (j + 1));
            }
        }

        for (int i = 0; i < diasPorMes.length; i++) {
            System.out.print(mesAnio[i] + "=[");
            for (int j = 0; j < diasPorMes[i]; j++) {
                System.out.print(" " + calendary[i][j].description);
            }
            System.out.print("]\n");
        }
    }
}
class Task2 {

    String description;
    Task2 next;

    Task2(String description) {
        this.description = description;
        this.next = null;
    }
}
