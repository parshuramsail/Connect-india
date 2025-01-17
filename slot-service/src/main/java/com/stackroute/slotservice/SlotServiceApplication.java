package com.stackroute.slotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SlotServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(SlotServiceApplication.class, args);
    }


}
