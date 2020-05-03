package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.TestDao;
import com.capgemini.entity.Test;

@Service
public class TestService implements TestServiceI {
   
	@Autowired
	TestDao dao;
	
	@Override
	public Test addTest(Test test) {
		// TODO Auto-generated method stub
		return dao.save(test);
	}

	@Override
	public String deleteTest(int testId) {
		// TODO Auto-generated method stub
		
		Optional<Test> findById=dao.findById(testId);
		if(findById.isPresent()) {
			dao.deleteById(testId);
			return "Test deleted";
		}
		return "Test does not exist";
	}
	

	@Override
	public String updateTest(int testId,Test test) {
		// TODO Auto-generated method stub
		Optional<Test> tst=dao.findById(testId);
		if(tst.isPresent())
		{
			Test t=tst.get();
			t.setStartDate(test.getStartDate());
			t.setEndDate(test.getEndDate());
			t.setTestDuration(test.getTestDuration());
			//t.setTestQuestions(test.getTestQuestions());
			t.setTestTitle(test.getTestTitle());
			t.setTestTotalMarks(test.getTestTotalMarks());
			dao.save(t);
			return "Test Updated";
		}
		else
			return "Test Does not exist";
		
	}

	@Override
	public List<Test> viewAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<Test> findById(int id) {
		
		return dao.findById(id);
	}

}
