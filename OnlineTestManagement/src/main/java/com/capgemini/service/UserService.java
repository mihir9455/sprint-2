package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.TestDao;
import com.capgemini.dao.UserDao;
import com.capgemini.entity.Test;
import com.capgemini.entity.UserTest;
@Service
public class UserService implements UserServiceI {
    
	@Autowired
	UserDao dao;
	@Autowired
	TestDao tdao;
	@Override
	public String addUser(UserTest user) {
		
			dao.save(user);
			return "User Created!!"; 
		
	}

	@Override
	public String deleteUser(int userId) {
		Optional<UserTest> findById=dao.findById(userId);
		if(findById.isPresent()) {
			dao.deleteById(userId);
			return "User Removed";
		}
		return "User not exists";
		
	}

	@Override
	public String updateUser(int userId,UserTest userDetails) {
		Optional<UserTest> findById=dao.findById(userId);
		if(findById.isPresent()) {
			UserTest usr=findById.get();
			usr.setUserName(userDetails.getUserName());
			usr.setUserPassword(userDetails.getUserPassword());
			dao.save(usr);
			return "User Updated";
		}
		return "User does not exist";
	}

	@Override
	public List<UserTest> viewAll() {
		
		return dao.findAll();
	}

	@Override
	public Optional<UserTest> findById(int userId) {
		Optional<UserTest> findById=dao.findById(userId);
		return findById;
		
	}
	public String assignTest(int userId,int testId)
	{
		Optional<UserTest> findById=dao.findById(userId);
		Optional<Test> test=tdao.findById(testId);
		if(findById.isPresent() && test.isPresent()) {
			UserTest usr=findById.get();
			usr.setTestId(testId);
			
			dao.save(usr);
			return "Test Assigned to user";
		}
		return "User or Test does not exist";
	}
	
	
	}


