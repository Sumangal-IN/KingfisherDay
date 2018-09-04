package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Question;
import com.tcs.KingfisherDay.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	public void updateCurrentQuestion(String questionID) {
		for (Question question : questionRepository.findAll()) {
			if (question.getQuestionID().equals(questionID))
				question.setCurrent(true);
			else
				question.setCurrent(false);
			questionRepository.save(question);
		}
	}

	public Question getCurrentQuestion() {
		List<Question> activeQuestions = questionRepository.findByCurrent(true);
		if (activeQuestions.isEmpty())
			return null;
		else
			return activeQuestions.get(0);
	}
}
