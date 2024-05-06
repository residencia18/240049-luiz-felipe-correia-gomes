package br.com.lufecrx.crudexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableCaching
@SpringBootApplication
public class CrudExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudExerciseApplication.class, args);
		log.info("Application started successfully!");
	}

}
