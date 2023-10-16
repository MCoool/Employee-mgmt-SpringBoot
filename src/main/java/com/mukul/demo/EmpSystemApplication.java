package com.mukul.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
@EntityScan({"com.mukul.demo.entity"})
public class EmpSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpSystemApplication.class, args);
	
	}

}
