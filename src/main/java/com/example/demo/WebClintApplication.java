package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebClintApplication {

	public static void main(String[] args) {
		System.out.println("start" + Thread.currentThread().getName());

		SpringApplication.run(WebClintApplication.class, args);
		System.out.println("end" + Thread.currentThread().getName());

	}

}
