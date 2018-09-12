package com.tcs.KingfisherDay.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT")
public class Event {

	@Id
	@Column(name = "event_id", nullable = false)
	private String eventId;
	@Column(name = "event_name", nullable = false)
	private String eventName;
	@Column(name = "event_details", nullable = false)
	private String details;
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	@Column(name = "end_date", nullable = true)
	private Date endDate;
	@Column(name = "start_time", nullable = false)
	private String startTime;
	@Column(name = "end_time", nullable = false)
	private String endTime;
	@Column(name = "photo", nullable = false)
	private String photo;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Event() {

	}

	public Event(String eventId, String eventName, String details, Date startDate, Date endDate, String startTime,
			String endTime, String photo) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.details = details;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Events [eventId=" + eventId + ", eventName=" + eventName + ", details=" + details + ", startDate="
				+ startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", photo=" + photo + "]";
	}

}
