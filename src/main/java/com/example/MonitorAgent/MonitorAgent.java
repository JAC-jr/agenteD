package com.example.MonitorAgent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
@SpringBootApplication
public class MonitorAgent {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MonitorAgent.class, args);
	}

}

