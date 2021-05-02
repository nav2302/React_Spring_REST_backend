package com.todoapp.rest.webservices.RestfulWebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public String helloWorldPathVariable(@PathVariable String name) {
		return "Hello World " + name;
	}
}