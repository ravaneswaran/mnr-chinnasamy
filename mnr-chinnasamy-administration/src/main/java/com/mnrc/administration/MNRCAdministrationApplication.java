package com.mnrc.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MNRCAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MNRCAdministrationApplication.class, args);
		System.out.println("The application <<<<< MNRC-Administration >>>>> is now up and running.");
	}

}
