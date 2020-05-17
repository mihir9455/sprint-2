package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Question;
import com.capgemini.entity.Test;
import com.capgemini.entity.User;
import com.capgemini.exception.EntityAlreadyExists;
import com.capgemini.service.CalculateMarks;
import com.capgemini.service.QuestionService;
import com.capgemini.service.TestService;
import com.capgemini.service.UserService;

@RestController
@CrossOrigin("*")
/**
 * 
 * Rest controller class
 *
 */
public class Controller {

	@Autowired
	QuestionService quesService;

	@Autowired
	UserService userService;
	@Autowired
	TestService testService;
	@Autowired
	CalculateMarks calculate;

	Logger logger = LoggerFactory.getLogger(Controller.class);

	/**
	 * 
	 * @param usr
	 * @return
	 */
	@ExceptionHandler(EntityAlreadyExists.class)
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Validated @RequestBody User usr) {
		try {
			userService.addUser(usr);
			return new ResponseEntity<String>("User Added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<?> removeUser(@PathVariable(value = "email") String email) {
		try {
			userService.deleteUser(email);
			return new ResponseEntity<String>("User deleted", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * 
	 * @param email
	 * @param user
	 * @return
	 */
	@PutMapping("/updateUser/{email}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "email") String email,
			@Validated @RequestBody User user) {
		try {
			userService.updateUser(email, user);
			return new ResponseEntity<String>("User updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/viewUser/{email}")
	public User viewUserByEmail(@PathVariable(value = "email") String email) {
		return userService.findUserByEmail(email);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/viewUsers")
	public List<User> viewAllUser() {
		return userService.viewAll();
	}

	/**
	 * 
	 * @param testId
	 * @param question
	 * @return
	 */
	@PostMapping("/addQuestion/{id}")
	public ResponseEntity<?> addQuestion(@PathVariable(value = "id") int testId,
			@Validated @RequestBody Question question) {
		try {
			quesService.addQuestion(testId, question);
			return new ResponseEntity<String>("Question Added", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	/**
	 * 
	 * @param qId
	 * @param question
	 * @return
	 */
	@PutMapping("/updateQuestion/{id}")
	public ResponseEntity<?> updateQuestion(@PathVariable(value = "id") int qId,
			@Validated @RequestBody Question question) {
		try {
			quesService.updateQuestion(qId, question);
			return new ResponseEntity<String>("Question updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * 
	 * @param qId
	 * @return
	 */

	@DeleteMapping("/deleteQuestion/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable(value = "id") int qId) {
		try {
			quesService.deleteQuestion(qId);
			return new ResponseEntity<String>("Question deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * 
	 * @param qId
	 * @return
	 */
	@GetMapping("/getQuestion/{id}")
	public Question getQuestion(@PathVariable(value = "id") int qId) {
		Optional<Question> question = quesService.findById(qId);
		return question.get();
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/viewQuestions")
	public List<Question> viewAll() {
		return quesService.viewAll();
	}

	/**
	 * 
	 * @param test
	 * @return
	 */

	@PostMapping("/addTest")
	public ResponseEntity<?> addTest(@RequestBody Test test) {
		try {
			testService.addTest(test);
			return new ResponseEntity<String>("Test Added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	/**
	 * 
	 * @return
	 */

	@GetMapping("/viewTests")
	public List<Test> viewAllTest() {
		return testService.viewAll();
	}

	/**
	 * 
	 * @param testId
	 * @return
	 */

	@DeleteMapping("/deleteTest/{id}")
	public ResponseEntity<?> deleteTest(@PathVariable(value = "id") int testId) {
		try {
			testService.deleteTest(testId);
			return new ResponseEntity<String>("Test deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * 
	 * @param testId
	 * @param test
	 * @return
	 */
	@PutMapping("/updateTest/{id}")
	public ResponseEntity<?> updateTest(@PathVariable(value = "id") int testId, @RequestBody Test test) {
		try {
			testService.updateTest(testId, test);
			return new ResponseEntity<String>("Question updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * 
	 * @param testId
	 * @return
	 */

	@GetMapping("/viewTestById/{id}")
	Optional<Test> findById(@PathVariable(value = "id") int testId) {
		return testService.findById(testId);
	}

	/**
	 * 
	 * @param test
	 * @return
	 */
	@PostMapping("/getResult")
	public int getResult(@RequestBody Test test) {
		return calculate.getResults(test);
	}

	/**
	 * 
	 * @param email
	 * @param testId
	 * @return
	 */

	@PostMapping("/assignTest/{email}/{id}")
	public ResponseEntity<?> assignTest(@PathVariable(value = "email") String email,
			@PathVariable(value = "id") int testId) {
		try {
			userService.assignTest(email, testId);
			return new ResponseEntity<String>("Test Assigned", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
