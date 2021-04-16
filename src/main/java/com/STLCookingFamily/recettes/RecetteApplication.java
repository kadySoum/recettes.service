package com.STLCookingFamily.recettes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(= configuration , Enable Auto Configuration , ComponentScan)
public class RecetteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecetteApplication.class, args);
	}

}
