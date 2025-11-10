package com.aishwarya.ethical.transperency_portal;

import org.springframework.boot.SpringApplication;

public class TestTransperencyPortalApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransperencyPortalApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
