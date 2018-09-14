package com.tcs.KingfisherDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.EventResponse;
import com.tcs.KingfisherDay.model.QuizResponse;

@Repository
public interface QuizResponseRepository extends JpaRepository<QuizResponse, String> {
	QuizResponse findTopByQuestionIDAndOptionOrderByTimeStamp(String questionID, String optionCorrect);

	long countByOptionAndQuestionID(String string, String questionID);

	long countByQuestionID(String questionID);
}
