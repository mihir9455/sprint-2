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
 * @author mahir
 *
 */
public class UserService implements UserServiceI {

	@Autowired
	UserDao dao;
	@Autowired
	TestDao tdao;

	@Override
	/**
	 * to add new user
	 */
	public String addUser(User user) {
		Long id = dao.getIdByEmail(user.getEmail());
		if (id != null)
			throw new EntityAlreadyExists("Email already present");
		dao.save(user);
		return "User Created!!";

	}

	@Override
	/**
	 * for deleting an user
	 */
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

	@Override
	/**
	 * to update user by his/her email
	 */
	public String updateUser(String email, User userDetails) {
		Long userId = dao.getIdByEmail(email);
		Optional<User> findById = dao.findById(userId);
		if (findById.isPresent()) {
			User usr = findById.get();
			usr.setUserName(userDetails.getUserName());
			usr.setUserPassword(userDetails.getUserPassword());
			dao.save(usr);
			return "User Updated";
		} else {
			throw new EntityNotFoundException("User does not exist");
		}
	}

	@Override
	/**
	 * to see all users except admin
	 */
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

	@Override
	/**
	 * to find user by id
	 */
	public Optional<User> findById(long userId) {
		Optional<User> findById = dao.findById(userId);
		if (findById.isPresent()) {
			return findById;
		} else {
			throw new EntityNotFoundException("User of user id" + userId + "does not exist");
		}
	}

	@Override
	/**
	 * to assign test to user
	 */
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
			throw new EntityNotFoundException("User or Test does not exist");
		}

	}

	@Override
	/**
	 * to find user ny email
	 */
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
