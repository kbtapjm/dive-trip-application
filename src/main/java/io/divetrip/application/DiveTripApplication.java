package io.divetrip.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "io.divetrip.application",
        "io.divetrip.library"
})
public class DiveTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveTripApplication.class, args);
    }
}
