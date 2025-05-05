package com.code;


public class MatricesDinamicas {

    public static void main(String[] args) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        matriz(diasPorMes);

    }


    public static void matriz(int[] diasPorMes) {

        String[] mesAnio = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        int[][] calendary = new int[12][];

        for (int i = 0; i < mesAnio.length; i++) {
            calendary[i] = new int[diasPorMes[i]];
            for (int j = 0; j < diasPorMes[i]; j++) {
                calendary[i][j] = j + 1;
            }
        }

        for (int i = 0; i < diasPorMes.length; i++) {
            System.out.print(mesAnio[i] + "=[");
            for (int j = 0; j < diasPorMes[i]; j++) {
                System.out.print(" " + calendary[i][j]);
            }
            System.out.print("]\n");
        }
    }
}
