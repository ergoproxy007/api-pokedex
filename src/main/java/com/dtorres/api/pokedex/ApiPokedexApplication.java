package com.dtorres.api.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiPokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPokedexApplication.class, args);
	}

}
