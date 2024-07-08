package com.stackroute.helpDeskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HelpDeskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskServiceApplication.class, args);
	}

}
