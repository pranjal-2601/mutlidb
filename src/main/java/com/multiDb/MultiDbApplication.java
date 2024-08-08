package com.multiDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.multiDb.config")
public class MultiDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDbApplication.class, args);
	}

}
