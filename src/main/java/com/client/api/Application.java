package com.client.api;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping("/")
	public String home() {
		return "Spring is here!";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
    @GetMapping("/bye")
	public String bye() {
		return "See Ya!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
