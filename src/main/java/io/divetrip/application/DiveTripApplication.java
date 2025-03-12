package io.divetrip.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "io.divetrip" })
@EnableJpaRepositories(basePackages = { "io.divetrip.domain.repository" })
@EntityScan(basePackages = { "io.divetrip.domain.entity" })
public class DiveTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveTripApplication.class, args);
    }
}
