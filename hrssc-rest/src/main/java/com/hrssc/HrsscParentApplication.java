package com.hrssc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(value = "com.hrssc")
@EnableScheduling
@SpringBootApplication
public class HrsscParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrsscParentApplication.class, args);
	}
}
