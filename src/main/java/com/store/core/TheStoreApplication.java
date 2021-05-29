package com.store.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheStoreApplication.class, args);
		System.out.println("The application <<<<< The Store >>>>> is now up and running.");
	}

}
