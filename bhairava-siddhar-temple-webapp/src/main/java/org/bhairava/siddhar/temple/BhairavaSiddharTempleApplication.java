package org.bhairava.siddhar.temple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mnrc.core", "org.bhairava.siddhar.temple"})
public class BhairavaSiddharTempleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BhairavaSiddharTempleApplication.class, args);
    }

}
