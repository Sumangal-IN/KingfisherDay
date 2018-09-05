package com.tcs.KingfisherDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, String> {
	Response findTopByQuestionIDAndOptionOrderByTimeStamp(String questionID, String optionCorrect);

	long countByOptionAndQuestionID(String string, String questionID);
}
