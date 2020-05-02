package com.capgemini.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "UserTest")
public class UserTest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@NotBlank(message="User name can not be Blank")
	@Size(min=5,max=20,message="User name should be minimum 5 character long")
	private String userName;
	private int testId;
	private String isAdmin;
	@NotBlank(message="user password can not be blank")
	@Size(min=8,message="Password should be of minimum 8 character long")
	private String userPassword;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public UserTest( String userName, int testId, String isAdmin, String userPassword) {
		
		
		this.userName = userName;
		this.testId = testId;
		this.isAdmin = isAdmin;
		this.userPassword = userPassword;
	}
	public UserTest()
	{}
}
