package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginservice;
	@RequestMapping("/login/{id}/{pass}")
	public String login(@PathVariable(value = "id") int id, @PathVariable(value = "pass") String password){
		return loginservice.login(id, password);
	}
}
