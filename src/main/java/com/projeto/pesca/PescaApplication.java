package com.projeto.pesca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.projeto.pesca", "com.projeto.pesca.config"})
public class PescaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PescaApplication.class, args);
	}

}
