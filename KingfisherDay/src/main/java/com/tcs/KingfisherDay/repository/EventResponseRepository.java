package com.tcs.KingfisherDay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.EventResponse;

@Repository
public interface EventResponseRepository extends JpaRepository<EventResponse, String> {

	List<EventResponse> findTop10ByEventIDOrderByTimeStampDesc(int eventID);

}
