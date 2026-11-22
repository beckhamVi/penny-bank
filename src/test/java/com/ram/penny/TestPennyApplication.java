package com.ram.penny;

import org.springframework.boot.SpringApplication;

public class TestPennyApplication {

	public static void main(String[] args) {
		SpringApplication.from(PennyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
