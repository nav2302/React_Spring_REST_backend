package com.todoapp.rest.webservices.basicAuth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BasicAuthenticationController {

	@GetMapping(path = "/basicauth")
	public String helloWorld() {
		return "You are authenticated";
	}
}