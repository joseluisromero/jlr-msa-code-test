package com.code;

import static com.code.Arrays.processArrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JlrMsaCodeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JlrMsaCodeTestApplication.class, args);
		//verifyNumericIsGuay(10);

	}

	public static void verifyNumericIsGuay(int n) {
		int sum = 0;
		for (int i = 1; i < n / 2; i++) {
			sum += i;
		}
		if (sum == n) {
			System.out.println("El número " + n + " es guay");
		} else {
			System.out.println("El número " + n + " no es guay");
		}
	}


}
