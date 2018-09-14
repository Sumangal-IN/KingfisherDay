package com.tcs.KingfisherDay.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT")
public class Event {
	@Id
	@Column(name = "event_id", nullable = false)
	private String eventID;
	@Column(name = "event_name", nullable = false)
	private String eventName;
	@Column(name = "event_details", nullable = false)
	private String details;
	@Column(name = "start", nullable = false)
	private Timestamp start;
	@Column(name = "end", nullable = true)
	private Timestamp end;
	@Column(name = "photo", nullable = false)
	private String photo;
	@Column(name = "current", nullable = false)
	private boolean current;

	public Event() {

	}

	public Event(String eventID, String eventName, String details, Timestamp start, Timestamp end, String photo, boolean current) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.details = details;
		this.start = start;
		this.end = end;
		this.photo = photo;
		this.current = current;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

}