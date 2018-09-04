package com.tcs.KingfisherDay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
