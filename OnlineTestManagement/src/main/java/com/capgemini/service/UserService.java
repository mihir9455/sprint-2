package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.TestDao;
import com.capgemini.dao.UserDao;
import com.capgemini.entity.Test;
import com.capgemini.entity.User;
import com.capgemini.exception.EntityAlreadyExists;
import com.capgemini.exception.EntityNotFoundException;

@Service
/**
 * 
 * @author mihir
 *
 */
public class UserService implements UserServiceI {

	@Autowired
	UserDao dao;
	@Autowired
	TestDao tdao;

	/**
	 * For adding User
	 */
	@Override
	public User addUser(User user) {
		Long id = dao.getIdByEmail(user.getEmail());
		if (id != null)
			throw new EntityAlreadyExists("Email already present");
		dao.save(user);
		return user;

	}

	/**
	 * for deleting an user
	 */
	@Override
	public String deleteUser(String email) {
		long userId = dao.getIdByEmail(email);
		Optional<User> findById = dao.findById(userId);
		if (findById.isPresent()) {
			dao.deleteById(userId);
			return "User Removed";
		} else {
			throw new EntityNotFoundException("User does not exist");
		}

	}

	/**
	 * Update User
	 */
	@Override
	public String updateUser(String email, User userDetails) {
		Long userId = dao.getIdByEmail(email);
		if (userId != null) {
			Optional<User> findById = dao.findById(userId);
			User usr = findById.get();
			usr.setUserName(userDetails.getUserName());
			usr.setUserPassword(userDetails.getUserPassword());
			dao.save(usr);
			return "User Updated";
		} else {
			throw new EntityNotFoundException("User does not exist");
		}
	}

	/**
	 * Gives list Of all Users
	 */
	@Override
	public List<User> viewAll() {
		List<User> returnUsers = new ArrayList<User>();
		List<User> userList = dao.findAll();
		for (User user : userList) {
			if (!(user.getIsAdmin().equals("True"))) {
				returnUsers.add(user);
			}
		}
		return returnUsers;
	}

	/**
	 * Finds user Object by Id
	 */
	@Override
	public Optional<User> findById(long userId) {
		Optional<User> findById = dao.findById(userId);
		if (findById.isPresent()) {
			return findById;
		} else {
			throw new EntityNotFoundException("User of user id" + userId + "does not exist");
		}
	}

	/**
	 * Assigns Test To User
	 */
	@Override
	public String assignTest(String email, int testId) {
		Long userId = dao.getIdByEmail(email);
		Optional<User> findById = dao.findById(userId);
		Optional<Test> test = tdao.findById(testId);
		if (findById.isPresent() && test.isPresent()) {
			User usr = findById.get();
			usr.setTestId(testId);
			dao.save(usr);
			return "Test Assigned to user";
		} else {
			throw new EntityNotFoundException("Test does not exist");
		}

	}

	/**
	 * Finds User Object By Email
	 */
	@Override
	public User findUserByEmail(String email) {
		Long userId = dao.getIdByEmail(email);
		Optional<User> findById = dao.findById(userId);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new EntityNotFoundException("User not found");
		}

	}
}
