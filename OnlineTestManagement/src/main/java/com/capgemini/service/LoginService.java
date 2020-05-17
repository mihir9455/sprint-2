package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.UserDao;
import com.capgemini.entity.User;

@Service
/**
 * 
 * service for login service class
 *
 */
public class LoginService implements LoginServiceI {

	@Autowired
	UserDao userDao;

	@Override
	/**
	 * login method
	 */
	public String login(String email, String password) {
		long id = userDao.getIdByEmail(email);
		Optional<User> findById = userDao.findById(id);
		if (findById.isPresent()) {
			User user = findById.get();
			if (user.getUserPassword().equals(password)) {
				if (user.getIsAdmin().equals("True")) {
					return "Admin";
				} else {
					return "User";
				}
			}
			return "Incorrect Password";
		}
		return "The User is Not Present";
	}
}
