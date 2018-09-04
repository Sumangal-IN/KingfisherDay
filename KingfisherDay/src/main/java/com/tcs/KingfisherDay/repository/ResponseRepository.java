package com.tcs.KingfisherDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, String> {
//	@Query("SELECT TOP 1 r FROM Response r WHERE r.questionID = :question and r.option = :correctOption order by timestamp")
//	Response findTopByOptionOrderByTimeStamp(@Param("question") String questionID,
//			@Param("correctOption") String optionCorrect);
	
	Response findTopByQuestionIDAndOptionOrderByTimeStamp(String questionID,String optionCorrect);
}
