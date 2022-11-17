package com.example.agenteD;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.logging.Logger;

@EnableJpaRepositories("com.example.agenteD.Repository")
@EntityScan("com.example.agenteD.Entity")
@SpringBootApplication
public class AgentDApplication {


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(AgentDApplication.class, args);
	}

}
