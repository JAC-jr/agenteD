package com.example.agenteD;

import com.example.agenteD.MultiThread.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentDApplication {

	public static void main(String[] args) {SpringApplication.run(AgentDApplication.class, args);

		ApplicationThread applicationRun = new ApplicationThread();
		Thread applicationThread = new Thread(applicationRun);

		Load_balancerThread load_balancerRun = new Load_balancerThread();
		Thread load_balancerThread = new Thread(load_balancerRun);

		ApiThread ApiRun = new ApiThread();
		Thread apiThread = new Thread(ApiRun);

		ServiceThread serviceRun = new ServiceThread();
		Thread serviceThread = new Thread(serviceRun);

		PersistenceThread persistenceRun = new PersistenceThread();
		Thread persistenceThread = new Thread(persistenceRun);

		IntegrationThread integrationRun = new IntegrationThread();
		Thread integrationThread = new Thread(integrationRun);

		applicationThread.start();

		load_balancerThread.start();

		apiThread.start();

		serviceThread.start();

		persistenceThread.start();

		integrationThread.start();

	}
}

