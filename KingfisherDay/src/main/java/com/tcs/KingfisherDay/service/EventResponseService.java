package com.tcs.KingfisherDay.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.model.EventResponse;
import com.tcs.KingfisherDay.model.enums.EventState;
import com.tcs.KingfisherDay.model.enums.EventVote;
import com.tcs.KingfisherDay.repository.EventRepository;
import com.tcs.KingfisherDay.repository.EventResponseRepository;

@Service
public class EventResponseService {

	@Autowired
	EventResponseRepository eventResponseRepository;

	@Autowired
	EventRepository eventRepository;

	public EventResponse save(String emailID, int eventID, String vote) {
		return eventResponseRepository.save(new EventResponse(eventID, emailID, EventVote.valueOf(vote), new Timestamp(new Date().getTime())));
	}

	public EventResponse saveWithComment(String emailID, int eventID, String vote, String comment) {
		return eventResponseRepository.save(new EventResponse(eventID, emailID, EventVote.valueOf(vote), comment, new Timestamp(new Date().getTime())));
	}

	public List<EventResponse> getLatestResponses() {
		Event currentEvent = eventRepository.findByState(EventState.RUNNING);
		if (currentEvent != null)
			return eventResponseRepository.findTop10ByEventIDOrderByTimeStampDesc(currentEvent.getEventID());
		return null;
	}

}
