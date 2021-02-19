package com.SAlvesJr.patrimonio.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.SAlvesJr.patrimonio.services.Impl.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	private DBService dbService;

	public TestConfig(DBService dbService) {
		this.dbService = dbService;
	}

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
}
