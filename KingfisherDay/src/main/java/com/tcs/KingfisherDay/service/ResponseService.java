package com.tcs.KingfisherDay.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Response;
import com.tcs.KingfisherDay.repository.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	ResponseRepository responseRepository;

	public void saveResponse(String questionID, String employeeEmail, String option) {
		responseRepository.save(new Response(questionID, employeeEmail, option, new Timestamp(new Date().getTime())));
	}
}
