package com.svc.jogging.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.svc.jogging" })
@EnableCaching
@SpringBootApplication
public class JoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoggingApplication.class, args);
	}
}
