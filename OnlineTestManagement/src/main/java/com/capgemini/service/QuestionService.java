package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.QuestionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.entity.Test;

@Service
public class QuestionService implements QuestionServiceI {
	@Autowired
	QuestionDao dao;

	@Autowired
	TestDao testDao;

	@Override
	public String addQuestion(int testId, Question question) {
		Test test = testDao.findById(testId).get();
		if (testDao.findById(testId).isPresent()) {
			List<Question> ques = test.getTestQuestions();
			ques.add(question);
			test.setTestQuestions(ques);
			testDao.save(test);
			return "question added";
		}
		return "Invalid data";

	}

	@Override
	public String deleteQuestion(int questionId) {
		Optional<Question> findById = dao.findById(questionId);
		if (findById.isPresent()) {
			dao.deleteById(questionId);
			return "Question deleted";
		}
		return "Question does not exist";
	}

	@Override
	public List<Question> viewAll() {

		System.out.println(dao.findAll());
		return dao.findAll();
	}

	@Override
	public Optional<Question> findById(int id) {

		return dao.findById(id);

	}
	@Override
    public String updateQuestion(int questionId,Question ques) {
		
		Optional<Question> findById=dao.findById(questionId);
		if(findById.isPresent()) {
			Question q=findById.get();
			q.setQuestionTitle(ques.getQuestionTitle());
			q.setQuestionOptions(ques.getQuestionOptions());
			q.setQuestionAnswer(ques.getQuestionAnswer());
			q.setQuestionMarks(ques.getQuestionMarks());
			dao.save(q);
			return "Question Updated";
		}
		return "Question does not exist";
	}


}
