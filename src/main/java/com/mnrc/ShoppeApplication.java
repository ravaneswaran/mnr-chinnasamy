package com.mnrc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppeApplication.class, args);
		System.out.println("The application <<<<< The Store >>>>> is now up and running.");
	}

}
