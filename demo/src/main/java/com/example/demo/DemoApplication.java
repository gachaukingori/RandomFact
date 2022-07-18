package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@RestController
@ControllerAdvice

public class DemoApplication extends SpringBootServletInitializer {
	private static final Logger logger =
			LoggerFactory.getLogger(DemoApplication.class);
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);

	}
	public static void main(String[] args)  {
		SpringApplication.run(DemoApplication.class, args);
	}
	@RequestMapping("/secondGet")
	public String secondGet(){
		
		return "hello from my tomcat server";
	}
	@Bean
	public RestTemplate getRestTemplate(){

		return new RestTemplate();
	}

}
