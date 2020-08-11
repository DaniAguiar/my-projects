package com.invillia.poc.notegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class NoteGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteGeneratorApplication.class, args);
	}

}
