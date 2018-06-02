package com.hrssc.hrsscweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HrsscWebApplication{

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HrsscWebApplication.class);
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(HrsscWebApplication.class, args);
	}
}
