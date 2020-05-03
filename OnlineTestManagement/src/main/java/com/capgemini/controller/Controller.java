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

//import com.capgemini.dao.AnswerDao;
import com.capgemini.dao.QuestionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.entity.Test;
import com.capgemini.entity.UserTest;
import com.capgemin.service.CalculateMarks;
import com.capgemin.service.QuestionService;
import com.capgemin.service.TestService;
import com.capgemin.service.UserService;

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
	com.capgemin.service.TestService testService;
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
	
	@PostMapping("/addQuestion/{id}")
	public String addQuestion(@PathVariable(value = "id")int testId,@RequestBody Question question) {
		Optional<Question> q = quesService.findById(question.getQuestionId());
		if (!q.isPresent()) {
			quesService.addQuestion(testId,question);
			return "question added";
		} 
		return "question already exists";

	}
	
	@DeleteMapping("/deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable(value = "id")int qId) {
		return quesService.deleteQuestion(qId);
	} 
	
	@GetMapping("/view")
	public List<Question> viewAll() {
		return quesService.viewAll();
	}
	@PostMapping("/getResult")
	public int getResult(@RequestBody Test test) {
	   
		return calculate.getResults(test);
	}
	@GetMapping("/viewTest/{id}")
	public Test viewTestById(@PathVariable(value="id") int testId)
	{
		Optional<Test> tst=testService.findById(testId);
		return tst.get();
	}
	@PutMapping("/updateQuestion/{id}")
	public String updateQuestion(@PathVariable(value = "id")int qId,@RequestBody Question question) {
		return quesService.updateQuestion(qId, question);
		
	}	
	
	
}

	

	