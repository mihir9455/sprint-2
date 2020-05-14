package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OnlineTestManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTestManagementServerApplication.class, args);
	}

}
