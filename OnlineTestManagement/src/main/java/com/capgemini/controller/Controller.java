package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dao.AnswerDao;
import com.capgemini.dao.QuestionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.entity.Test;
import com.capgemini.entity.UserTest;
import com.capgemini.service.CalculateMarks;
import com.capgemini.service.QuestionService;
import com.capgemini.service.TestService;
import com.capgemini.service.UserService;

@RestController
public class Controller {

	@Autowired
	QuestionService quesService;
	@Autowired
	TestDao tdao;
//	@Autowired
//	AnswerDao adao;
	@Autowired
	UserService userService;
	@Autowired
	TestService testService;
	@Autowired
	CalculateMarks calculate;

	@PostMapping("/addUser")
	public String addUser(@RequestBody UserTest usr)
	{	
			
			return userService.addUser(usr); 
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String removeUser(@PathVariable(value="id") int id) {
		
		return userService.deleteUser(id);
	}
	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable(value = "id") int id,@RequestBody UserTest user) {
		return userService.updateUser(id, user);
	}
	
	@GetMapping("/viewUsers")
	public List<UserTest> viewAllUser() {
		return userService.viewAll();
	}
	
	@PostMapping("/addTest")
	public Test addTest(@RequestBody Test test) {
		
		return testService.addTest(test);
	}
	
	@DeleteMapping("/deleteTest/{id}")
	public String deleteTest(@PathVariable(value="id") int testId) {
		return testService.deleteTest(testId);
	}
	
	@PutMapping("/updateTest/{id}")
	public String updateTest(@PathVariable(value = "id")int testId,@RequestBody Test test) {
		return testService.updateTest(testId, test);
		
	}
	
	
}

	

	