package com.tcs.KingfisherDay.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.EventResponse;
import com.tcs.KingfisherDay.model.enums.EventVote;
import com.tcs.KingfisherDay.repository.EventResponseRepository;

@Service
public class EventResponseService {

	@Autowired
	EventResponseRepository eventResponseRepository;

	public void save(String emailID, String eventID, String vote) {
		eventResponseRepository.save(new EventResponse(emailID, eventID, EventVote.valueOf(vote), new Timestamp(new Date().getTime())));
	}
	
	public void saveWithComment(String emailID, String eventID, String vote, String comment) {
		eventResponseRepository.save(new EventResponse(emailID, eventID, EventVote.valueOf(vote),comment, new Timestamp(new Date().getTime())));
	}

}
