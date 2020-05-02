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

	

	@PostMapping("/addQuestion/{id}")
	public String addQuestion(@PathVariable(value = "id")int testId,@RequestBody Question question) {
		Optional<Question> q = quesService.findById(question.getQuestionId());
		if (!q.isPresent()) {
			quesService.addQuestion(testId,question);
			return "question added";
		} 
		return "question already exists";

	}
	
	@PutMapping("/updateQuestion/{id}")
	public String updateQuestion(@PathVariable(value = "id")int qId,@RequestBody Question question) {
		return quesService.updateQuestion(qId, question);
		
	}
	
	@DeleteMapping("/deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable(value = "id")int qId) {
		return quesService.deleteQuestion(qId);
	} 
	
	@GetMapping("/viewQuestions")
	public List<Question> viewAll() {
		return quesService.viewAll();
	}

	

	@PostMapping("/addTest")
	public Test addTest(@RequestBody Test test) {
		
		return testService.addTest(test);
	}

	@GetMapping("/viewTests")
	public List<Test> viewAllTest() {
		return testService.viewAll();
	}
	
	@DeleteMapping("/deleteTest/{id}")
	public String deleteTest(@PathVariable(value="id") int testId) {
		return testService.deleteTest(testId);
	}
	
	@PutMapping("/updateTest/{id}")
	public String updateTest(@PathVariable(value = "id")int testId,@RequestBody Test test) {
		return testService.updateTest(testId, test);
		
	}
	@PostMapping("/getResult")
	public int getResult(@RequestBody Test test) {
		System.out.println(test.getTestId()+"/////////////////////////////////////////////////");
		return calculate.getResults(test);
	}
	@PostMapping("/assignTest/{id}/{id1}")
	public String assignTest(@PathVariable(value= "id") int userId,@PathVariable(value= "id1") int testId )
	{
		 return userService.assignTest(userId, testId);
	}

}
