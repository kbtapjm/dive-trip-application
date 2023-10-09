package io.divetrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.divetrip")
public class DiveTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveTripApplication.class, args);
    }
}
