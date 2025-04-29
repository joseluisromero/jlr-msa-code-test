package com.code;

import org.springframework.boot.SpringApplication;

public class Recursividad {

  public static void main(String[] args) {
    SpringApplication.run(JlrMsaCodeTestApplication.class, args);
    System.out.println("10 x 5 =" + multiply(10, 5));

    System.out.println("3 x 2 =" + multiply(3, 2));

    System.out.println("-2 x 5 =" + multiply(-2, 5));

  }

  public static int multiply(int num1, int num2) {
    int multiply = 0;
    // Caso base
    if (num2 == 0) {
      return 0;
    }
    // Si b es negativo, cambia los signos
    if (num2 < 0) {
      return -multiply(num1, -num2);
    }
    // Llamada recursiva
    multiply = num1 + multiply(num1, num2 - 1);
    return multiply;
  }
}

