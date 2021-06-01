package com.webshoppe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebShoppeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebShoppeApplication.class, args);
		System.out.println("The application <<<<< The Store >>>>> is now up and running.");
	}

}
