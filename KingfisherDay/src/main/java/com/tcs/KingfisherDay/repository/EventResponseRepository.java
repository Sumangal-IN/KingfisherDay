package com.tcs.KingfisherDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.EventResponse;
import com.tcs.KingfisherDay.model.QuizResponse;

@Repository
public interface EventResponseRepository extends JpaRepository<EventResponse, String> {

}
