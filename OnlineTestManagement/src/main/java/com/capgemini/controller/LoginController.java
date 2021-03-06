package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.User;
import com.capgemini.service.LoginService;

@RestController
@CrossOrigin("*")
public class LoginController {

	@Autowired
	LoginService loginservice;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		String s = loginservice.login(user.getEmail(), user.getUserPassword());
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
}
