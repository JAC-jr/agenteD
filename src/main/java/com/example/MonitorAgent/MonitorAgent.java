package com.example.MonitorAgent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MonitorAgent {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MonitorAgent.class, args);
	}

}

