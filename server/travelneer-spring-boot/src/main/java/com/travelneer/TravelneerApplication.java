package com.travelneer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TravelneerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelneerApplication.class, args);
	}
}
