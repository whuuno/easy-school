package com.practice.easyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.practice.easyschool.repository")
@EntityScan("com.practice.easyschool.model")
public class EasySchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasySchoolApplication.class, args);
	}

}
