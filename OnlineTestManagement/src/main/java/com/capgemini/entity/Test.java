package com.capgemini.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer testId;
	private String testTitle;

	@OneToMany(targetEntity = Question.class,cascade= CascadeType.ALL)
	@JoinColumn(name="ts_id", referencedColumnName = "testId")
	private List<Question> testQuestions;
	private int testDuration;
	private int testTotalMarks;
	@Transient
	private int currentQuestion;
	private Date startDate;
	private Date endDate;
	
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public String getTestTitle() {
		return testTitle;
	}
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	public List<Question> getTestQuestions() {
		return testQuestions;
	}
	public void setTestQuestions(List<Question> testQuestions) {
		this.testQuestions = testQuestions;
	}
	public int getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}
	public int getTestTotalMarks() {
		return testTotalMarks;
	}
	public void setTestTotalMarks(int testTotalMarks) {
		this.testTotalMarks = testTotalMarks;
	}
	public int getCurrentQuestion() {
		return currentQuestion;
	}
	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	public Test(String testTitle, List<Question> testQuestions, int testDuration, int testTotalMarks,
			int currentQuestion, Date startDate, Date endDate) {

		this.testTitle = testTitle;
		this.testQuestions = testQuestions;
		this.testDuration = testDuration;
		this.testTotalMarks = testTotalMarks;
		this.currentQuestion = currentQuestion;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Test() {}
	
	
	
	
}
