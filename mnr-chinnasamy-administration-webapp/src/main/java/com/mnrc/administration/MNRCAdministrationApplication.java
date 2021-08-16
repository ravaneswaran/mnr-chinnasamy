package com.mnrc.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mnrc.core", "com.mnrc.administration"})
public class MNRCAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MNRCAdministrationApplication.class, args);
		System.out.println(" __  __ _   _ ____        ____ _     _                                                      _       _           _       _     _             _   _              ");
		System.out.println("|  \\/  | \\ | |  _ \\      / ___| |__ (_)_ __  _ __   __ _ ___  __ _ _ __ ___   _   _        / \\   __| |_ __ ___ (_)_ __ (_)___| |_ _ __ __ _| |_(_) ___   _ __  ");
		System.out.println("| |\\/| |  \\| | |_) |____| |   | '_ \\| | '_ \\| '_ \\ / _` / __|/ _` | '_ ` _ \\ | | | |_____ / _ \\ / _` | '_ ` _ \\| | '_ \\| / __| __| '__/ _` | __| |/ _ \\ | '_ \\ ");
		System.out.println("| |  | | |\\  |  _ <_____| |___| | | | | | | | | | | (_| \\__ \\ (_| | | | | | || |_| |_____/ ___ \\ (_| | | | | | | | | | | \\__ \\ |_| | | (_| | |_| | (_) || | | |");
		System.out.println("|_|  |_|_| \\_|_| \\_\\     \\____|_| |_|_|_| |_|_| |_|\\__,_|___/\\__,_|_| |_| |_| \\__, |    /_/   \\_\\__,_|_| |_| |_|_|_| |_|_|___/\\__|_|  \\__,_|\\__|_|\\___/ |_| |_|");
		System.out.println("                                                                              |___/                                                                            ");
		System.out.println("+--------------------------------+");
		System.out.println("|    Version : 0.0.1-SNAPSHOT    |");
		System.out.println("+--------------------------------+");
	}

}
