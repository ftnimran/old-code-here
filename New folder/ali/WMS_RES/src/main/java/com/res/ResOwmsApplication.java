package com.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ResOwmsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ResOwmsApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(ResOwmsApplication.class);
	    return application;
	}
}
