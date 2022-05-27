package com.imagnative.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableMongoAuditing
@EnableWebMvc
public class ProjectApplicationTests extends SpringBootServletInitializer {

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// builder) {
	// return builder.sources(SpringBootApp.class);
	// }

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplicationTests.class, args);
	}
}
