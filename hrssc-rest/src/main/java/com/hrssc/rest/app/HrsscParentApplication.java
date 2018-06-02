package com.hrssc.rest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.hrssc")
@SpringBootApplication
public class HrsscParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrsscParentApplication.class, args);
	}
}
