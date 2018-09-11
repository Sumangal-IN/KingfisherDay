package com.tcs.KingfisherDay.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.OptionPercentage;
import com.tcs.KingfisherDay.model.Question;
import com.tcs.KingfisherDay.model.Response;
import com.tcs.KingfisherDay.repository.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	ResponseRepository responseRepository;

	public Response saveResponse(String questionID, String employeeEmail, String option) {
		return responseRepository
				.save(new Response(questionID, employeeEmail, option, new Timestamp(new Date().getTime())));
	}

	public Response getWinner(String questionID, Question question) {
		return responseRepository.findTopByQuestionIDAndOptionOrderByTimeStamp(questionID, question.getOptionCorrect());
	}

	public OptionPercentage getPercentages(Question question) {
		long total = responseRepository.countByQuestionID(question.getQuestionID());
		if (total == 0)
			return new OptionPercentage(0, 0, 0, 0);
		long totalA = responseRepository.countByOptionAndQuestionID(question.getOptionA(), question.getQuestionID());
		long totalB = responseRepository.countByOptionAndQuestionID(question.getOptionB(), question.getQuestionID());
		long totalC = responseRepository.countByOptionAndQuestionID(question.getOptionC(), question.getQuestionID());
		long totalD = responseRepository.countByOptionAndQuestionID(question.getOptionD(), question.getQuestionID());
		total = totalA + totalB + totalC + totalD;
		return new OptionPercentage((double) totalA / (double) total, (double) totalB / total, (double) totalC / total,
				(double) totalD / total);
	}

}
