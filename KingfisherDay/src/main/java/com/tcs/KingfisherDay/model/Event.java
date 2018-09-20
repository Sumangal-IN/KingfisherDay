package com.tcs.KingfisherDay.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcs.KingfisherDay.model.enums.EventState;

@Entity
@Table(name = "EVENT")
public class Event {
	@Id
	@Column(name = "event_id", nullable = false)
	private int eventID;
	@Column(name = "event_name", nullable = false)
	private String eventName;
	@Column(name = "event_details", nullable = false)
	private String details;
	@Column(name = "start", nullable = false)
	private Timestamp start;
	@Column(name = "end", nullable = true)
	private Timestamp end;
	@Column(name = "state", nullable = false)
	private EventState state;
	@Column(name = "ppt_path", nullable = false)
	private String pptPath;
	@Column(name = "photo", nullable = false)
	private String photo;

	public Event() {

	}

	public Event(int eventID, String eventName, String details, Timestamp start, Timestamp end, EventState state, String pptPath, String photo) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.details = details;
		this.start = start;
		this.end = end;
		this.state = state;
		this.pptPath = pptPath;
		this.photo = photo;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
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

	public EventState getState() {
		return state;
	}

	public void setState(EventState state) {
		this.state = state;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getPptPath() {
		return pptPath;
	}

	public void setPptPath(String pptPath) {
		this.pptPath = pptPath;
	}

}