package com.subg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
public class SpringBootApplications extends SpringBootServletInitializer{

	public SpringBootApplications() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplications.class, args );
		
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(SpringBootApplications.class);
	}

}
