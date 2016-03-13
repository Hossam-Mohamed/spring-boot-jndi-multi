package de.laboranowitsch.jndimulti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main Spring Boot class.
 *
 * Created by cla on 3/12/16.
 */
@Configuration
@ComponentScan // EnableAutoConfiguration moved out
public class MultiJndiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiJndiApplication.class, args);
	}
}
