package com.todoapp.rest.webservices.RestfulWebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String password = bCryptPasswordEncoder.encode("nav");
		System.out.println(password);

	}

}
