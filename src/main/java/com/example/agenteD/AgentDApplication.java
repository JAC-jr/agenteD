package com.example.agenteD;

import com.example.agenteD.DaoImp.ApiDaoImp;
import com.example.agenteD.DaoImp.ApplicationDaoImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentDApplication {

	/*try{
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test-database","postgres","cisneros");
	} catch (Exception e) {
		throw new RuntimeException(e);
	}*/
	public static void main(String[] args) {
		SpringApplication.run(AgentDApplication.class, args);

		ApplicationDaoImp applicationRun = new ApplicationDaoImp();
		Thread ThreadApplication = new Thread(applicationRun);

		ApiDaoImp ApiRun = new ApiDaoImp();
		Thread ThreadApi = new Thread(ApiRun);

		ThreadApplication.start();
		ThreadApi.start();
	}

}
