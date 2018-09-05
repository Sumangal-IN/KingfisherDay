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

	public OptionPercentage getPercentages(String questionID) {
		long total = responseRepository.count();
		if (total == 0)
			return new OptionPercentage(0, 0, 0, 0);

		long totalA = responseRepository.countByOptionAndQuestionID("A", questionID);
		long totalB = responseRepository.countByOptionAndQuestionID("B", questionID);
		long totalC = responseRepository.countByOptionAndQuestionID("C", questionID);
		long totalD = responseRepository.countByOptionAndQuestionID("D", questionID);
		total = totalA + totalB + totalC + totalD;
		return new OptionPercentage((double) totalA / (double) total, (double) totalB / total, (double) totalC / total,
				(double) totalD / total);
	}

}
