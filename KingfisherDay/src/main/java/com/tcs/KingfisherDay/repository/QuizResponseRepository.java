package com.tcs.KingfisherDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.QuizResponse;

@Repository
public interface QuizResponseRepository extends JpaRepository<QuizResponse, String> {

	@Query("select q from QuizResponse q where q.questionID=:questionID and instr(:optionCorrect,q.option)>0 order by q.timeStamp desc")
	QuizResponse findTopByQuestionIDAndOptionOrderByTimeStamp(@Param("questionID") String questionID, @Param("optionCorrect") String optionCorrect);

	long countByOptionAndQuestionID(String string, String questionID);

	long countByQuestionID(String questionID);
}
