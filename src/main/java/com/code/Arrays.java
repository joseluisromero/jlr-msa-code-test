package com.code;

import java.util.stream.Collectors;

public class Arrays {

  public static void main(String[] args) {

    double array[] = {2.5, 2.0, 3.0, 12.1, -2.0, 6.0, -1.0, 8.0, 9.0, 11.9};
    int array1[] = {5, 1, 4, 6, 2, 7, 3};
    //processArrays(array);
    shortBubble(array1);
  }

  public static void processArrays(double[] array) {

    double avg = 0.0;
    double numericBig = 0.0;
    double numericSmall = 0.0;
    for (int i = 0; i < array.length; i++) {
      avg += array[i];
      if (array[i] > numericBig) {
        numericBig = array[i];
      }
      if (array[i] < numericSmall) {
        numericSmall = array[i];
      }
    }

    avg = avg / array.length;

    System.out.println("numero mayor en el array: " + numericBig);
    System.out.println("numero menor en el array: " + numericSmall);
    System.out.println("promedio de numero: " + avg);

  }

  /**
   * Teste metodo para ordenar un array de forma ascendente
   *
   * @param array
   */
  public static void shortBubble(int[] array) {

    for (int i = 0; i < array.length; i++) {

      for (int j = 0; j < array.length - i; j++) {
        System.out.print("V[" + j + "]=" + array[j] + ",");
        if (j == array.length - 1) {
          break;
        }
        if (array[j] > array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
        //System.out.print("V[" + j + "]=" + array[j] + ",");
      }
      System.out.println();
    }
    System.out.println();

    System.out.println("Vector Ordenado");
    for (int i = 0; i < array.length; i++) {
      System.out.print("V[" + i + "]=" + array[i] + ",");
    }

  }

}
