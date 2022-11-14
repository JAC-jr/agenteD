package com.example.agenteD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentDApplication {

	public static void main(String[] args) throws InterruptedException {

		//Logger logger = LoggerFactory.getLogger(AgentDApplication.class);

		SpringApplication.run(AgentDApplication.class, args);
	}

}

