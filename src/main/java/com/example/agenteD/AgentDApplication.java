package com.example.agenteD;

import com.example.agenteD.Entity.Api;
import com.example.agenteD.MultiThread.*;
import com.example.agenteD.Repository.ApiRepository;
import com.example.agenteD.Util.TestJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AgentDApplication {

	public static void main(String[] args) throws InterruptedException {

		Logger logger = LoggerFactory.getLogger(AgentDApplication.class);

		SpringApplication.run(AgentDApplication.class, args);
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

		/*logger.info("initialization of threads");
		applicationThread.start();
		load_balancerThread.start();
		apiThread.start();
		serviceThread.start();
		persistenceThread.start();
		integrationThread.start();*/

		TestJpa testRun = new TestJpa();
		Thread testJpaThread = new Thread(testRun);

		testJpaThread.start();
	}
}

