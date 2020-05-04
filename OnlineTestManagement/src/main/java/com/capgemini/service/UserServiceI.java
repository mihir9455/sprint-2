package com.capgemin.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.entity.UserTest;

public interface UserServiceI {
	public String addUser(UserTest User);
	public String deleteUser(int UserId);
	public String updateUser(int UserId,UserTest userDetails);
	public List<UserTest> viewAll();
	public Optional<UserTest> findById(int userId);
}
