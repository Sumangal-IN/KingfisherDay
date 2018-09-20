package com.tcs.KingfisherDay.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.OptionPercentage;
import com.tcs.KingfisherDay.model.Question;
import com.tcs.KingfisherDay.model.QuizResponse;
import com.tcs.KingfisherDay.repository.QuizResponseRepository;

@Service
public class QuizResponseService {

	@Autowired
	QuizResponseRepository quizResponseRepository;

	public QuizResponse saveResponse(String questionID, String employeeEmail, String option) {
		return quizResponseRepository
				.save(new QuizResponse(questionID, employeeEmail, option, new Timestamp(new Date().getTime())));
	}

	public QuizResponse getWinner(String questionID, Question question) {
		return quizResponseRepository.findTopByQuestionIDAndOptionOrderByTimeStamp(questionID, question.getOptionCorrect());
	}

	public OptionPercentage getPercentages(Question question) {
		long total = quizResponseRepository.countByQuestionID(question.getQuestionID());
		if (total == 0)
			return new OptionPercentage(0, 0, 0, 0);
		long totalA = quizResponseRepository.countByOptionAndQuestionID(question.getOptionA(), question.getQuestionID());
		long totalB = quizResponseRepository.countByOptionAndQuestionID(question.getOptionB(), question.getQuestionID());
		long totalC = quizResponseRepository.countByOptionAndQuestionID(question.getOptionC(), question.getQuestionID());
		long totalD = quizResponseRepository.countByOptionAndQuestionID(question.getOptionD(), question.getQuestionID());
		total = totalA + totalB + totalC + totalD;
		return new OptionPercentage((double) totalA / (double) total, (double) totalB / total, (double) totalC / total,
				(double) totalD / total);
	}

}
