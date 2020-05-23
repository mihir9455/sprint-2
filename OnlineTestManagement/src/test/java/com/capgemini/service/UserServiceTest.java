package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.TestDao;
import com.capgemini.dao.UserDao;
import com.capgemini.entity.User;

@SpringBootTest
/**
 * user service layer tests
 */
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@MockBean
	private UserDao userDao;
	@MockBean
	private TestDao testDao;

	/**
	 * view all users test
	 */
	@Test
	public void viewAllUsersTest() {

		when(userDao.findAll())
				.thenReturn(Stream
						.of(new User("Sahstra", 1, "False", "sahstra@gmail.com", "abcd@1234"),
								new User("Mihir", 1, "False", "mihir @gmail.com", "abcd@1234"))
						.collect(Collectors.toList()));
		assertEquals(2, userService.viewAll().size());

	}

	/**
	 * find user by id method test
	 */
	@Test
	public void findByIdTest() {
		long id = 1;
		User user = new User("Sahstra", 1, "True", "sahstra@gmail.com", "abcd@1234");
		Optional<User> userOptional = Optional.of(user);
		when(userDao.findById(id)).thenReturn(userOptional);
		assertEquals(userOptional, userService.findById(id));

	}

	/**
	 * find user by email test
	 */
	@Test
	public void findUserByEmailTest() {
		String email = "sahstra@gmail.com";
		long id = userDao.getIdByEmail(email);
		User user = new User("Sahstra", 1, "True", "sahstra@gmail.com", "abcd@1234");
		user.setEmail(email);
		Optional<User> userOptional = Optional.of(user);
		when(userDao.findById(id)).thenReturn(userOptional);
		assertEquals(userOptional, userService.findById(id));

	}

	@Test
	public void deleteUserTest() {
		String email = "sahstra@gmail.com";
		long id = userDao.getIdByEmail(email);
		User user = new User("Sahstra", 1, "True", "sahstra@gmail.com", "Abcd@1234");
		Optional<User> userOptional = Optional.of(user);
		when(userDao.findById(id)).thenReturn(userOptional);

		userService.deleteUser("sahstra@gmail.com");

		verify(userDao, times(1)).deleteById(id);

	}

}
