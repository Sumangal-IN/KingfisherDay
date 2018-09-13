package com.tcs.KingfisherDay.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.repository.EventRepository;

@Service
public class EventService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	@Autowired
	EventRepository eventRepository;

	public Event getEventById(String eventId) {
		List<Event> eventList = eventRepository.findByEventId(eventId);
		if (eventList.isEmpty()) {
			return null;
		} else {
			return eventList.get(0);
		}
	}

	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	public Event registerAnEvent(String name, String details, String startDate, String endDate, String startTime, String duration, String photoFile) {
		return eventRepository.save(new Event(String.valueOf(Math.abs(new Random(Integer.MAX_VALUE).nextInt())), name, details, convertToSQLDate(startDate), convertToSQLDate(endDate), startTime, duration, photoFile));
	}

	private Date convertToSQLDate(String startDate) {
		Date sqlDate = null;
		if (null != startDate) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			try {
				java.util.Date date = sdf.parse(startDate);
				sqlDate = new Date(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return sqlDate;
	}
}